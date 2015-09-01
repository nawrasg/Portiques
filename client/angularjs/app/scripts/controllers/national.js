'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:NationalCtrl
 * @description # NationalCtrl Controller of the angularjsApp
 */

nApp.controller('NationalCtrl', function($scope, $http, $sessionStorage, PortiquesUri) {
	getGare();
	
	$scope.getLink = function(point){
		return '#local/' + point.id;
	};
	function getGare (){
		var nURL = PortiquesUri.Gares();
		$http.get(nURL).success(function(data, status, header, config){
			$scope.points = data;
			$sessionStorage.gares = data;
		});
	}
});
