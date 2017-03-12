/**
 * Created by Alexandra Ale on 12.03.2017.
 */
angular.module('libraryApp').service('AdminAuthSharedService', function ($rootScope, $http, authService, AdminSession, localStorageService) {
    return {
        login: function (email, password) {

            var config = {
                ignoreAuthModule: 'ignoreAuthModule',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };
            $http.post('/admin/login', $.param({
                email: email,
                password: password
            }), config)
                .then(function (response) {
                    localStorageService.set('isAdmin', true);
                    localStorageService.set('isUser', false);

                    authService.loginConfirmed(response.data);
                }).catch(function () {

                $rootScope.adminAuthenticationError = true;
                AdminSession.invalidate();
            });

        },

        getAccount: function () {
            $rootScope.loadingAccount = true;
            $http.get('/admin/account')
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
                var authorized = (!!AdminSession.email &&
                UserSession.userRoles.indexOf(authorizedRole) !== -1);
                if (authorized || authorizedRole == '*') {
                    isAuthorized = true;
                }
            });
            return isAuthorized;
        },

        logout: function () {
            $rootScope.adminAuthenticationError = false;
            $rootScope.authenticated = false;
            $rootScope.account = null;
            localStorageService.remove('isUser');
            localStorageService.remove('isAdmin');
            UserSession.invalidate();
            authService.loginCancelled();
        }
    };
});
