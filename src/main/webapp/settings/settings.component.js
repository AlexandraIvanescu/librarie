/**
 * Created by Alexandra Ale on 27.04.2017.
 */

angular.module('libraryApp').component('settings', {
    templateUrl: 'settings/settings.template.html',
    controller: ['$scope', '$location', '$http', '$mdPanel', '$rootScope', '$route',
        function SubscribersController($scope, $location, $http, $mdPanel, $rootScope, $route) {

            function getCategory() {

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: '/library/get/category',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    $scope.categories = response.data;
                });

            }

            function getAccount() {

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: '/library/get/account',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    $scope.account = response.data;
                });

            }

            getCategory();
            getAccount();

            $scope.addCategory = function () {

                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<add-category></add-category>',
                    hasBackdrop: true,
                    panelClass: 'new-post',
                    position: position,
                    clickOutsideToClose: true,
                    escapeToClose: true,
                    disableParentScroll: true,
                    trapFocus: true
                };

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                });

            };

            $scope.updateAccount = function () {
                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/update/account',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $scope.account
                };

                $http(req).then(function (response) {
                    $route.reload();
                });
            };

            $scope.changePassword = function () {
                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/update/password',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: {"oldPassword": $scope.oldPassword, "newPassword": $scope.newPassword}
                };

                $http(req);

            };

        }
    ]
});