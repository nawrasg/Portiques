'use strict';

/**
 * @ngdoc overview
 * @name angularjsApp
 * @description
 * # angularjsApp
 *
 * Main module of the application.
 */
var nApp = angular.module('angularjsApp', ['ngRoute', 'ngSanitize', 'ngMaterial', 'ngStorage']);
  

nApp.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/main.html',
		controller : 'MainCtrl'
	}).when('/login', {
		templateUrl : 'views/login.html',
		controller : 'LoginCtrl'
	}).otherwise({
		redirectTo : '/'
	});
});

nApp.run(function($rootScope, $location, $sessionStorage) {
	$rootScope.$on("$routeChangeStart", function(event, next, current) {
		if ($sessionStorage.api == null) {
			$rootScope.navigation = true;
			$location.path('/login');
		}
	});
});