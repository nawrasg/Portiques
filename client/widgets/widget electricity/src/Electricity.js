'use strict';

angular.module('adf.widget.Electricity', ['adf.provider'])
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('Electricity', {
        title: 'Electricity',
        description: 'Affiche la consommation électrique des portiques.',
        templateUrl: '{widgetsPath}/Electricity/src/view.html',
        edit: {
          templateUrl: '{widgetsPath}/Electricity/src/edit.html'
        }
      });
  });
