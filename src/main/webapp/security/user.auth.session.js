/**
 * Created by Alexandra Ale on 12.03.2017.
 */
angular.module('libraryApp').service('UserAuthSharedService', function ($rootScope, $http, authService, UserSession, localStorageService) {
    return {
        login: function (email, password) {

            var config = {
                ignoreAuthModule: 'ignoreAuthModule',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };
            $http.post('/user/login', $.param({
                email: email,
                password: password
            }), config)
                .then(function (response) {
                    localStorageService.set('isUser', true);
                    localStorageService.set('isAdmin', false);

                    authService.loginConfirmed(response.data);
                }).catch(function () {

                $rootScope.userAuthenticationError = true;
                UserSession.invalidate();
            });

        },

        getAccount: function () {
            $rootScope.loadingAccount = true;
            $http.get('/user/account')
                .then(function (response) {
                    authService.loginConfirmed(response.data);
                });
        },

        isAuthorized: function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                if (authorizedRoles == '*') {
                    return true;
                }
                authorizedRoles = [authorizedRoles];
            }
            var isAuthorized = false;
            angular.forEach(authorizedRoles, function (authorizedRole) {
                var authorized = (!!UserSession.email &&
                UserSession.userRoles.indexOf(authorizedRole) !== -1);
                if (authorized || authorizedRole == '*') {
                    isAuthorized = true;
                }
            });
            return isAuthorized;
        },

        logout: function () {
            $rootScope.userAuthenticationError = false;
            $rootScope.authenticated = false;
            $rootScope.account = null;
            localStorageService.remove('isUser');
            localStorageService.remove('isAdmin');
            UserSession.invalidate();
            authService.loginCancelled();
        }
    };
});