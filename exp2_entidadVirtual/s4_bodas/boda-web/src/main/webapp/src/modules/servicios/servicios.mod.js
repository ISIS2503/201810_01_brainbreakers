(function (ng) {
    var mod = ng.module("serviciosModule", ['ui.router']);
    mod.constant("serviciosContext", "api/servicios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/servicios/';
            //var basePathRegalos = 'src/modules/regalos/';
            $urlRouterProvider.otherwise("/serviciosList");
            $stateProvider.state('servicios', {
                url: '/servicios',
                abstract: true,
                 data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'servicios.html',
                        controller: 'serviciosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('serviciosList', {
                url: '/list',
                parent: 'servicios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'servicios.list.html'
                    }
                }
            }).state('servicioDetail', {
                url: '/servicios/{servicioId:int}',
                parent: 'servicios',
                param: {
                    servicioId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'servicios.detail.html',
                        controller: 'serviciosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('servicioCreate', {
                url: '/create',
                parent: 'servicios',
                views: {
                    'detailView': {
                    templateUrl: basePath + '/new/servicios.new.html',
                    controller: 'servicioNewCtrl'
                    }
                }
            }).state('servicioUpdate', {
                url: '/update/{servicioId:int}',
                parent: 'servicios',
                param: {
                    servicioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/servicios.new.html',
                        controller: 'servicioUpdateCtrl'
                    }
                }
            }).state('servicioDelete', {
                url: '/delete/{servicioId:int}',
                parent: 'servicios',
                param: {
                    authorId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/servicio.delete.html',
                        controller: 'servicioDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);