(function (ng) {
    var mod = ng.module("tareasModule");
   mod.constant("tareasContext", "tareas");
   mod.constant("opcionesContext", "opcionServicios");
   mod.constant("proveedoresContext", "api/proveedores");
   
    mod.controller('tareasDeleteCtrl', ['$scope', '$http', 'opcionesContext','proveedoresContext', '$state','tareasContext',
        function ($scope, $http, opcionesContext,proveedoresContext, $state, tareasContext) {
            var idProveedor = $state.params.proveedorId;
            var idOpcion = $state.params.opcionId;
            var idTarea = $state.params.tareaId;
            $scope.deleteTarea = function () {
                
                $http.delete(proveedoresContext + "/" + idProveedor + "/" + opcionesContext+ "/" +idOpcion + "/" + tareasContext+ "/" + idTarea, {}).then(function (response) {
                    $state.go('tareasList', {tareaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
  
   

        

            
   


