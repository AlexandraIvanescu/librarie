/**
 * Created by Alexandra Ale on 27.04.2017.
 */

angular.module('libraryApp').component('subscribers', {
    templateUrl: 'subscribers/subscribers.template.html',
    controller: ['$scope', '$location', '$http', 'Subscriber',
        function SubscribersController($scope, $location, $http, Subscriber) {

            $scope.subscribers = Subscriber.query();

        }
    ]
});