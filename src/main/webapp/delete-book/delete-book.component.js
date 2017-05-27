/**
 * Created by Alexandra Ale on 27/05/2017.
 */


angular.module('libraryApp').component('deleteBook', {
    templateUrl: 'delete-book/delete-book.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope',
        function DeleteBookController($scope, $location, $http, $rootScope) {

            $scope.deleteBook = function () {

                var req = {
                    method: 'DELETE',
                    dataType: 'json',
                    url: '/library/delete/book',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $rootScope.delteBook
                };

                $http(req).then(function () {

                    $location.path('/books');
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