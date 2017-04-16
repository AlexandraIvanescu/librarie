/**
 * Created by Alexandra Ale on 16.04.2017.
 */

'use strict';

angular.module('libraryApp').component('register', {
    templateUrl: 'register/register.template.html',
    controller: ['$scope', '$rootScope', '$http', '$location',
        function RegisterCtrl($scope, $rootScope, $http, $location) {
            $scope.user = {};

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
                            $location.path('/login').replace();
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
