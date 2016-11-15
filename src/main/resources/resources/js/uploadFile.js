/**
 * Created by Administrator on 21:40.
 */
var app = angular.module('uploadApp',[]);
app.controller('uploadImg',['$scope','$http',function ($scope,$http) {
    $scope.imageSrc = 'd://123.jpg';
    $scope.uploadImg = function () {
        console.log($scope.uploadImg);
        $http({
            url:content+'upload/img',
            method: 'POST',
            headers: {'Content-Type':undefined},
            params:{
                'files':$scope.uploadImg
            },
            transformRequest: angular.identity
        }).success(function (response) {
            console.log(response);
            $scope.imageSrc = response;
        }).error(function (response) {
            console.log('-------'+response);
        });
    }

}]);