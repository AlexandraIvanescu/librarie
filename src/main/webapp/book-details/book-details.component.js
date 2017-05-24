/**
 * Created by Alexandra Ale on 24/05/2017.
 */
'use strict';


angular.module('libraryApp').component('bookDetails', {
    templateUrl: 'book-details/book-details.template.html',
    controller: ['$mdPanel', '$rootScope', '$http', '$location', '$scope',
        function BookDetailsController($mdPanel, $rootScope, $http, $location, $scope) {

            var path = $location.path().split("/");

            $scope.bookId = path[path.length - 1];
        }]
});


