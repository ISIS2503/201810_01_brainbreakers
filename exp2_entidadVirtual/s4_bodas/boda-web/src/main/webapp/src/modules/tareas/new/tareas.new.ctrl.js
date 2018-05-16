    
(function (ng) {
    var mod = ng.module("tareasModule");
    mod.constant("tareasContext", "tareas");
    mod.constant("opcionesServicioContext","opcionServicios");
    mod.constant("proveedoresContext","api/proveedores");
    
    mod.controller('tareasNewCtrl', ['$scope', '$http',  '$state','proveedoresContext','opcionesServicioContext', '$rootScope','tareasContext',
        function ($scope, $http, $state, proveedoresContext, opcionesServicioContext,  $rootScope, tareasContext) {
            $rootScope.edit = false;
            $scope.createTarea = function () {
                 
                $http.post(proveedoresContext + "/" + $state.params.proveedorId + "/" + opcionesServicioContext + "/" + $state.params.opcionId + "/" + tareasContext, {
               
                     aprobada: $scope.tareaAprobada,
                     dia: $scope.tareaDia,
                     nombre: $scope.tareaNombre,
                     image: $scope.tareaImage
                     
                  
               
                    
                  
                }).then(function (response) {

                    $state.go('tareasList', {tareaId: response.data.id}, {reload: true});
                });
                 
            
            };
        }
    ]);
}
)(angular);


           


           

