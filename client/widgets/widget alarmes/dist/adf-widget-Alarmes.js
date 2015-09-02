(function(window, undefined) {'use strict';


var ajs = angular.module('adf.widget.Alarmes', [ 'adf.provider', 'ngMaterial' ]);

ajs.config(["dashboardProvider", function(dashboardProvider) {
	dashboardProvider.widget('Alarmes', {
		title : 'Alarmes',
		description : 'Afficher les alarmes des portiques.',
		templateUrl : '{widgetsPath}/Alarmes/src/view.html',
		edit : {
			templateUrl : '{widgetsPath}/Alarmes/src/edit.html'
		}
	});
}]);

ajs.factory('Excel',["$window", function($window){
    var uri='data:application/vnd.ms-excel;base64,',
    template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
    base64=function(s){return $window.btoa(unescape(encodeURIComponent(s)));},
    format=function(s,c){return s.replace(/{(\w+)}/g,function(m,p){return c[p];})};
	return {
	    tableToExcel:function(tableId,worksheetName){
	        var table=$(tableId),
	            ctx={worksheet:worksheetName,table:table.html()},
	            href=uri+base64(format(template,ctx));
	        return href;
	    }
	};
}]);

ajs.controller('AlarmCtrl', ["$scope", "$mdDialog", "$timeout", "$filter", "Excel", function($scope, $mdDialog, $timeout, $filter, Excel){
	var alarms = [{id:0, date:1441181523000, level:201, quai:3, up:1, message:'Lecteur en panne', treated:false, actions:[{id:1, title:'Hello'}, {id:2, title:'Tout réparer'}, {id:3, title:'Tout éteindre'}]},
	              {id:1, date:1441181523000, level:201, quai:3, up:1, message:'Porte coincée', treated:false, actions:[{id:1, title:'Redémarrer'}]},
	              {id:2, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:true, actions:[{id:1, title:'Do'}]},
	              {id:3, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:4, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:5, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:6, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:7, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:true, actions:[{id:1, title:'Do'}]},
	              {id:8, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:9, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:true, actions:[{id:1, title:'Do'}]},
	              {id:10, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:11, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:12, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:13, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:14, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:15, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]},
	              {id:16, date:1441181523000, level:301, quai:3, up:0, message:'Portique foutu', treated:false, actions:[{id:1, title:'Do'}]}];
	$scope.alarms = alarms;
	$scope.data = alarms;
	$scope.viewby = 5;
	$scope.totalItems = $scope.data.length;
	$scope.currentPage = 1;
	$scope.itemsPerPage = $scope.viewby;
	$scope.maxSize = 5;
	
	$scope.getIcon = function(alarm){
		if(alarm.treated){
			return 'data:image/png;base64,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';
		}else{
			return 'data:image/png;base64,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';
		}
	};
	$scope.getActions = function(alarm){
		if(alarm.actions.length > 0 && !alarm.treated){
			return 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAEkklEQVR42s2VC0wcVRSGzw6lmFipLdrWt4mJMWk01RZNNY0xRmNStUlJGyzLowqBNkAfoFJDBBIVjBIiUalJUy22rLaGAtbEIC2lrbUVIcqrLG8oy7Osu8w+Zubemeu5MwvUGHddulAnnJ3dyWW+7/z3zK4JbvJh+t8K/HCxPzwiPCy6sXOy50D8+olFF8gqbTiX9PLaTXmHfhU7hxwZHUd3HFlUgQ1J5exIUQz0jDjh068bYWhMtDCAtC6LeXpRBB7c8hn77vNEcMoqCJoG39d2wKlzfX2CYIrrrDBfWnCBB175hJWXmEGUqLEQVw5ftcPhylbidCn5msaKur6J1xZM4P7NxeyLolhwYwL8YL5XRjWoqrXCbx2T9ZhGPKZhWxCB+176kJUUbAdJUWevMa6Bf4IA0NU7BSdP914jlCVfqTBXh1zgnhffZ4XvxAAC9EVsJgNmvDAsWaZQU9/PBsdcZXgxy2pJkEImcPfzBSzvza2gsdn2fQkYcF4aDqeAd2i12qGhebTNZDK9dqUivi0kAnc9l8ty9sZct8SI34BrCJ8581LB5SZQd3nM63CTrM6KhLIbFljzbA7LztiG02/yRc/+Xj4wL1XFMxa/3tI9DR2DripMJrnTkjg1b4HVm7LZ/vTY2SU8/jnwHFxTNRSgelHKP1P4U1Shqcs97JFIfM+JN87OT+CZfWxPulmHGgmAHvmMgKpRvWvePaUoQAmE4d1cogsmJ+3QMzAODnW5GrZ0WRH+Y37fyTQalMCqpzNZZnqib6+NDGb2nUNNoIEiK+DxSOByecDpFGF0fArfu0GWvHpJWEuXrYaI2++t661MeyE4gY27WUpKEihE1cEqxksxbkIoKArFoUOILON7LH6WJfwszcG9xvmW26JAuHVNXV91ZpACT6Wy7XEJOoxPv8YnXt9vDUVwv7kIUf4BN8AefSaEiEhVVsMLQQgrGDiVHeQWPJnMzK+n4s3p7DPPh44PGi+Ce64oxIBLPjiCZRQAgYHoVYcIYebB2vzz8xrCVdE72c5de4BIhoCqabMDR3gRQ+D6yBUioYQbh9BxnEFY6tUzhQ4IcPyrwJ3rE1jq/hwgHuIbPB49F/B1zgVwCCVMQPJ6gKhesA31u6Yd7kzb+dIvA4EDCtzxRBzLyMkH6lX0IdThxNc57r2sw3EGiAx2hw162tobKWVxoxcPdv9XuF+BqMdj2b68QtAwAb17PXb+BCBc4XAZvNQL1vZGdaR34CP8yXp3ovErEgzcr8DKddvYWx+UAHMp+t4rM3DeOSYwhl03na0blkR3wkTTsfpgwQEFVjy2lR0oLgPBJeudyz64SCVo/uMCdP1yuRIfz5Rrv39rny/cr8DyR7ew3NLDsESUdLAXIx8Ux6Gh5rjbaRvda2+pPHQj4IACkWs3s7yyoyA48GtWkeBCy8/QXFPVpFE1ztFWbQ0F3K9A1LpX2a6PD4J9dAR+qrZoY63txXg5V7T+qIQK7ldgg/lt54pHHo68VG6x4Vdworv3zOlQggMKRO9+b+PKiCUP9TW3VHc3VIgLAfcrsFjHTRf4C7v0YE42gaKhAAAAAElFTkSuQmCC';
		}
	};
	$scope.seeActions = function(alarm, e){
		$scope.alarm = alarm;
		$mdDialog.show({
			template : '<md-dialog layout-padding><md-subheader>Actions</md-subheader><md-content><div ng-repeat="action in alarm.actions"><label ng-click="">{{action.title}}</label></div></md-content><div class=md-actions><md-button ng-click=close()>Fermer</md-button></div></md-dialog>',
			targetEvent : e,
			scope: $scope,
			preserveScope: true
		});
	};
	$scope.close = function(){
		$mdDialog.cancel();
	};
	$scope.exportExcel=function(tableId){
        var exportHref=Excel.tableToExcel(tableId,'Alarmes');
        $timeout(function(){location.href=exportHref;},100); // trigger download
    };
    $scope.filter = function(search){
    	var result = [];
		angular.forEach($scope.data, function(current){
			if($filter('lowercase')(current.message).indexOf($filter('lowercase')(search)) > -1){
				result.push(current);
			}
		})
		$scope.alarms = result;
    };
}]);


angular.module("adf.widget.Alarmes").run(["$templateCache", function($templateCache) {$templateCache.put("{widgetsPath}/Alarmes/src/edit.html","<form role=form><div class=form-group><label for=sample>Sample</label> <input type=text class=form-control id=sample ng-model=config.sample placeholder=\"Enter sample\"></div></form>");
$templateCache.put("{widgetsPath}/Alarmes/src/view.html","<div ng-controller=AlarmCtrl><md-input-container><label>Recherchez</label> <input ng-model=search ng-change=filter(search)></md-input-container><div layout=row style=margin-bottom:20px><md-checkbox flex class=md-primary ng-model=showAll aria-label=\"Tout afficher\">Tout afficher</md-checkbox><button class=\"btn btn-success btn-lg\" ng-click=\"exportExcel(\'#alarmTable\')\">Exporter Excel</button></div><table class=\"table table-striped\" id=alarmTable hide-sm><thead><tr><th>Etat</th><th>Date</th><th>Niveau</th><th>Quai</th><th>UP</th><th>Message</th><th>Actions</th></tr></thead><tr ng-repeat=\"alarm in alarms.slice(((currentPage-1)*itemsPerPage), ((currentPage)*itemsPerPage))\" ng-hide=\"alarm.treated && !showAll\"><td><img ng-src={{getIcon(alarm)}}></td><td>{{::alarm.date | date : \'dd/MM/yyyy &agrave; HH:mm\'}}</td><td>{{::alarm.level}}</td><td>{{::alarm.quai}}</td><td>{{::alarm.up}}</td><td>{{::alarm.message}}</td><td><img ng-src={{getActions(alarm)}} ng-click=seeActions(alarm)></td></tr></table><table class=\"table table-striped\" hide-gt-sm><tr ng-repeat=\"alarm in alarms\" ng-hide=\"alarm.treated && !showAll\"><td><img ng-src={{getIcon(alarm)}}></td><td flex layout=column layout-align><label>{{::alarm.level}} - {{::alarm.message}}</label> <label>Quai : {{::alarm.quai}} - UP : {{::alarm.up}}</label> <label>{{::alarm.date | date : \'dd/MM/yyyy &agrave; HH:mm\'}}</label></td><td><img ng-src={{getActions(alarm)}} ng-click=seeActions(alarm)></td></tr></table><div layout=row layout-align=\"center start\"><pagination total-items=totalItems ng-model=currentPage ng-change=pageChanged() class=pagination-sm items-per-page=itemsPerPage></pagination></div></div>");}]);})(window);