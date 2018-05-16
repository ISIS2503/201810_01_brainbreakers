(function (ng) {
    var mod = ng.module("bodasModule", ['ui.router']);
    mod.constant("bodasContext", "api/bodas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/bodas/';
            //var basePathRegalos = 'src/modules/regalos/';
            $urlRouterProvider.otherwise("/bodasList");
            $stateProvider.state('bodas', {
                url: '/bodas',
                abstract: true,
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bodas.html',
                        controller: 'bodasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bodasList', {
                url: '/list',
                parent: 'bodas',
                data: {
                    requireLogin: true,
                    roles: ['admin']
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'bodas.list.html'
                    }
                }
            }).state('bodaDetail', {
                url: '/{bodaId:int}',
                parent: 'bodas',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']

                },
                param: {
                    bodaId: null
                },
                views: {

                    'detailView': {
                        templateUrl: basePath + 'bodas.detail.html',
                        controller: 'bodasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bodaCreate', {
                url: '/create',
                parent: 'bodas',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/bodas.new.html',
                        controller: 'bodaNewCtrl'
                    }
                }
            }).state('bodaUpdate', {
                url: '/update/{bodaId:int}',
                parent: 'bodas',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    bodaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/bodas.new.html',
                        controller: 'bodaUpdateCtrl'
                    }
                }
            }).state('bodaDelete', {
                url: '/delete/{bodaId:int}',
                parent: 'bodas',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    bodaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/boda.delete.html',
                        controller: 'bodaDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);