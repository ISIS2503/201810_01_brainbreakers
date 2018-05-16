(function (ng) {
    var mod = ng.module("calificaionesModule");
    mod.constant("calificaionesContext", "calificaciones");
    mod.constant("opcionServicioContext","opcionServicios");
    mod.constant("proveedorContext","api/proveedores");
    mod.controller('calificacionesNewCtrl', ['$scope','$http','$state','calificaionesContext','opcionesContext','proveedoresContext','$rootScope',
        function ($scope,$http,$state,calificaionesContext,opcionesContext,proveedoresContext,$rootScope) {
            $rootScope.editC = false;
            $scope.data = {};
            $scope.createCalificacion= function () {
                $http.post(proveedoresContext + '/' + $state.params.proveedorId + '/'+ opcionesContext +"/"+ $state.params.opcionId + "/" + calificaionesContext, $scope.data ).then(function (response) {
                    //Author created successfully
                    swal("Creada!", "Tu calificación fue creada.", "success");
                    $state.go('calificacionesList', {parejaId: response.data.correoElec}, {reload: true});
                });
            };
           
                     
            
            
        }
    ]);
}
)(angular);