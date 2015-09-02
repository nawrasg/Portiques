'use strict';

/**
 * @ngdoc function
 * @name angularjsApp.controller:DemoCtrl
 * @description
 * # DemoCtrl
 * Controller of the angularjsApp
 */
angular.module('angularjsApp')
  .controller('DemoCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
