/**
 * Created by Alexandra Ale on 27.04.2017.
 */

angular.module('libraryApp').component('settings', {
    templateUrl: 'settings/settings.template.html',
    controller: ['$scope', '$location', '$http',
        function SubscribersController($scope, $location, $http) {

            $scope.change = {};
            $scope.change.email = "biblioteca@romania.ro";
            $scope.change.name = "Biblioteca Nationala";

        }
    ]
});