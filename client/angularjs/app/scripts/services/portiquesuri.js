'use strict';

/**
 * @ngdoc service
 * @name angularjsApp.PortiquesUri
 * @description # PortiquesUri Factory in the angularjsApp.
 */

nApp.factory('PortiquesUri', function() {
	var mIP = 'http://localhost:8080/portiques';
	return {
		Login : function(){return mIP + '/auth';},
	};
});
