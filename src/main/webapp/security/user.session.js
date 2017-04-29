/**
 * Created by Alexandra Ale on 12.03.2017.
 */

angular.module('libraryApp').service('UserSession', function () {

    this.create = function (data) {
        this.id = data.id;
        this.email = data.email;
        this.name = data.name;
        this.userRoles = ['USER'];
    };

    this.invalidate = function () {
        this.id = null;
        this.email = null;
        this.name = null;
        this.userRoles = null;
    };

    return this;
});