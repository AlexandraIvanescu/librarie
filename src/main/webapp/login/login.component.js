/**
 * Created by Alexandra Ale on 12.03.2017.
 */
'use strict';

angular.module('login').component('login', {
    templateUrl: 'login/login.template.html',
    controller: ['$scope', '$rootScope', '$http', '$location', 'UserAuthSharedService', 'AdminAuthSharedService',
        function LoginController($scope, $rootScope, $http, $location, UserAuthSharedService, AdminAuthSharedService){
            $scope.user = {};
            $scope.admin = {};

            $scope.userLogin = function () {
                $rootScope.userAuthenticationError = false;
                userAuthSharedService.login($scope.user.email, $scope.user.password);
            };

            $scope.adminLogin = function () {
                $rootScope.adminAuthenticationError = false;
                AdminAuthSharedService.login($scope.admin.email, $scope.admin.password);
            };

            $scope.navigateTo = function (newPath) {
                $location.path(newPath).replace();
            };

        }]});

