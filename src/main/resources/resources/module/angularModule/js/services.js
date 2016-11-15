/**
 * Created by Administrator on 22:04.
 */
var services = angular.module('jthink.services', ['ngResource']).
value('version', '1.0');

services.factory('LoginService', ['$resource', function ($resource) {
    return $resource('fakeData/userLogin.json', {}, {
        login: {method: 'GET', params: {}, isArray: false}
    });
}]);