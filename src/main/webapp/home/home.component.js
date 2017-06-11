/**
 * Created by Alexandra Ale on 17.04.2017.
 */


angular.module('libraryApp').component('home', {
    templateUrl: 'home/home.template.html',
    controller: ['$scope', '$location',
        function HomeController($scope) {

            $scope.labels_book = ["Numar de carti", "Carti imprumutate", "Carti ce trebuiesc returnate"];
            $scope.data_book = [1000, 500, 105];

            $scope.labels_subscriber = ["Numar de abonati", "Abonati cu carti imprumutate", "Abonati ce trebuie sa returneze carti"];
            $scope.data_subscriber = [200, 75, 10];

        }
    ]
});