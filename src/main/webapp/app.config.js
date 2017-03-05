/**
 * Created by Alexandra Ale on 05.03.2017.
 */
'use strict';

angular.module('libraryApp').config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.when('/books', {
            template: '<book-list></book-list>'
        }).otherwise('/');
    }
]);
