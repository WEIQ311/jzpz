// var mainApp = angular.module('index',['ngRoute','ngAnimate','index']);
// 
// mainApp.config(function ($routeProvider){
	// //路由功能
	// $routeProvider.when('/hello',{
		// templateUrl:'index.html',
		// controller :['GreenCtrl']
	// }).when('/test',{
		// templateUrl:'Test.html',
		// controller:'greenCtrl'
	// }).otherwise({
		// redirectTo:'/hello'
	// });
// });

var test04App = angular.module('Test04', [
    'ngRoute', 'ngAnimate', 'bookStoreCtrls', 'bookStoreFilters',
    'bookStoreServices', 'bookStoreDirectives'
]);

test04App.config(function($routeProvider) {
    $routeProvider.when('/hello', {
        templateUrl: 'tpls/hello.html',
        controller: 'HelloCtrl'
    }).when('/list', {
        templateUrl: 'tpls/bookList.html',
        controller: 'BookListCtrl'
    }).otherwise({
        redirectTo: '/hello'
    });
});

