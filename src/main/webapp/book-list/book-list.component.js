/**
 * Created by Alexandra Ale on 05.03.2017.
 */
'use strict';


angular.module('libraryApp').component('bookList', {
    templateUrl: 'book-list/book-list.template.html',
    controller: ['Book', 'Category', '$mdPanel', '$rootScope', '$http', '$location',
        function BookListController(Book, Category, $mdPanel, $rootScope, $http, $location) {
            var that = this;
            this.books = Book.query();
            this.categories = Category.query();

            this.categoryId = 0;
            this.title = '';
            this.author = '';

            this.newBook = function () {

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

                $rootScope.updateBook = false;

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                    $rootScope.getAllBooks = function () {
                        that.books = Book.query();
                    }
                });

            };

            this.searchBook = function () {

                var url = '/library/get/books/search?title=' + this.title + '&author=' + this.author + '&categoryId=' + this.categoryId;

                var req = {
                    method: 'GET',
                    dataType: 'json',
                    url: url,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                };

                var that = this;

                $http(req).then(function (response) {
                    that.books = response.data;
                });

            };

            this.bookDetails = function (book) {

                $location.path("/book/details/" + book.id);

            };

        }
    ]
});
