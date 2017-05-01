/**
 * Created by Alexandra Ale on 05.03.2017.
 */
'use strict';

angular.module('libraryApp').factory('Book', ['$resource',
    function ($resource) {
        return $resource('/library/get/books', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);
