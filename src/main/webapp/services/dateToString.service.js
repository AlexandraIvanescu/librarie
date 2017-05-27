/**
 * Created by Alexandra Ale on 27/05/2017.
 */
'use strict';

angular.module('libraryApp').service('DateToStringService', function () {

    var dateToString = function (date) {
        var mm = date.getMonth() + 1;
        var dd = date.getDate();

        return [(dd > 9 ? '' : '0') + dd,
            (mm > 9 ? '' : '0') + mm,
            date.getFullYear()
        ].join('-');
    };

    return {
        dateToString: dateToString
    };

});