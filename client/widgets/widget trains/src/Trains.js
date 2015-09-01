'use strict';

angular.module('adf.widget.Trains', ['adf.provider'])
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('Trains', {
        title: 'Trains',
        description: 'Widget pour le remplissage des trains.',
        templateUrl: '{widgetsPath}/Trains/src/view.html',
        edit: {
          templateUrl: '{widgetsPath}/Trains/src/edit.html'
        }
      });
  });
