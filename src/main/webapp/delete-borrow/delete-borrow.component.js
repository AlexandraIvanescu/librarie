/**
 * Created by Alexandra Ale on 13/06/2017.
 */


angular.module('libraryApp').component('deleteBorrow', {
    templateUrl: 'delete-borrow/delete-borrow.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope', '$route',
        function DeleteBorrowController($scope, $location, $http, $rootScope, $route) {

            $scope.deleteBorrow = function () {

                var req = {
                    method: 'DELETE',
                    dataType: 'json',
                    url: '/library/delete/borrow',
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