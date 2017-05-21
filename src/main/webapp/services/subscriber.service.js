/**
 * Created by Alexandra Ale on 18/05/2017.
 */
'use strict';

angular.module('libraryApp').factory('Subscriber', ['$resource',
    function ($resource) {
        return $resource('/library/get/subscribers', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);
