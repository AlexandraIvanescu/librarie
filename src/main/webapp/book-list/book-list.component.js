/**
 * Created by Alexandra Ale on 05.03.2017.
 */
'use strict';


angular.module('libraryApp').component('bookList', {
    templateUrl: 'book-list/book-list.template.html',
    controller: ['Book',
        function BookListController(Book) {
            this.books = Book.query();
        }
    ]
});
