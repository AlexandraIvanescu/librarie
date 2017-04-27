/**
 * Created by Alexandra Ale on 05.03.2017.
 */
'use strict';

angular.module('libraryApp').config(['$locationProvider', '$routeProvider', '$httpProvider', 'USER_ROLES',
    function config($locationProvider, $routeProvider, $httpProvider, USER_ROLES) {
        $locationProvider.hashPrefix('!');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        $routeProvider.when('/', {
            redirectTo: '/login'
        }).when('/login', {
            template: '<login></login>',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            }
        }).when('/user/home', {
            template: '<home></home>',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user]
            }
        }).when('/user/books', {
            template: '<book-list></book-list>',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user]
            }
        }).when('/user/subscribers', {
            template: '<subscribers></subscribers>',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user]
            }
        }).when('/user/settings', {
            template: '<settings></settings>',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user]
            }
        }).when("/error/:code", {
            template: '<error></error>',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            }
        }).when('/loading', {
            template: '<loading></loading>',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            }
        }).otherwise({
            redirectTo: '/error/404',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            }
        });
    }
]).run(function ($rootScope, $location, $http, UserSession, UserAuthSharedService, $q, $timeout, localStorageService) {

    $rootScope.$on('event:auth-forbidden', function () {
        $rootScope.$evalAsync(function () {
            $location.path('/error/403').replace();
        });
    });

    // Call when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
        $rootScope.isUser = localStorageService.get("isUser");
        $rootScope.loadingAccount = false;
        var home = $rootScope.isUser ? "/user/home" : "";
        var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : home);
        var delay = ($location.path() === "/loading" ? 1500 : 0);

        $timeout(function () {

            if ($rootScope.isUser) {
                UserSession.create(data);
                $rootScope.account = UserAuthSharedService;
            }

            $rootScope.authenticated = true;
            $location.path(nextLocation).replace();
        }, delay);

    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function (event, data) {
        if ($rootScope.loadingAccount && data.status !== 401) {
            $rootScope.requestedUrl = $location.path();
            $location.path('/loading');
        } else {

            UserSession.invalidate();

            $rootScope.authenticated = false;
            $rootScope.loadingAccount = false;
            $location.path('/login');
        }
    });

    $rootScope.$on('$routeChangeStart', function (event, next) {
        if (next.originalPath === "/login" && $rootScope.authenticated) {
            event.preventDefault();
        } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-loginRequired", {});
        } else if (next.access && (!UserAuthSharedService.isAuthorized(next.access.authorizedRoles))) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-forbidden", {});
        }
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function () {
        $location.path('/login').replace();
    });

    $rootScope.isUser = localStorageService.get("isUser");

    // Get already authenticated user account
    if ($rootScope.isUser) {
        UserAuthSharedService.getAccount();
    }
});
