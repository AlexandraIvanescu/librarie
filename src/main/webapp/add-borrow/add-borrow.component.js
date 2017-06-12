/**
 * Created by Alexandra Ale on 11/06/2017.
 */

angular.module('libraryApp').component('addBorrow', {
    templateUrl: 'add-borrow/add-borrow.template.html',
    controller: ['$scope', '$http', '$rootScope', '$route',
        function AddBorrowController($scope, $http, $rootScope, $route) {

            var req = {
                method: 'GET',
                dataType: 'json',
                url: '/library/get/books/not-borrowed',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };

            $http(req).then(function (response) {
                $scope.books = response.data;
            });

            $scope.closeDialog = function closePoPup() {
                $rootScope.panelRef && $rootScope.panelRef.close().then(function () {
                    angular.element(document.querySelector('.dialog-button')).focus();
                    $rootScope.panelRef.destroy();
                });
            };

            $scope.saveBorrow = function () {

                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/add/borrow',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: {subscriber: $rootScope.subscriber, bookId: $scope.bookId}
                };

                $http(req).then(function (response) {
                    $route.reload();
                    $scope.closeDialog();
                });

            };

        }
    ]
});