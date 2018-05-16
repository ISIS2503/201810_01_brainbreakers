/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    var aplicacionMundial = angular.module('aplicacionMundial', []);



    aplicacionMundial.directive('toolbar', function () {
        return{
            restrict: 'E',
            templateUrl: 'toolbar.html',
            controller: function () {
                this.tab = 0;
                this.selectTab = function (setTab) {
                    this.tab = setTab;
                };
                this.isSelected = function (tabParam) {
                    return this.tab === tabParam;
                };
            },
            controllerAs: 'toolbar'
        };
    });




    aplicacionMundial.directive('competitorInfo', function () {
        return{
            restrict: 'E',
            templateUrl: 'competitorInfo.html',
            controller: ['$http', '$scope', function ($http, $scope) {
                    var self = this;
                    self.competitors = [];
                    $scope.api = function () {
                        var callApi = $http.get('http://localhost:8083/webresources/competitors').success(function (data) {
                            self.competitors = data;
                        });
                    }
                    $scope.api();
                }],
            controllerAs: 'CompetitorsListCtrl'
        };
    });




})();

