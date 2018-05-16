(function (ng) {
    var mod = ng.module("ubicacionesModule", ['ui.router', 'uiGmapgoogle-maps']);
    mod.constant("ubicacionesContext", "api/ubicaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', 'uiGmapGoogleMapApiProvider',function ($stateProvider, $urlRouterProvider,uiGmapGoogleMapApiProvider) {
            uiGmapGoogleMapApiProvider.configure({
                key: 'AIzaSyBJlzzVMq3Q8sBKH-UCGuEalt3J3BD1RGg',
                libraries: 'weather,geometry,visualization',
                v: '3.20'
            });
            var basePath = 'src/modules/ubicaciones/';
            //var basePathRegalos = 'src/modules/regalos/';
            $urlRouterProvider.otherwise("/ubicacionesList");
            $stateProvider.state('ubicaciones', {
                url: '/ubicaciones',
                abstract: true,
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ubicaciones.html',
                        controller: 'ubicacionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ubicacionesList', {
                url: '/list',
                parent: 'ubicaciones',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'ubicaciones.list.html'
                    }
                }
            }).state('ubicacionDetail', {
                url: '/ubicaciones/{ubicacionId:int}',
                parent: 'ubicaciones',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    ubicacionId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'ubicaciones.detail.html',
                        controller: 'ubicacionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ubicacionCreate', {
                url: '/create',
                parent: 'ubicaciones',
                views: {
                    'detailView': {
                    templateUrl: basePath + '/new/ubicaciones.new.html',
                    controller: 'ubicacionNewCtrl'
                    }
                }
            }).state('ubicacionUpdate', {
                url: '/update/{ubicacionId:int}',
                parent: 'ubicaciones',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    ubicacionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/ubicaciones.new.html',
                        controller: 'ubicacionUpdateCtrl'
                    }
                }
            }).state('ubicacionDelete', {
                url: '/delete/{ubicacionId:int}',
                parent: 'ubicaciones',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    ubicacionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/ubicacion.delete.html',
                        controller: 'ubicacionDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);