/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("casaModule", ['ui.router']);
    mod.constant("brainBrakersContext", "api/casa");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modulos/casa/';

            $urlRouterProvider.otherwise("/casa");


            $stateProvider.state('casa', {
                url: '/casa',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'casa.html',
                        controller: 'casaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $stateProvider.state('casaAlerta1', {
                url: '/casa/Alerta1',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'casaAlerta1.html',
                        controller: 'casaAlerta1Ctrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $stateProvider.state('casaAlerta2', {
                url: '/casa/Alerta2',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'casaAlerta2.html',
                        controller: 'casaAlerta2Ctrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $stateProvider.state('casaAlerta3', {
                url: '/casa/Alerta3',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'casaAlerta3.html',
                        controller: 'casaAlerta3Ctrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $stateProvider.state('casaAlerta4', {
                url: '/casa/Alerta4',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'casaAlerta4.html',
                        controller: 'casaAlerta4Ctrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


