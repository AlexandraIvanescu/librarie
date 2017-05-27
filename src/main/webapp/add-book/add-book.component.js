/**
 * Created by Alexandra Ale on 01/05/2017.
 */

angular.module('libraryApp').component('addBook', {
    templateUrl: 'add-book/add-book.template.html',
    controller: ['$scope', '$location', '$http', '$rootScope', 'Category',
        function AddBookController($scope, $location, $http, $rootScope, Category) {
            var currentDate = new Date();

            $scope.maxDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
            $scope.book = {};
            $scope.categories = Category.query();
            $scope.windowTitle = 'Adaugati o noua carte';

            $scope.closeDialog = function () {
                closePoPup();
            };

            if ($rootScope.updateBook) {
                $scope.book = $rootScope.book;
                $scope.book.releaseDate = new Date($rootScope.book.releaseDate);
                $scope.windowTitle = 'Actualizati cartea';
                $scope.book.categoryIndex = $scope.book.category.id - 1;
            }


            var updateBook = function () {
                $scope.book.category = $scope.categories[$scope.book.categoryIndex];

                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/add/book',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $scope.book
                };

                $http(req).then(function () {
                    closePoPup();
                });

            };

            var newBook = function () {

                $scope.book.category = $scope.categories[$scope.book.categoryIndex];
                $scope.book.image = $scope.bookPicture.name;

                var req = {
                    method: 'POST',
                    dataType: 'json',
                    url: '/library/add/book',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    data: $scope.book
                };

                $http(req).then(function () {

                    $http({
                        method: 'POST',
                        url: "/library/add/picture",
                        headers: {'Content-Type': undefined},
                        data: {picture: $scope.bookPicture},
                        transformRequest: function (data) {
                            var formData = new FormData();

                            formData.append("picture", data.picture);

                            return formData;
                        }
                    }).then(function () {
                        closePoPup();
                    });
                });
            };


            function closePoPup() {
                $rootScope.panelRef && $rootScope.panelRef.close().then(function () {

                    if ($rootScope.updateBook) {
                        $rootScope.getBook();
                    } else {
                        $rootScope.getAllBooks();
                    }

                    angular.element(document.querySelector('.dialog-button')).focus();
                    $rootScope.panelRef.destroy();
                });
            }

            $scope.saveBook = function () {
                if ($rootScope.updateBook) {
                    updateBook();
                } else {
                    newBook();
                }
            };

        }
    ]
});