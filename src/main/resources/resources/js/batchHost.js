var app = angular.module('batchHostApp', []);
app.controller('batchHostCtr', ['$scope', '$http', function ($scope, $http) {
    /**
     * 重置搜索框
     */
    $scope.resetHost = function () {
        $scope.hostIp = '';
        $scope.hostName = '';
        $scope.hostList = new Array();
        $scope.batchHostParam();
        $scope.searchHost();
    };
    $scope.batchHostParam = function () {
        $scope.ip = '';
        $scope.num1 = '';
        $scope.num2 = '';
        $scope.name1 = '';
        $scope.name2 = '';
        $scope.userName = '';
        $scope.passWord = '';
        $scope.port = '';
        $scope.remark = '';
    };

    /**
     * 搜索主机
     */
    $scope.searchHost = function () {
        $http({
            url: content + 'hostInfo/searchHost',
            method: 'POST',
            params: {
                hostIp: $scope.hostIp,
                hostName: $scope.hostName
            },
            headers: contentType
        }).success(function (response) {
            $scope.hostList = response.data;
        }).error(function (response) {
            $scope.errorTip(response.message);
        });
    };
    $scope.showBatchHost = function () {
        $('#showBatchHostWin').modal('toggle');
    };
    /**
     * 预览批量主机
     */
    $scope.previewHost = function () {
        $scope.batchHost(true);
    };
    /**
     * 批量增加
     */
    $scope.batchHost = function (isPreview) {
        $scope.isPriview = isPreview;
        $http({
            url: content + 'hostInfo/batchHost',
            method: 'POST',
            params: {
                ip: $scope.ip,
                num1: $scope.num1,
                num2: $scope.num2,
                name1: $scope.name1,
                name2: $scope.name2,
                userName: $scope.userName,
                passWord: $scope.passWord,
                port: $scope.port,
                remark: $scope.remark,
                preview:isPreview
            },
            headers: contentType
        }).success(function (response) {
            if(isPreview){
                $scope.preHostList = response.data;
            }
            if (response.flag&&!isPreview) {
                $scope.showBatchHost();
                $scope.resetHost();
            }
            if(!response.flag){
                $scope.errorTip(response.message);
            }
        }).error(function (response) {
            $scope.errorTip(response.message);
        });
    };
    $scope.showPreview = function () {
        return $scope.isPriview;
    }
    /**
     * 错误信息
     * @param msg
     */
    $scope.errorTip = function (msg) {
        $scope.errorMessage = msg;
        $('#errorMessageWin').modal('toggle');
    };
    $scope.isEven=function (index) {
        return index%2==1;
    }
    $scope.resetHost();
}]);