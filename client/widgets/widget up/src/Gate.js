'use strict';

angular.module('adf.widget.Gate', ['adf.provider'])
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('Gate', {
        title: 'UP',
        description: 'Afficher et contrôler les UP.',
        templateUrl: '{widgetsPath}/Gate/src/view.html',
        edit: {
          templateUrl: '{widgetsPath}/Gate/src/edit.html'
        }
      });
  });
