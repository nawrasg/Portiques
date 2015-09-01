'use strict';

describe('Service: PortiquesUri', function () {

  // load the service's module
  beforeEach(module('angularjsApp'));

  // instantiate service
  var PortiquesUri;
  beforeEach(inject(function (_PortiquesUri_) {
    PortiquesUri = _PortiquesUri_;
  }));

  it('should do something', function () {
    expect(!!PortiquesUri).toBe(true);
  });

});
