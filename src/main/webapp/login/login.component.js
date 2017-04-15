/**
 * Created by Alexandra Ale on 12.03.2017.
 */
'use strict';

angular.module('libraryApp').component('login', {
    templateUrl: 'login/login.template.html',
    controller: ['$scope', '$rootScope', '$http', '$location', 'UserAuthSharedService',
        function LoginController($scope, $rootScope, $http, $location, UserAuthSharedService) {
            $scope.user = {};

            $scope.userLogin = function () {
                $rootScope.userAuthenticationError = false;
                UserAuthSharedService.login($scope.user.email, $scope.user.password);
            };

            $scope.navigateTo = function (newPath) {
                $location.path(newPath).replace();
            };

        }]
});

