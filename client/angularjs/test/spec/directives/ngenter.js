'use strict';

describe('Directive: ngenter', function () {

  // load the directive's module
  beforeEach(module('angularjsApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<ngenter></ngenter>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the ngenter directive');
  }));
});
