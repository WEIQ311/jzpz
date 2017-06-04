/**
 * Created by Administrator on 21:40.
 */
var app = angular.module('uploadApp',[]);
app.controller('uploadImg',['$scope','$http',function ($scope,$http) {
    $scope.errorMsg='';
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
            if(response.flag){
                $scope.getAllUploadImg();
            }
            $scope.errorTip(response.message);
        }).error(function (response) {
            $scope.errorTip(response.message);
        });
    }
    $scope.getAllUploadImg = function () {
        $http({
            url:content+'upload/getAll',
            method:'get'
        }).success(function (response) {
            $scope.uploadImgs = response.data;
        }).error(function (response) {
            $scope.errorTip(response);
        });
    }
    $scope.showImg=function (img) {
        $scope.showImgeId=img.mediaId;
        $scope.showImage=img;
    }
    $scope.clickImge=function (id) {
        return angular.equals(id,$scope.showImgeId);
    }
    $scope.errorTip=function (msg) {
        $scope.errorMsg = msg;
        $('#myModalShowMsg').modal('toggle');
    }
    $scope.getAllUploadImg();
}]);