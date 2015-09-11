'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:LoginCtrl
 * @description # LoginCtrl Controller of the angularjsApp
 */

nApp.controller('LoginCtrl', function($scope, $http, base64, $sessionStorage, $rootScope, $location, $mdToast, PortiquesUri) {
	$scope.auth = function(){
		var nUsername = $scope.user.username;
		var nPwd = $scope.user.password;
		var nURL = PortiquesUri.Login();
		$http.defaults.headers.common.Authorization = base64.encode(nUsername + ':' + nPwd);
		$http.get(nURL).success(function(data, status){
			if(status == 202){
				$sessionStorage.user = data;
				delete $http.defaults.headers.common.Authorization;
				$location.path('/demo');
			}else{
				showToast($mdToast, data);
			}
		});
	};
	
	var toastPosition = {
    bottom : false,
    top : true,
    left : false,
    right : true
  };
  
  function getToastPosition() {
    return Object.keys(toastPosition).filter(function(pos) {
      return toastPosition[pos];
    }).join(' ');
  }
  
  function showToast($mdToast, message) {
    $mdToast.show($mdToast.simple().content(message).position(
        getToastPosition()).hideDelay(3000));
  }
});
