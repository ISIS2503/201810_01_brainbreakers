(function (ng) {
    var mod = ng.module("tareasModule", ['proveedoresModule','opcionesModule', 'ui.router']);
    mod.constant("tareasContext", "tareas");
     mod.constant("proveedoresContext", "api/proveedores");
    mod.constant("opcionesContext", "opcionServicios");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/tareas/';
            $urlRouterProvider.otherwise("/tareasList");
         
            $stateProvider.state('tareas', {
                url: '/tareas',
                abstract: true,
                parent: 'opcionDetail',
                 data: {
                    requireLogin: false
                },
              views: {
                     'listCView': {
                        templateUrl: basePath + 'tareas.html',
                        controller: 'tareasCtrl',
                        controllerAs: 'ctrl'
                    }
                
                }
            }).state('tareasList', {
                url: '/list',
                parent: 'tareas',
                 data: {
                    requireLogin: false
                },
                views: {
                    'listView':{
                      
                        templateUrl: basePath + 'tareas.list.html',
                        controller: 'tareasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tareaDetail', {
                url: '/detail/:tareaId',
                parent: 'tareas',
                 data: {
                    requireLogin: false
                },
                param: {
                    tareaId: null
                },
                views: {
                    'listView':{
                      
                        templateUrl: basePath + 'tareas.list.html',
                        controller: 'tareasCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'tareas.detail.html',
                        controller: 'tareasCtrl',
                        controllerAs: 'ctrl'
                    }
                    
                }
            }).state('tareaCreate', {
                url: '/create',
                parent: 'tareas',
                 data: {
                    requireLogin: true,
                    roles: ['admin']

                },
                views: {
                    'detailView': {
                    templateUrl: basePath + 'new/tareas.new.html',
                    controller: 'tareasNewCtrl'
                    }
                }
            }).state('tareaDelete', {
                url: '/delete/:tareaId',
                parent: 'tareas',
                  data: {
                    requireLogin: true,
                    roles:['admin']

                },
                param: {
                    tareaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/tareas.delete.html',
                        controller: 'tareasDeleteCtrl'
                    }
                    
                }
            }).state('tareaUpdate', {
                url: '/nue/:tareaId',
                parent: 'tareas',
                  data: {
                    requireLogin: true,
                    roles: ['admin']

                },
                param: {
                    tareaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'update/tareas.update.html',
                        controller: 'tareaUpdateCtrl'
                    }
                    
                }
            }).state('tareasListOpcion', {
                url: '/list',
                parent: 'tareas',
                  data: {
                    requireLogin: false
                    

                },
                views: {
                    'listView':{
                      
                        templateUrl: basePath + 'tareas.opciones.list.html',
                        controller: 'tareasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);


