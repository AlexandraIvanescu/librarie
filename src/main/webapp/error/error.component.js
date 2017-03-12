/**
 * Created by Alexandra Ale on 12.03.2017.
 */
'use strict';


angular.module('error').component('error', {
    templateUrl: 'error/error.template.html',
    controller: ['$scope', '$routeParams',
        function ErrorController($scope, $routeParams) {
            $scope.code = $routeParams.code;

            switch ($scope.code) {
                case "403" :
                    $scope.message = "Oops! you have come to unauthorised page.";
                    break;
                case "404" :
                    $scope.message = "Page not found.";
                    break;
                default:
                    $scope.code = 500;
                    $scope.message = "Oops! unexpected error";
            }
        }
    ]
});