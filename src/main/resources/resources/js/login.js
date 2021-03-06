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
                method:'POST',
                params:{
                    username:$scope.userName,
                    password:$scope.password //,
                    //CSRF:'6829blae-0a14-4920-aac4-5abbd7eeb9ee'
                }
            }).success(function (response, success, error) {
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
    //获取地址参数
    $scope.getUrlParam = function(name){
        alert(name);
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substring(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return '';
    }
    $scope.initLogin();
}]);