    
(function (ng) {
    var mod = ng.module("opcionesModule");
    mod.constant("opcionesServicioContext", "opcionServicios");
    mod.constant("proveedoresContext", "api/proveedores");
    
    mod.controller('opcionesNewCtrl', ['$scope', '$http', 'proveedoresContext', '$state', '$rootScope','opcionesServicioContext',
        function ($scope, $http, proveedoresContext, $state,  $rootScope, opcionesServicioContext) {
            $rootScope.edit = false;
            $scope.createOpcionServicio = function () {
                $http.post(proveedoresContext + "/" + $state.params.proveedorId + "/" + opcionesServicioContext , {
                    descripcion: $scope.opcionDescripcion,
                    costo: $scope.opcionCosto,
                    diasDisponibles: $scope.opcionDias,
                    image: $scope.opcionImage
                  
                    
                }).then(function (response) {
                    $state.go('opcionesList', {
                        opcionId: response.data.id}, {reload: true});
                });
                 
            
            };
        }
    ]);
}
)(angular);


           


           


