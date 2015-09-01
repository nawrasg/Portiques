'use strict';

angular.module('adf.widget.TrainUP', ['adf.provider'])
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('TrainUP', {
        title: 'TrainUP',
        description: 'Affecter un train à une unité de passage.',
        templateUrl: '{widgetsPath}/TrainUP/src/view.html',
        edit: {
          templateUrl: '{widgetsPath}/TrainUP/src/edit.html'
        }
      });
  });
