/**
 * Created by Alexandra Ale on 17.04.2017.
 */


angular.module('libraryApp').component('home', {
    templateUrl: 'home/home.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope', '$mdPanel',
        function HomeController($scope, $location, $http, $rootScope, $mdPanel) {

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

            $scope.newBook = function () {
                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<add-book></add-book>',
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
                });

                $location.path('/books');

            };

            $scope.newSubscriber = function () {
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
                });

                $location.path('/subscribers');

            };


            $scope.newCategory = function () {
                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<add-category></add-category>',
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
                });

                $location.path('/settings');

            };

        }
    ]
});