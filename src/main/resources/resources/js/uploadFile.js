/**
 * Created by Administrator on 21:40.
 */
var app = angular.module('uploadApp',[]);
app.controller('uploadImg',['$scope','$http',function ($scope,$http) {
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
            $scope.imageSrc = response.message;
        }).error(function (response) {
            console.log('-------'+response);
        });
    }
    $http({
        url:content+'upload/getAll',
        method:'get'
    }).success(function (response) {
        $scope.uploadImgs = response.data;
    }).error(function (response) {

    });
}]);