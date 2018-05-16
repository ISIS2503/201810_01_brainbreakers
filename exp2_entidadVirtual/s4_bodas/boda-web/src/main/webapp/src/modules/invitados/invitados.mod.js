(function (ng) {
    var mod = ng.module("invitadosModule", ['bodasModule', 'ui.router']);
    mod.constant("invitadosContext", "invitados");
    mod.constant("bodasContext", "api/bodas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/invitados/';
            $urlRouterProvider.otherwise("/invitadosList");

            $stateProvider.state('invitados', {
                url: '/invitados',
                abstract: true,
                parent: 'bodaDetail',
                data: {
                    requireLogin: false
                },
                views: {
                    childrenView: {
                        templateUrl: basePath + 'invitados.html'
                    }
                }
            }).state('invitadoDetail', {
                url: '/{invitadoId:int}',
                parent: 'invitados',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    invitadoId: null
                },
                views: {

                    'detailView': {
                        templateUrl: basePath + 'invitados.detail.html',
                        controller: 'invitadosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('invitadosList', {
                url: '/list',
                parent: 'invitados',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    bodaId: null
                },
                views: {
                    listView: {
                        templateUrl: basePath + 'invitados.list.html',
                        controller: 'invitadosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('invitadoCreate', {
                url: '/create',
                parent: 'invitados',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/invitados.new.html',
                        controller: 'invitadosNewCtrl'
                    }
                }
            }).state('invitadoUpdate', {
                url: '/update/{invitadoId:int}',
                parent: 'invitados',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    invitadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/invitados.new.html',
                        controller: 'invitadosUpdateCtrl'
                    }
                }
            }).state('invitadoDelete', {
                url: '/delete/{invitadoId:int}',
                parent: 'invitados',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']
                },
                param: {
                    invitadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/invitados.delete.html',
                        controller: 'invitadosDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);


