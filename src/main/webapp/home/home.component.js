/**
 * Created by Alexandra Ale on 17.04.2017.
 */


angular.module('libraryApp').component('home', {
    templateUrl: 'home/home.template.html',
    controller: ['$scope', '$location', '$http',
        function HomeController($scope, $location, $http) {

            $scope.labels_book = ["Numar de carti", "Carti imprumutate", "Carti ce trebuiesc returnate"];

            function getBookStatistics() {

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: '/library/book/statistics',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    $scope.data_book = response.data;
                });

            }

            getBookStatistics();

            $scope.labels_subscriber = ["Numar de abonati", "Abonati cu carti imprumutate", "Abonati ce trebuie sa returneze carti"];

            function getSubscriberStatistics() {

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: '/library/subscriber/statistics',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    $scope.data_subscriber = response.data;
                });

            }

            getSubscriberStatistics();

        }
    ]
});