(function (ng) {
    var mod = ng.module("tarjetasCreditoModule", ['parejasModule', 'ui.router']);
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/tarjetasCredito/';
            $urlRouterProvider.otherwise("/tarjetasList");

            $stateProvider.state('tarjetas', {
                url: '/tarjetasCredito',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetasCredito.html',
                        controller: 'tarjetasCreditoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetasList', {
                url: '/:parejaId/list',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    parejaId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetasCredito.list.html',
                        controller: 'tarjetasCreditoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetasDetail', {
                url: '/:tarjetaId',
                parent: 'tarjetasList',
                 data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    tarjetaId: null
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'tarjetas.detail.html',
                        controller: 'tarjetasCreditoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetasDelete', {
                url: 'delete/:parejaId/:tarjetaId',
                parent: 'tarjetas',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    tarjetaId: null,
                    parejaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/tarjetas.delete.html',
                        controller: 'tarjetasCreditoDeleteCtrl'
                    }
                }
            }).state('tarjetasUpdate', {
                url: '/update/:parejaId/:tarjetaId',
                parent: 'tarjetas',
                 data: {
                    requireLogin: true,
                    roles: ['admin']
                },
                param: {
                    tarjetaId: null,
                    parejaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'update/tarjetas.update.html',
                        controller: 'tarjetasUpdateCtrl'
                    }
                }
            }).state('tarjetasCreate', {
                url: '/createTarjeta/nueva',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/tarjetas.new.html',
                        controller: 'tarjetasNewCtrl'
                    }
                }
            });
        }]);
})(window.angular);