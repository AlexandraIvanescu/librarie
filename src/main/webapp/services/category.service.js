/**
 * Created by Alexandra Ale on 01/05/2017.
 */


'use strict';

angular.module('libraryApp').factory('Category', ['$resource',
    function ($resource) {
        return $resource('/library/get/category', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);