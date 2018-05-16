(function (ng) {
    var mod = ng.module("regalosModule", ['bodasModule', 'ui.router']);
    mod.constant("regalosContext", "regalos");
    mod.constant("bodasContext", "api/bodas");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/regalos/';
            $urlRouterProvider.otherwise("/regalosList");

            $stateProvider.state('regalos', {
                url: '/regalos',
                abstract: true,
                parent: 'bodaDetail',
                data: {
                    requireLogin: false
                },
                views: {
                    childrenView: {
                        templateUrl: basePath + 'regalos.html'
                    }
                }
            }).state('regaloDetail', {
                url: '/{regaloId:int}',
                parent: 'regalos',
                data: {
                    requireLogin: true,

                    roles: ['admin', 'pareja']
                },
                param: {
                    regaloId: null
                },
                views: {

                    'detailView': {
                        templateUrl: basePath + 'regalos.detail.html',
                        controller: 'regalosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('regalosList', {
                url: '/list',
                parent: 'regalos',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'regalos.list.html',
                        controller: 'regalosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('regaloCreate', {
                url: '/create',
                parent: 'regalos',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/regalos.new.html',
                        controller: 'regalosNewCtrl'
                    }
                }
            }).state('regaloUpdate', {
                url: '/update/{regaloId:int}',
                parent: 'regalos',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    regaloId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/regalos.new.html',
                        controller: 'regalosUpdateCtrl'
                    }
                }
            }).state('regaloDelete', {
                url: '/delete/{regaloId:int}',
                parent: 'regalos',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    regaloId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/regalos.delete.html',
                        controller: 'regalosDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);


