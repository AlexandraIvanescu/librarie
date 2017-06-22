/**
 * Created by Alexandra Ale on 24/05/2017.
 */

'use strict';


angular.module('libraryApp').component('bookDetails', {
    templateUrl: 'book-details/book-details.template.html',
    controller: ['$mdPanel', '$rootScope', '$http', '$location', '$scope', 'DateToStringService',
        function BookDetailsController($mdPanel, $rootScope, $http, $location, $scope, DateToStringService) {

            var path = $location.path().split("/");
            var bookId = path[path.length - 1];
            $scope.currentDate = new Date();


            var getBook = function () {

                var url = '/library/get/book/details?bookId=' + bookId;

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: url,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                $http(req).then(function (response) {
                    $scope.book = response.data;

                    var releaseDate = new Date($scope.book.releaseDate);

                    $scope.book.releaseDateToString = DateToStringService.dateToString(releaseDate);

                });

            };

            var getBorrow = function () {

                var url = '/library/get/book/borrow?bookId=' + bookId;

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
                        borrow.startDateToString = DateToStringService.dateToString(new Date(borrow.startDate));
                        borrow.endDateToString = DateToStringService.dateToString(new Date(borrow.endDate));
                    });

                });

            };

            getBook();
            getBorrow();

            $scope.updateBook = function () {

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

                $rootScope.updateBook = true;
                $rootScope.book = $scope.book;

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                    $rootScope.getBook = getBook;
                });

            };


            $scope.deleteBook = function () {

                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<delete-book></delete-book>',
                    hasBackdrop: true,
                    panelClass: 'new-post',
                    position: position,
                    clickOutsideToClose: true,
                    escapeToClose: true,
                    disableParentScroll: true,
                    trapFocus: true
                };

                $rootScope.delteBook = $scope.book;

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                });

            };

        }]
});


