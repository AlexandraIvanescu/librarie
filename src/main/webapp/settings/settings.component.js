/**
 * Created by Alexandra Ale on 27.04.2017.
 */

angular.module('libraryApp').component('settings', {
    templateUrl: 'settings/settings.template.html',
    controller: ['$scope', '$location', '$http', '$mdPanel', '$rootScope',
        function SubscribersController($scope, $location, $http, $mdPanel, $rootScope) {

            $scope.change = {};
            $scope.change.email = "biblioteca@romania.ro";
            $scope.change.name = "Biblioteca Nationala";

            $scope.addCategory = function () {

                var position = $mdPanel.newPanelPosition()
                    .absolute()
                    .center();

                var config = {
                    attachTo: angular.element(document.body),
                    template: '<add-category></add-category>',
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

            };

        }
    ]
});