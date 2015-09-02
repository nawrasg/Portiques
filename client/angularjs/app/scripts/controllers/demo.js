'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:DemoCtrl
 * @description # DemoCtrl Controller of the angularjsApp
 */

nApp.controller('DemoCtrl', function($scope, $localStorage) {
	var model = $localStorage.dashboard;
	$scope.model = model;
	$scope.$on('adfDashboardChanged', function(event, name, model){
		$localStorage.dashboard = model;
	});
});
