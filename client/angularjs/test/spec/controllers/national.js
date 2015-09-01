'use strict';

describe('Controller: NationalCtrl', function () {

  // load the controller's module
  beforeEach(module('angularjsApp'));

  var NationalCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    NationalCtrl = $controller('NationalCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
