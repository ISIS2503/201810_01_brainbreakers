(function (ng) {
    var mod = ng.module("opcionesModule", ['proveedoresModule', 'bodasModule','ui.router']);
    mod.constant("opcionesContext", "opcionServicios");
    mod.constant("proveedoresContext", "api/proveedores");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/opciones/';
            $urlRouterProvider.otherwise("/opcionesList");

            $stateProvider.state('opciones', {
                url: '/opciones',
                abstract: true,
                parent: 'proveedorChild',
                  data: {
                    requireLogin: false
                },
              views: {
                     'childrenView': {
                        templateUrl: basePath + 'opciones.html'
                    }
                
                }
            }).state('opcionesList', {
                url: '/lista',
                parent: 'opciones',
                  data: {
                    requireLogin: false
                    

                },
                views: {
                    'listView':{                                      
                        templateUrl: basePath + 'opciones.list.html',
                        controller: 'opcionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bodasOpcionesList', {
                url: '/lista/contratadas/:bodaId',
                parent: 'bodas',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']

                },
                param: {
                    bodaId: null
                },
                views: {
                    'listView':{                                      
                        templateUrl: basePath + 'opciones.list.mod.html',
                        controller: 'bodaOpcionesCtrl'
                    }
                }
            }).state('bodasOpcionesContratar', {
                url: '/contrato/opcion/:opcionId',
                parent: 'bodas',
                data: {
                    requireLogin: true,
                    roles: ['admin', 'pareja']

                },
                param: {
                    opcionId: null
                },
                views: {
                    'listView':{                                      
                        templateUrl: basePath + 'opciones.list.mod.html',
                        controller: 'bodaOpcionesCtrl'
                    }
                }
            }).state('opcionDetail', {
                url: '/:opcionId',
                parent: 'opciones',
                  data: {
                    requireLogin: false
                    

                },
                param: {
                    opcionId: null
                },
                views: {
                    'detailView':{
                      
                        templateUrl: basePath + 'opciones.detail.html',
                        controller: 'opcionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('opcionesCreate', {
                url: '/nuevo/create',
                parent: 'opciones',
                  data: {
                    requireLogin: true,
                    roles: ['admin']

                },
                views: {
                    'detailView': {
                    templateUrl: basePath + 'new/opciones.new.html',
                    controller: 'opcionesNewCtrl'
                    }
                }
            }).state('opcionesDelete', {
                url: '/delete/:opcionId',
                parent: 'opciones',
                  data: {
                    requireLogin: true,
                    roles: ['admin']

                },
                param: {
                    opcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/opciones.delete.html',
                        controller: 'opcionesDeleteCtrl'
                    }
                }
            }).state('opcionesUpdate', {
                url: '/update/:opcionId',
                parent: 'opciones',
                  data: {
                    requireLogin: true,
                    roles: ['admin']

                },
                param: {
                    opcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/opciones.new.html',
                        controller: 'opcionesUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);



                


