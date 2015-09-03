'use strict';

var ajs = angular.module('adf.widget.Electricity', [ 'adf.provider', 'ngMaterial', 'chart.js' ]);

ajs.config(function(dashboardProvider) {
	dashboardProvider.widget('Electricity', {
		title : 'Electricity',
		description : 'Affiche la consommation électrique des portiques.',
		templateUrl : '{widgetsPath}/Electricity/src/view.html',
		edit : {
			templateUrl : '{widgetsPath}/Electricity/src/edit.html'
		}
	});
});

ajs.controller('CurrentCtrl', function($scope){
	
});