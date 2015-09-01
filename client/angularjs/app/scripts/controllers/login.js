'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:LoginCtrl
 * @description # LoginCtrl Controller of the angularjsApp
 */

nApp.controller('LoginCtrl', function($scope, $http, base64, $sessionStorage, $rootScope, $location, PortiquesUri) {
	$scope.auth = function(){
		var nUsername = $scope.user.username;
		var nPwd = $scope.user.password;
		var nURL = PortiquesUri.Login();
		$http.defaults.headers.common.Authorization = base64.encode(nUsername + ':' + nPwd);
		$http.get(nURL).success(function(data, status){
			if(status == 202){
				$sessionStorage.user = data;
				delete $http.defaults.headers.common.Authorization;
				$location.path('/');
			}else{
				console.log(status, data);
			}
		});
	};
});
