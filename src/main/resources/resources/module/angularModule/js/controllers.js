/**
 * Created by Administrator on 22:04.
 */
var controllers = angular.module('jthink.controllers', []);
controllers.controller('LoginCTRL', ['$scope', 'LoginService', function ($scope, LoginService) {
    $scope.login = {};
    $scope.login.submit = function() {
        console.log($scope.login.email);
        console.log($scope.login.password);
        // do some process, invoke the service layer
        LoginService.login(
            {},
            {
                email: $scope.login.email,
                password: $scope.login.password
            },
            function (success) {
                if (success.status == "success") {
                    alert('login success');
                } else {
                    // error message
                }
            },
            function (error) {
                // error message
            }
        );
    };
}]);