(function (ng) {
    var mod = ng.module("calificaionesModule");
    mod.constant("calificaionesContext", "calificaciones");
    mod.constant("opcionServicioContext","opcionServicios");
    mod.constant("proveedorContext","api/proveedores");
    mod.controller('calificacionesUpdateCtrl', ['$scope','$http','$state','calificaionesContext','opcionesContext','proveedoresContext','$rootScope','$filter',
        function ($scope,$http,$state,calificaionesContext,opcionesContext,proveedoresContext,$rootScope,$filter) {
          
            $rootScope.editC = true;
                    
                    //Inicializo el data que se muestra en el formulario
                    $scope.data ={};
                   
                   //Obtengo el id de la Calificacion el cual debio pasarse en parejasList 
                   var idCalificacion= $state.params.cafId;
                   
                   //Pido los datos de la Calificacion para mostrarlos y que el rol administrador pueda determinar modificarlos
                   $http.get(proveedoresContext + '/' + $state.params.proveedorId + '/'+ opcionesContext +"/"+ $state.params.opcionId + "/" + calificaionesContext + "/" +idCalificacion ).then(function(response){
                       var califica= response.data;
                       
                     $scope.data.calificacionNum = califica.calificacionNum;
                     $scope.data.comentario= califica.comentario;
                   });
                   
                   //Cuando se da submit en el formulario de crearParejas
                   //Llama a crear calificaion, aqui reescribimos esa función
                   $scope.createCalificacion = function () {
                                                
                        $http.put(proveedoresContext + '/' + $state.params.proveedorId + '/'+ opcionesContext +"/"+ $state.params.opcionId + "/" + calificaionesContext + '/' + idCalificacion , $scope.data).then(function (response) {
                            
                            //la Pareja fue creada 
                             swal("Actualizada!", "La calificación fue modificada.", "success");
                            $state.go('calificacionesList', {cafId: response.data.id}, {reload: true});
                        });
                    };
            
            
        }
    ]);
}
)(angular);