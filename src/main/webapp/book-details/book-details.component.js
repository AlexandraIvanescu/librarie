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

                $scope.book.releaseDate = DateToStringService.dateToString(releaseDate);

            });

        }]
});


