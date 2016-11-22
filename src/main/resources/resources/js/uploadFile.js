/**
 * Created by Administrator on 21:40.
 */
var app = angular.module('uploadApp',[]);
app.controller('uploadImg',['$scope','$http',function ($scope,$http) {
    $scope.imageSrc = 'd://123.jpg';
    $scope.uploadImg = function () {
        var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('file', file);
        $http({
            url:content+'upload/img',
            method: 'POST',
            headers: {'Content-Type':undefined},
            data: fd,
            transformRequest: angular.identity
        }).success(function (response) {
            console.log(response);
            $scope.imageSrc = $scope.imageSrc;
        }).error(function (response) {
            console.log('-------'+response);
        });
    }
    $http({
        url:content+'upload/findOne',
        method:'get',
        params:{
            mediaId:'1'
        }
        //http://127.0.0.1:8888/jzpz/upload/findOne?mediaId=1
    }).success(function (response) {
        $scope.imgSrc = response;
    }).error(function (response) {

    });
}]);