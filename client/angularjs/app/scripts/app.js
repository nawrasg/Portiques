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

