/**
 * Created by Alexandra Ale on 17.04.2017.
 */


angular.module('libraryApp').component('home', {
    templateUrl: 'home/home.template.html',
    controller: ['$scope', '$location',
        function HomeController($scope, $location) {

            $scope.goTo = function (newpath) {
                $location.path(newpath);
            }

        }
    ]
});