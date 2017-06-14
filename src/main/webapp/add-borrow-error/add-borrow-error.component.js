/**
 * Created by Alexandra Ale on 14/06/2017.
 */


angular.module('libraryApp').component('addBorrowError', {
    templateUrl: 'add-borrow-error/add-borrow-error.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope',
        function AddBorrowErrorController($scope, $location, $http, $rootScope) {

            $scope.closeDialog = function () {
                $rootScope.panelRef && $rootScope.panelRef.close().then(function () {
                    angular.element(document.querySelector('.dialog-button')).focus();
                    $rootScope.panelRef.destroy();
                });
            }

        }]
});