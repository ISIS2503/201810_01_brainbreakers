
(function (ng) {
    var mod = ng.module("tareasModule");
    mod.constant("tareasContext", "tareas");
    mod.constant("opcionesServicioContext","opcionServicios");
    mod.constant("proveedoresContext","api/proveedores");
  
    mod.controller('tareasCtrl', ['$scope', '$http', '$state',  'tareasContext','opcionesServicioContext','proveedoresContext',
        function ($scope, $http,$state, tareasContext,opcionesServicioContext,proveedoresContext){
           $http.get(proveedoresContext + '/' + $state.params.proveedorId + '/'+ opcionesServicioContext +"/"+ $state.params.opcionId + "/" + tareasContext).then(function (response) {

                $scope.tareasRecords = response.data;
               
            });
       if ($state.params.tareaId !== undefined) {
           $http.get(proveedoresContext + '/' + $state.params.proveedorId + '/'+ opcionesServicioContext +"/"+ $state.params.opcionId + "/" + tareasContext+ "/" + $state.params.tareaId ).then(function (response) {
                   
                    $scope.currentTarea = response.data;
                });   
            }
    
            }]);
    })(window.angular);