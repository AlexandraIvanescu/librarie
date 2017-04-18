/**
 * Created by Alexandra Ale on 17.04.2017.
 */


angular.module('libraryApp').component('home', {
    templateUrl: 'home/home.template.html',
    controller: ['$scope', '$location', '$http', 'UserAuthSharedService',
        function HomeController($scope, $location, $http, UserAuthSharedService) {

            $scope.goTo = function (newpath) {
                $location.path(newpath);
            };

            $scope.logout = function () {
                $http.post('/user/logout', {}).finally(function () {
                    UserAuthSharedService.logout();
                });
            };

        }
    ]
});