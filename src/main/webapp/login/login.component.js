/**
 * Created by Alexandra Ale on 12.03.2017.
 */
'use strict';

angular.module('libraryApp').component('login', {
    templateUrl: 'login/login.template.html',
    controller: ['$scope', '$rootScope', '$http', '$location', 'UserAuthSharedService',
        function LoginController($scope, $rootScope, $http, $location, UserAuthSharedService) {
            $scope.library = {};
            $scope.loginCredentials = {};

            $scope.userLogin = function () {
                $rootScope.userAuthenticationError = false;
                UserAuthSharedService.login($scope.loginCredentials.email, $scope.loginCredentials.password);
            };

            $scope.navigateTo = function (newPath) {
                $location.path(newPath).replace();
            };

            $scope.newAccount = function () {
                if ($scope.library.password === $scope.library.repeatPassword) {

                    var req = {
                        method: 'POST',
                        dataType: 'json',
                        url: '/register',
                        headers: {
                            'Content-Type': 'application/json; charset=utf-8'
                        },
                        data: $scope.library
                    };

                    $http(req).then(function (response) {

                        if (response.data.isCreated) {
                            $rootScope.isCreated = true;
                            $scope.isCreated = true;
                        } else {
                            $scope.error = true;
                            $scope.errorMessage = $scope.library.email + " exista deja !";
                        }

                    }, function () {
                        $scope.error = true;
                        $scope.errorMessage = "O problema s-a inregistrat pe parcursul inregistrarii. Va rugam sa incercati din nou !";
                    });
                }
            };
        }]
});

