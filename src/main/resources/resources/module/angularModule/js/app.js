/**
 * Created by Administrator on 22:04.
 */
angular.module('jthink', ['jthink.filters', 'jthink.services', 'jthink.directives']).
config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: MyCtrl2});
    $routeProvider.when('/register', {templateUrl: 'partials/register.html', controller: MyCtrl2});
    $routeProvider.otherwise({redirectTo: '/login'});
}]);