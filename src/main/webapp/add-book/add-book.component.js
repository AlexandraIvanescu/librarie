/**
 * Created by Alexandra Ale on 01/05/2017.
 */

angular.module('libraryApp').component('addBook', {
    templateUrl: 'add-book/add-book.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope','Category',
        function AddBookController($scope, $location, $http, $rootScope, Category) {
            var currentDate = new Date();

            $scope.maxDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
            $scope.book = {};
            $scope.categories = Category.query();

            $scope.closeDialog = function () {
                $rootScope.panelRef && $rootScope.panelRef.close().then(function () {
                    angular.element(document.querySelector('.dialog-button')).focus();
                    $rootScope.panelRef.destroy();
                });
            };

            $scope.newBook = function () {

            }

        }
    ]
});