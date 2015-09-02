'use strict';

angular.module('adf.widget.Alarmes', ['adf.provider'])
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('Alarmes', {
        title: 'Alarmes',
        description: 'Afficher les alarmes des portiques.',
        templateUrl: '{widgetsPath}/Alarmes/src/view.html',
        edit: {
          templateUrl: '{widgetsPath}/Alarmes/src/edit.html'
        }
      });
  });
