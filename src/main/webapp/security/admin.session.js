/**
 * Created by Alexandra Ale on 12.03.2017.
 */
angular.module('libraryApp').service('AdminSession', function () {

    this.create = function (data) {
        this.adminId = data.adminId;
        this.email= data.email;
        this.firstName = data.firstName;
        this.lastName = data.lastName;
        this.userRoles = ['ADMIN'];
    };

    this.invalidate = function () {
        this.adminId = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.userRoles = null;
    };

    return this;
});
