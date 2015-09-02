'use strict';

// Declare app level module which depends on views, and components
var myApp = angular.module('myApp', ['AngularStomp']);

/*myApp.controller("nbBilletsCompostesController", function ($scope, $http) {
 $scope.nbBillets = function () {
 if ($scope.numeroTrain > 0) {
 $http.get('http://localhost:8080/train?numero=' + $scope.numeroTrain).success(function (data, status, headers, config) {
 return data;
 }).error(function (data, status, headers, config) {
 return data;
 });
 }
 return "inconnu";
 };
 });*/

myApp.controller("nbBilletsCompostesController", function ($scope, $http) {
    $http.get('http://localhost:8080/sncf/trains/8667').success(function (data, status, headers, config) {
        $scope.nbBillets = data.nombreBilletsCompostes;
    }).error(function (data, status, headers, config) {
        $scope.nbBillets = data;
    });
});

myApp.controller("nSocketController", function ($scope, ngstomp) {
    $scope.messages = [];
    $scope.client = ngstomp('http://localhost:8080/hello');
    $scope.client.connect("name", "guest", function(){
        $scope.client.subscribe("/topic/greetings", function(message) {
            //$scope.messages.push(message.body);
            $scope.reponse = message.body;
        });
    });
});