(function(window, undefined) {'use strict';


var ajs = angular.module('adf.widget.TrainUP', [ 'adf.provider', 'ngMaterial' ]);

ajs.config(["dashboardProvider", function(dashboardProvider) {
	dashboardProvider.widget('TrainUP', {
		title : 'TrainUP',
		description : 'Affecter un train à une unité de passage.',
		templateUrl : '{widgetsPath}/TrainUP/src/view.html',
		edit : {
			templateUrl : '{widgetsPath}/TrainUP/src/edit.html'
		}
	});
}]);

ajs.controller('UpCtrl', ["$scope", function($scope){
	var ups = [{id:'35000_1_2', trains:[{numero:8657, affected:true}, {numero:8658, affected:false}, {numero:8659, affected:false}]}, 
	           {id:'35000_1_3', trains:[{numero:8667, affected:false}, {numero:8668, affected:false}, {numero:8669, affected:false}]}];
	$scope.ups = ups;
	$scope.addTrain = function(train){
		train.affected = true;
	};
	$scope.removeTrain = function(train){
		train.affected = false;
	};
}]);

angular.module("adf.widget.TrainUP").run(["$templateCache", function($templateCache) {$templateCache.put("{widgetsPath}/TrainUP/src/edit.html","<form role=form><div class=form-group><label for=sample>Sample</label> <input type=text class=form-control id=sample ng-model=config.sample placeholder=\"Enter sample\"></div></form>");
$templateCache.put("{widgetsPath}/TrainUP/src/view.html","<div ng-controller=UpCtrl><div layout=row><div flex=33><label>Liste des UPs :</label><md-input-container flex><md-select ng-model=up placeholder=\"Liste des UPs\"><md-option ng-value=up ng-repeat=\"up in ups\">UP {{up.id}}</md-option></md-select></md-input-container></div><div flex><label>Liste des trains disponibles :</label><div layout=row layout-align=\"center start\"><md-input-container flex><md-select ng-model=train placeholder=\"Liste des trains\"><md-option ng-value=train ng-repeat=\"train in up.trains\" ng-if=!train.affected>Train n&deg;{{train.numero}}</md-option></md-select></md-input-container><md-button class=\"md-fab md-primary md-mini\" aria-label=add ng-click=addTrain(train)><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAQAAABKfvVzAAAAH0lEQVQ4y2NgGAUw8B8IRjXgUoQLUEfDaDyQqmF4AwADqmeZrHJtnQAAAABJRU5ErkJggg==\"></md-button></div></div></div><div><label>Trains affect&eacute;s :</label><div layout=row ng-repeat=\"train in up.trains\" ng-if=train.affected><label flex=33>Train n&deg;{{train.numero}}</label> <img ng-click=removeTrain(train) src=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAQAAABKfvVzAAAAGElEQVR4AWMgDEbBKPiPG1KqAROMglEAAAoWG+UEvH49AAAAAElFTkSuQmCC></div></div></div>");}]);})(window);