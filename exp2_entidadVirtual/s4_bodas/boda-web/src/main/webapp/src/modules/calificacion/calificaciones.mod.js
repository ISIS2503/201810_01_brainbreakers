(function(ng){
    var mod = ng.module("calificaionesModule", ['proveedoresModule','opcionesModule', 'ui.router']);    
    mod.constant("proveedoresContext", "api/proveedores");
    mod.constant("opcionesContext", "opcionServicios");
    mod.constant("calificacionesContext", "calificaciones");
    
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            var basePath = 'src/modules/calificacion/';
            $urlRouterProvider.otherwise("/calificacionesList");
            
            $stateProvider.state('calificaciones', {
                url: '/calificaciones',
                abstract: true,
                parent:'opcionDetail',
                views: {
                    'listCView': {
                        templateUrl: basePath + 'calificaciones.html',
                        controller: 'calificacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calificacionesList', {
                url: '/lista', 
                parent :'calificaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificaciones.list.html',
                        controller: 'calificacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calificacionesCreate', {
                url: '/crearNueva', 
                parent :'calificacionesList',    
                views: {
                    'modView': {
                        templateUrl: basePath + 'create/calificaciones.create.html',
                        controller: 'calificacionesNewCtrl',
                    }
                }
            }).state('calificacionesUpdate', {
                url: '/EditC/:cafId', 
                parent :'calificacionesList',
                param:{
                    cafId:null
                },
                views: {
                    'modView': {
                        templateUrl: basePath + 'create/calificaciones.create.html',
                        controller: 'calificacionesUpdateCtrl',
                    }
                }
            });
    }]);
})(window.angular);

