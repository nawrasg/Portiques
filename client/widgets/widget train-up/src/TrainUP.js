'use strict';

var ajs = angular.module('adf.widget.TrainUP', [ 'adf.provider', 'ngMaterial' ]);

ajs.config(function(dashboardProvider) {
	dashboardProvider.widget('TrainUP', {
		title : 'TrainUP',
		description : 'Affecter un train à une unité de passage.',
		templateUrl : '{widgetsPath}/TrainUP/src/view.html',
		edit : {
			templateUrl : '{widgetsPath}/TrainUP/src/edit.html'
		}
	});
});

ajs.controller('UpCtrl', function($scope){
	var ups = [{id:'35000_1_2', trains:[{numero:8657, affected:true}, {numero:8658, affected:false}, {numero:8659, affected:false}]}, 
	           {id:'35000_1_3', trains:[{numero:8667, affected:false}, {numero:8668, affected:false}, {numero:8669, affected:false}]}];
	$scope.ups = ups;
	$scope.addTrain = function(train){
		train.affected = true;
	};
	$scope.removeTrain = function(train){
		train.affected = false;
	};
});
