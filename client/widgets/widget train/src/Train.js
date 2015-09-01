'use strict';

var ajs = angular.module('adf.widget.Train', [ 'adf.provider', 'chart.js' ]);
var mStatus = false;
var stompClient = null;

ajs.config(function(dashboardProvider) {
	dashboardProvider.widget('Train', {
		title : 'Train',
		description : 'Information sur le remplissage d\'un train et controle de l\'embarquement',
		templateUrl : '{widgetsPath}/Train/src/view.html',
		edit : {
			templateUrl : '{widgetsPath}/Train/src/edit.html'
		}
	});
});

ajs.controller('TrainWidgetController', function($scope, $http, $rootScope, $filter) {
	$scope.image = 'src/dismiss.png';
	$scope.statusClasse = 'btn btn-danger btn-block';
	$scope.statusLabel = 'Embarquement en attente';
	$scope.status = function(){
		if(mStatus){
			updateTrainDetails('nogo');
		}else{
			updateTrainDetails('go');
		}
		mStatus = !mStatus;
	};
	
	$scope.labels = [ "Voyageurs à bord", "Voyageurs à venir", "Capacité" ];
	$scope.colors = [ '#FD1F5E', '#1EF9A1', '#050000' ];
	$scope.options = {animateScale : true, animationEasing : "easeInOutQuart", animationSteps : 50};
	getTrainDetails();
	
	SocketConnect($scope, $http);
	$scope.connect = function() {
		SocketConnect($scope, $http);
	};
	$scope.disconnect = function() {
		if (stompClient != null) {
			stompClient.disconnect();
		}
	};
	$scope.send = function() {
		stompClient.send("/app/hello", {}, JSON.stringify({
			'name' : 'Fred Le Grand'
		}));
	};
	
	function SocketConnect($scope, $http){
		var socket = new SockJS('/sncf');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			stompClient.subscribe('/topic/update', function(greeting) {
				var msg = JSON.parse(greeting.body).content;
				if (msg == 'update') {
					getTrainDetails();
					updateLastTimestamp($rootScope, $filter);
				}
			});
			
		});
	}
	function updateTrainDetails(status){
		var nURL = 'http://localhost:8080/sncf/gares/35000/trains/' + $scope.config.train + '/' + status;
		$http.get(nURL).success(function(data, status, headers, config) {
			getTrainDetails();
		}).error(function(data, status, headers, config){
			console.log(status, data);
		});
	}
	
	function getTrainDetails() {
		var nURL = 'http://localhost:8080/sncf/trains/' + $scope.config.train; 
		$http.get(nURL).success(function(data, status, headers, config) {
			var abord = data.embarquesNumber;
			var voyageurs = data.buyNumber;
			var train = data.trainId;
			var capacite = data.trainCapacity;
			var vide = voyageurs - abord;
			$scope.abord = abord;
			$scope.voyageurs = voyageurs;
			$scope.data = [ abord, vide, capacite - voyageurs ];
			
			mStatus = data.status; 
			if(mStatus){
				$scope.statusClasse = 'btn btn-success btn-block';
				$scope.statusLabel = 'Embarquement en cours';
			}else{
				$scope.statusClasse = 'btn btn-danger btn-block';
				$scope.statusLabel = 'Embarquement en attente';
			}
		});
	}
});

