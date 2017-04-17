/**
 * Created by Alexandra Ale on 12.03.2017.
 */
'use strict';

angular.module('libraryApp').component('login', {
    templateUrl: 'login/login.template.html',
    controller: ['$scope', '$rootScope', '$http', '$location', 'UserAuthSharedService',
        function LoginController($scope, $rootScope, $http, $location, UserAuthSharedService) {
            $scope.user = {};
            $scope.loginCredentials = {};

            $scope.userLogin = function () {
                $rootScope.userAuthenticationError = false;
                UserAuthSharedService.login($scope.loginCredentials.email, $scope.loginCredentials.password);
            };

            $scope.navigateTo = function (newPath) {
                $location.path(newPath).replace();
            };

            $scope.newAccount = function () {
                if ($scope.user.password === $scope.user.repeatPassword) {

                    var req = {
                        method: 'POST',
                        dataType: 'json',
                        url: '/register/user',
                        headers: {
                            'Content-Type': 'application/json; charset=utf-8'
                        },
                        data: $scope.user
                    };

                    $http(req).then(function (response) {

                        if (response.data.isCreated) {
                            $rootScope.isCreated = true;
                        } else {
                            $scope.error = true;
                            $scope.errorMessage = $scope.user.email + " already exist !";
                        }

                    }, function () {
                        $scope.error = true;
                        $scope.errorMessage = "A problem has happened during recording. Please try again.";
                    });
                }
            };
        }]
});

