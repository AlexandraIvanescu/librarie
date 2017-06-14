/**
 * Created by Alexandra Ale on 13/06/2017.
 */

angular.module('libraryApp').component('extendedBorrow', {
    templateUrl: 'extended-borrow/extended-borrow.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope', '$route',
        function ExtendedBorrowController($scope, $location, $http, $rootScope, $route) {

            $scope.extendBorrow = function () {

                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/extended/borrow',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $rootScope.borrow
                };

                $http(req).then(function () {
                    $route.reload();
                    $scope.closeDialog();

                });

            };

            $scope.closeDialog = function () {
                $rootScope.panelRef && $rootScope.panelRef.close().then(function () {
                    angular.element(document.querySelector('.dialog-button')).focus();
                    $rootScope.panelRef.destroy();
                });
            }

        }]
});