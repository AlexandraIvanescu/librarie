/**
 * Created by Alexandra Ale on 05.03.2017.
 */
'use strict';

angular.module('bookList').factory('Book', ['$resource',
    function ($resource) {
        return $resource('librarie/get/books', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);
