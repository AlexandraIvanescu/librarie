/**
 * Created by Alexandra Ale on 27/05/2017.
 */

angular.module('libraryApp').component('subscriberDetails', {
    templateUrl: 'subscriber-details/subscriber-details.tempalte.html',
    controller: ['$mdPanel', '$rootScope', '$http', '$location', '$scope',
        function SubscriberDetailsController($mdPanel, $rootScope, $http, $location, $scope) {

            var path = $location.path().split("/");
            var subscriberId = path[path.length - 1];

            var url = '/library/get/subscriber/details?subscriberId=' + subscriberId;

            var req = {
                method: 'GET',
                dataType: 'json',
                url: url,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };

            $http(req).then(function (response) {
                $scope.subscriber = response.data;
            });

        }]
});