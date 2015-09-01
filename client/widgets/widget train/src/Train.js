'use strict';

angular.module('adf.widget.Train', ['adf.provider'])
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('Train', {
        title: 'Train',
        description: 'Information sur le remplissage d'un train et controle de l'embarquement',
        templateUrl: '{widgetsPath}/Train/src/view.html',
        edit: {
          templateUrl: '{widgetsPath}/Train/src/edit.html'
        }
      });
  });
