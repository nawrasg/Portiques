'use strict';

var ajs = angular.module('adf.widget.Gate', [ 'adf.provider', 'ngMaterial', 'chart.js' ]);
var up = {"id":"35000_3_2","status":0,"cp":35000,"direction":1,"accepted":413,"pmr":14,"rejected":27,"remain":1382,"open":1,"ticket":1,"luggage":0,"heureOn":"7:00","heureOff":"23:30","gare":1,"train":8667,"quai":3,"numero":2,"classe":-1};

ajs.config(function(dashboardProvider) {
	dashboardProvider.widget('Gate', {
		title : 'UP',
		description : 'Afficher et contrôler les UP.',
		templateUrl : '{widgetsPath}/Gate/src/view.html',
		edit : {
			templateUrl : '{widgetsPath}/Gate/src/edit.html'
		}
	});
});

ajs.controller('UpCtrl', function($scope, $mdDialog){
	var voyclasses = [{id:0, title:'Toutes les classes'}, {id:1, title:'Première classe'}, {id:2, title:'Deuxième classe'}];
	$scope.voyclasses = voyclasses;
	$scope.labels = ['Clients acceptés', 'Clients PMR', 'Clients refusés', 'Clients à embarquer'];
	$scope.series = ['UP'];
	$scope.data = [[]];
	$scope.data[0].push(up.accepted);
	$scope.data[0].push(up.pmr);
	$scope.data[0].push(up.rejected);
	$scope.data[0].push(up.remain);
  
	$scope.openStats = function(e){
		$mdDialog.show({
			template : '<md-dialog layout-padding flex><md-subheader>Statistiques</md-subheader><md-content><canvas id="bar" class="chart chart-bar" data="data" labels="labels"></canvas></md-content><div class=md-actions><md-button ng-click=close()>Fermer</md-button></div></md-dialog>',
			targetEvent : e,
			scope: $scope,
			preserveScope: true
		});
	};
	$scope.close = function(){
		$mdDialog.cancel();
	}
});

