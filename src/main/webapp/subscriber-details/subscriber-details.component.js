/**
 * Created by Alexandra Ale on 27/05/2017.
 */

angular.module('libraryApp').component('subscriberDetails', {
    templateUrl: 'subscriber-details/subscriber-details.tempalte.html',
    controller: ['$mdPanel', '$rootScope', '$http', '$location', '$scope', 'DateToStringService',
        function SubscriberDetailsController($mdPanel, $rootScope, $http, $location, $scope, DateToStringService) {

            var path = $location.path().split("/");
            var subscriberId = path[path.length - 1];
            $scope.currentDate = new Date();


            var getSubscriber = function () {
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
            };

            var getBorrow = function () {

                var url = '/library/add/borrow?subscriberId=' + subscriberId;

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: url,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    $scope.borrows = response.data;

                    $scope.borrows.forEach(function (borrow) {
                        borrow.startDate = DateToStringService.dateToString(new Date(borrow.borrow.startDate));
                        borrow.endDate = DateToStringService.dateToString(new Date(borrow.borrow.endDate));
                    });

                });

            };

            getSubscriber();
            getBorrow();

            $scope.updateSubscriber = function () {

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

                $rootScope.updateSubscriber = true;
                $rootScope.subscriber = $scope.subscriber;

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                    $rootScope.getSubscriber = getSubscriber;
                });

            };


            $scope.deleteSubscriber = function () {

                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<delete-subscriber></delete-subscriber>',
                    hasBackdrop: true,
                    panelClass: 'new-post',
                    position: position,
                    clickOutsideToClose: true,
                    escapeToClose: true,
                    disableParentScroll: true,
                    trapFocus: true
                };

                $rootScope.deleteSubscriber = $scope.subscriber;

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                });

            };

            $scope.addBorrow = function () {

                var url = '/library/has/borrow?subscriberId=' + subscriberId;

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: url,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    var config;
                    var position = $mdPanel.newPanelPosition()
                        .absolute()
                        .center();

                    if (response.data) {

                        config = {
                            attachTo: angular.element(document.body),
                            template: '<add-borrow></add-borrow>',
                            hasBackdrop: true,
                            panelClass: 'new-post',
                            position: position,
                            clickOutsideToClose: true,
                            escapeToClose: true,
                            disableParentScroll: true,
                            trapFocus: true
                        };

                        $rootScope.subscriber = $scope.subscriber;

                    } else {

                        config = {
                            attachTo: angular.element(document.body),
                            template: '<add-borrow-error></add-borrow-error>',
                            hasBackdrop: true,
                            panelClass: 'new-post',
                            position: position,
                            clickOutsideToClose: true,
                            escapeToClose: true,
                            disableParentScroll: true,
                            trapFocus: true
                        };

                    }

                    $mdPanel.open(config).then(function (result) {
                        $rootScope.panelRef = result;
                    });

                });

            };

            $scope.extended = function (borrow) {

                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<extended-borrow></extended-borrow>',
                    hasBackdrop: true,
                    panelClass: 'new-post',
                    position: position,
                    clickOutsideToClose: true,
                    escapeToClose: true,
                    disableParentScroll: true,
                    trapFocus: true
                };

                $rootScope.borrow = borrow;

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                });

            };

            $scope.deleteBorrow = function (borrow) {

                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<delete-borrow></delete-borrow>',
                    hasBackdrop: true,
                    panelClass: 'new-post',
                    position: position,
                    clickOutsideToClose: true,
                    escapeToClose: true,
                    disableParentScroll: true,
                    trapFocus: true
                };

                $rootScope.borrow = borrow;

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                });

            };


            $scope.title = "";
            $scope.author = "";
            $scope.startDate = "";
            $scope.endDate = "";

            $scope.searchBorrow = function () {

                var startDate = "";
                var endDate = "";

                if ($scope.startDate != "") {
                    startDate = DateToStringService.dateToString(new Date($scope.startDate));
                }

                if ($scope.endDate != "") {
                    endDate = DateToStringService.dateToString(new Date($scope.endDate));
                }

                var url = '/library/search/borrow?title=' + $scope.title + '&author=' + $scope.author + '&startDate=' + startDate + '&endDate=' + endDate;

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: url,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    $scope.borrows = response.data;

                    $scope.borrows.forEach(function (borrow) {
                        borrow.startDate = DateToStringService.dateToString(new Date(borrow.borrow.startDate));
                        borrow.endDate = DateToStringService.dateToString(new Date(borrow.borrow.endDate));
                    });
                });

            };

        }]
});