/**
 * Created by weiQiang on 12:45.
 */
var app = angular.module('createTable',[]);
app.controller('createTableCtr',['$scope','$http',function ($scope,$http) {

    $scope.columnTypes=[
        {name:'long'},
        {name:'char'},
        {name:'varchar'},
        {name:'clob'},
        {name:'date'}
        ]
    $scope.initTable=function () {
        $scope.errorMessage='';
        $scope.columns=[];
        $scope.tableName='';
        $scope.oneColumn();
    }
    //判断奇偶
    $scope.isEven=function (index) {
        return index%2==1;
    }
    //当是第一条或者最后一条数据时显示增加按钮
    $scope.isFirstOrLastColumn=function (index) {
        if($scope.columns.length==1|| $scope.columns.length==index)
            return true;
        else
            return false;
    }
    //添加一行
    $scope.addRow=function(){
        $scope.oneColumn();
    }
    //删除一行
    $scope.removeRow=function (index) {
        if($scope.columns.length==1)
            return false;
        else
            $scope.columns.splice(index,1);
    }

    $scope.oneColumn=function () {
        $scope.columns.push({
            columnChnName:'',
            columnEngName:'',
            columnType:$scope.columnTypes[1].name,
            columnLength:11,
            tableId:'',
            isPrimary:true,
            isUnique:true,
            isEmpty:false
        });
    }
    $scope.createTable=function () {
        $http({
            url:content+'table/create',
            method: 'POST',
            params: {
                tableName:$scope.tableName,
                columns:angular.toJson($scope.columns)
            },
            headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).success(function(responses,status, nativeStatusText, headers){
            if(responses.flag){
                $scope.initTable();
            }
            $scope.errorMessage = responses.message;
        }).error(function(responses,status, nativeStatusText, headers){
            $scope.errorMessage = '服务器异常';
            /**
            $('.container')
                .modal("show")
                .find(".modal-body")
                .empty()
                .html("<p>异常<br>服务器异常</p>");*/
        });
    }
    //初始化/重置
    $scope.initTable();
}]);
