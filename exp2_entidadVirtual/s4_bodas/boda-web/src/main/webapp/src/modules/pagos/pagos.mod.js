(function (ng) {
    var mod = ng.module("pagosModule", ['parejasModule', 'tarjetasCreditoModule', 'ui.router']);
    mod.constant("pagosContext", "pagos");
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            $urlRouterProvider.otherwise("/pagosList");

            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                parent: 'tarjetasDetail',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'pagos.html'
                    }
                }
            }).state('pagosList', {
                url: '/list',
                parent: 'pagos',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'pagos.list.html',
                        controller: 'pagosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagosDetail', {
                url: '/:pagoId',
                parent: 'pagos',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'pagos.detail.html',
                        controller: 'pagosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagosUpdate', {
                url: '/update/:pagoId',
                parent: 'pagos',
                data: {
                    requireLogin: true,
                    roles: ['admin']
                },
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'update/pagos.update.html',
                        controller: 'pagosUpdateCtrl'
                    }
                }
            }).state('pagosCreate', {
                url: '/createPago',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/pagos.new.html',
                        controller: 'pagosNewCtrl'
                    }
                }
            }).state('pagosConfirm', {
                url: '/confirmPagoCreate',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/pagos.confirm.new.html',
                    }
                }
            });
        }]);
})(window.angular);