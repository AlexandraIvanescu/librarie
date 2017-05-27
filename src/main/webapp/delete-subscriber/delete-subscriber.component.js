/**
 * Created by Alexandra Ale on 27/05/2017.
 */


angular.module('libraryApp').component('deleteSubscriber', {
    templateUrl: 'delete-subscriber/delete-subscriber.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope',
        function DeleteSubscriberController($scope, $location, $http, $rootScope) {

            $scope.deleteSubscriber = function () {

                var req = {
                    method: 'DELETE',
                    dataType: 'json',
                    url: '/library/delete/subscriber',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $rootScope.deleteSubscriber
                };

                $http(req).then(function () {

                    $location.path('/subscribers');
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