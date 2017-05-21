/**
 * Created by Alexandra Ale on 21/05/2017.
 */


angular.module('libraryApp').component('addSubscriber', {
    templateUrl: 'add-subscriber/add-subscriber.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope',
        function AddSubscriberController($scope, $location, $http, $rootScope) {
            $scope.subscriber = {};

            $scope.closeDialog = function () {
                closePoPup();
            };

            $scope.newSubscriber = function () {

                $scope.subscriber.image = $scope.subscriberPicture.name;

                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/add/subscriber',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $scope.subscriber
                };

                $http(req).then(function () {

                    $http({
                        method: 'POST',
                        url: "/library/add/subscriber/picture",
                        headers: {'Content-Type': undefined},
                        data: {picture: $scope.subscriberPicture},
                        transformRequest: function (data) {
                            var formData = new FormData();

                            formData.append("picture", data.picture);

                            return formData;
                        }
                    }).then(function () {
                        closePoPup();
                    });
                });
            };


            function closePoPup() {
                $rootScope.panelRef && $rootScope.panelRef.close().then(function () {
                    $rootScope.getAllSubscriber();
                    angular.element(document.querySelector('.dialog-button')).focus();
                    $rootScope.panelRef.destroy();
                });
            }
        }
    ]
});