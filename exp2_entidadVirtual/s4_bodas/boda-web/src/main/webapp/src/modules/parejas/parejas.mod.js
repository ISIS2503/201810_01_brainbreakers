(function (ng) {
    var mod = ng.module("parejasModule", ['ui.router']);
    mod.constant("parejasContext", "api/parejas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/parejas/';
            $urlRouterProvider.otherwise("/parejasList");
            
            $stateProvider.state('parejas', {
                url: '/parejas',
                abstract: true,
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'parejas.html',
                        controller: 'parejasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('parejasList', {
                url: '/list',
                parent: 'parejas',
                data: {
                    requireLogin: true,
                    roles: ['admin']
                },
                
                 views: {
                    'listView': {
                        templateUrl: basePath + 'parejas.list.html'
                    }
                }
            }).state('parejasDetail', {
                url: '/detail/:parejaId',
                parent: 'parejas',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    parejaId: null
                },
                 views: {                     
                    'detailView': {
                        templateUrl: basePath + 'parejas.detail.mod.html',
                        controller: 'parejasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('parejasCreate', {
                url: '/create',
                parent:'parejas',   
                 data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                views: {
                    'detailView': {
                        controller: 'parejasNewCtrl',                       
                        templateUrl: basePath + '/create/parejas.new.html'
                    }
                }
            }).state('parejasUpdate', {
                url: '/editar/:parejaId',
                parent: 'parejas',
                data: {
                    requireLogin: true,
                    roles: ['admin','pareja']
                },
                param: {
                    parejaId: null
                },
                 views: {                     
                    'detailView': {
                        controller: 'parejaUpdateCtrl',
                        templateUrl: basePath + '/create/parejas.new.html'                       
                    }
                }
            }).state('parejasOneDetail', {
                url: '/one/:parejaId',
                parent: 'parejasList',
                data: {
                    requireLogin: true,
                    roles: ['admin']
                },
                param: {
                    parejaId: null
                },
                 views: {
                    
                    'detailOneView': {
                        templateUrl: basePath + 'parejas.detail.mod.html',
                        controller: 'parejasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
            ;
        }]);
})(window.angular);