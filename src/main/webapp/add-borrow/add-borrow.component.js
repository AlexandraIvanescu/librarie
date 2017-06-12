/**
 * Created by Alexandra Ale on 11/06/2017.
 */

angular.module('libraryApp').component('addBorrow', {
    templateUrl: 'add-borrow/add-borrow.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope',
        function AddBorrowController($scope, $location, $http, $rootScope) {


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

            };

        }
    ]
});