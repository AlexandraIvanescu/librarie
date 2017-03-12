/**
 * Created by Alexandra Ale on 12.03.2017.
 */

angular.module('libraryApp').service('UserSession', function () {

    this.create = function (data) {
        this.userId = data.userId;
        this.email = data.email;
        this.firstName = data.firstName;
        this.lastName = data.lastName;
        this.userRoles = ['USER'];
    };

    this.invalidate = function () {
        this.userId = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.userRoles = null;
    };

    return this;
});