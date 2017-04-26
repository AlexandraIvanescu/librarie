/**
 * Created by Alexandra Ale on 26.04.2017.
 */


angular.module('libraryApp').component('navBar', {
    templateUrl: 'nav-bar/nav-bar.template.html',
    controller: ['$scope', '$location', '$http', 'UserAuthSharedService',
        function NavBarController($scope, $location, $http, UserAuthSharedService) {

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
