/**
 * Created by Alexandra Ale on 05.03.2017.
 */
'use strict';


angular.module('libraryApp').component('bookList', {
    templateUrl: 'book-list/book-list.template.html',
    controller: ['Book', '$mdPanel', '$rootScope',
        function BookListController(Book, $mdPanel, $rootScope) {
            this.books = Book.query();

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

                $mdPanel.open(config).then(function (result) {
                    $rootScope.panelRef = result;
                });

            }
        }
    ]
});
