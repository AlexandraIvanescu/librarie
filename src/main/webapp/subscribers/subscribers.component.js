/**
 * Created by Alexandra Ale on 27.04.2017.
 */

angular.module('libraryApp').component('subscribers', {
    templateUrl: 'subscribers/subscribers.template.html',
    controller: ['$scope', '$location', '$http', '$mdPanel', '$rootScope', 'Subscriber',
        function SubscribersController($scope, $location, $http, $mdPanel, $rootScope, Subscriber) {

            $scope.subscribers = Subscriber.query();
            $scope.lastName = '';
            $scope.firstName = '';

            $scope.newSubscribers = function () {
                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<add-subscriber></add-subscriber>',
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
                    $rootScope.getAllSubscriber = function () {
                        $scope.subscribers = Subscriber.query();
                    }
                });

            };

            $scope.searchSubscribers = function () {

                var url = '/library/get/subscribers/search?lastName=' + $scope.lastName + '&firstName=' + $scope.firstName;

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: url,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                var that = this;

                $http(req).then(function (response) {
                    that.subscribers = response.data;
                });

            }

        }
    ]
});