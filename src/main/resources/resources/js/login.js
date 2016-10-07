/**
 * Created by weiQiang on 18:00.
 */
var app = angular.module('userLogin',[]);
app.controller('loginCtr',['$scope','$http',function ($scope,$http) {
   //登录
    $scope.login = function () {
        if($scope.userName.trim()!=''&&$scope.password.trim()!=''){
            $http({
                url:content+'user/login',
                method:'GET',
                data:{
                    username:$scope.userName,
                    password:$scope.password,
                    _csrf:'6829blae-0a14-4920-aac4-5abbd7eeb9ee'
                }
                // url:content+'j_spring_security_check',
                // method:'POST',
                // params:{
                //     j_username:$scope.userName,
                //     j_password:$scope.password,
                //     _csrf:'6829blae-0a14-4920-aac4-5abbd7eeb9ee'
                // }
            }).success(function (response) {
                if(response.flag){
                    document.location='index.html';
                }
            }).error(function (response) {

            });
        }
    }
    //重置
    $scope.reset = function () {
        $scope.initLogin();
    }
    //初始化
    $scope.initLogin = function () {
        $scope.userName = 'admin';
        $scope.password = 'bonc';
        $scope.errorMessage = '';
    }
    $scope.initLogin();
}]);