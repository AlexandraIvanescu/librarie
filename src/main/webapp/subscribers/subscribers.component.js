/**
 * Created by Alexandra Ale on 27.04.2017.
 */

angular.module('libraryApp').component('subscribers', {
    templateUrl: 'subscribers/subscribers.template.html',
    controller: ['$scope', '$location', '$http', 'Subscriber',
        function SubscribersController($scope, $location, $http, Subscriber) {

            $scope.subscribers = Subscriber.query();
            $scope.lastName = '';
            $scope.firstName = '';

            $scope.newSubscribers = function () {
                //TODO
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