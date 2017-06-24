/**
 * Created by Alexandra Ale on 24/06/2017.
 */

angular.module('libraryApp').component('addCategory', {
    templateUrl: 'add-category/add-category.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope', '$route',
        function AddCategoryController($scope, $location, $http, $rootScope, $route) {
            $scope.category = {};

            $scope.saveCategory = function () {

                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/add/category',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $scope.category
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
        }
    ]
});