(function(window, undefined) {'use strict';


var nApp = angular.module('adf.widget.Trains', [ 'adf.provider', 'googlechart' ]);
var stompClient = null;
nApp.config(["dashboardProvider", function(dashboardProvider) {
	dashboardProvider.widget('Trains', {
		title : 'Trains',
		description : 'Get trains details.',
		templateUrl : '{widgetsPath}/Trains/src/view.html',
		edit : {
			templateUrl : '{widgetsPath}/Trains/src/edit.html'
		}
	});
}]);

nApp.controller('TrainsCtrl', ["$scope", "$http", "$rootScope", "$filter", function($scope, $http, $rootScope, $filter) {
	loadTrains($scope, $http);
	SocketConnect($scope, $http);
	$scope.connect = function() {
		SocketConnect($scope, $http);
	};
	$scope.disconnect = function() {
		if (stompClient != null) {
			stompClient.disconnect();
		}
	};
	
	function SocketConnect($scope, $http){
		var socket = new SockJS('/sncf');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			stompClient.subscribe('/topic/trains', function(greeting) {
				var msg = JSON.parse(greeting.body).content;
				if (msg == 'update') {
					loadTrains($scope, $http);
					updateLastTimestamp($rootScope, $filter);
				}
			});

		});
	}
}]);

function loadTrains($scope, $http){
	var chart1 = {};
	chart1.type = "ColumnChart";
	var nGare = $scope.config.gare;
	var nURL = 'http://localhost:8080/sncf/gares/' + nGare + '/trains';
	$http.get(nURL).success(function(data, status) {
		if (status == 200) {
			chart1.data = {
				'cols' : [ {
					id : "train",
					label : 'Train',
					type : 'string'
				}, {
					id : "validate",
					label : 'Billets Compostes',
					type : 'number'
				}, {
					id: 'buy',
					label: 'A venir',
					type:'number'
				},{
					id:'empty',
					label:'Vide',
					type:'number'
				}],
				'rows' : [ ]
				 
			};
			angular.forEach(data, function(train) {
				var row = [];
				row.push({v:train.id + ''});
				row.push({v:train.validate});
				var buy = train.tickets - train.validate;
				row.push({v:buy});
				var empty = train.capacity - train.tickets;
				row.push({v:empty});
				chart1.data.rows.push({c:row});
			});
			chart1.options = {
				"title" : "Remplissage des trains",
				"isStacked" : "true",
				"fill" : 20,
				"displayExactValues" : true,
				"vAxis" : {
					"title" : "Nombre des voyageurs",
					"gridlines" : {
						"count" : 6
					}
				},
				"hAxis" : {
					"title" : "Train"
				}
			};

			chart1.formatters = {};

			$scope.chart = chart1;
		
		}
	});
}



angular.module("adf.widget.Trains").run(["$templateCache", function($templateCache) {$templateCache.put("{widgetsPath}/Trains/src/edit.html","<form role=form><div class=form-group><label for=sample>Gare</label> <input type=text class=form-control id=gare ng-model=config.gare></div></form>");
$templateCache.put("{widgetsPath}/Trains/src/view.html","<div ng-controller=TrainsCtrl><div google-chart chart=chart style={{chart.cssStyle}}></div></div>");}]);})(window);