/**
 * Created by weiQiang on 7:23.
 */
var app = angular.module('indexApp',[]);
app.controller('indexCtr',['$scope','$http',function ($scope,$http) {
   $scope.initUlLi=function () {
       $http({
           url:content+'sys/getDropdownMe',
           method:'GET'
       }).success(function (response) {

       }).error(function () {

       });
       $scope.sysMas = [
           {
               name:'角色管理',
               url:'#'
           },{
               name:'用户管理',
               url:'#'
           },{
               name:'模块管理',
               url:'#'
           }
       ];
   }
   $scope.updatePwd = function () {

   }
   $scope.updateInfo = function () {

   }
   $scope.initUlLi();
}]);