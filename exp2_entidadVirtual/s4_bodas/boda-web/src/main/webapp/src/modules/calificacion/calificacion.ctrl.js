/**
 * Controlador del modulo calificación
 */
(function(ng){
    var mod = ng.module("calificaionesModule");
    mod.constant("calificaionesContext", "calificaciones");
    mod.constant("opcionServicioContext","opcionServicios");
    mod.constant("proveedorContext","api/proveedores");
    
    mod.controller('calificacionCtrl',['$scope','$http','$state','calificaionesContext','opcionesContext','proveedoresContext',
        function($scope,$http,$state,calificaionesContext,opcionesContext,proveedoresContext){
            $http.get(proveedoresContext + '/' + $state.params.proveedorId + '/'+ opcionesContext +"/"+ $state.params.opcionId + "/" + calificaionesContext).then(function (response) {
              
                     $scope.calificacionesRecords = response.data;
                 });
                 
                 $scope.imgCalificacion = function (calificacionNum) {
                if (calificacionNum === 5) {
                    $scope.imgCalif = "resources/icons/cinco.png";
                } else if (calificacionNum === 4) {
                    $scope.imgCalif = "resources/icons/cuatro.png";
                } else if (calificacionNum === 3) {
                    $scope.imgCalif = "resources/icons/tres.png";
                } else if (calificacionNum === 2) {
                    $scope.imgCalif = "resources/icons/dos.png";
                } else if (calificacionNum === 1) {
                    $scope.imgCalif = "resources/icons/uno.png";
                }
            };
            
            this.deleteCalificacion = function(calificacionRecord) {
                swal({ title: "¿Desea eliminar la calificacion con id " + calificacionRecord.id +"?",
                    text: "No podrá recuperar la calificación!",
                    type: "warning",                   
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Si, eliminar",
                    cancelButtonText: "Cancelar"
                  
                     }).then(function () {
                        var k=  $http.delete(proveedoresContext + '/' + $state.params.proveedorId + '/'+ opcionesContext +"/"+ $state.params.opcionId + "/" + calificaionesContext+"/" + calificacionRecord.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.calificacionesRecords.indexOf(calificacionRecord);
                                if (index > -1) {
                                    $scope.calificacionesRecords.splice(index, 1);
                                }
                                 swal("Eliminada!", "Tu calificacion fue eliminada.", "success");
                            }, function (response) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.
                                if(response.status === 500){
                                    swal("No sabemos que sucedio, pero si que es nuestra culpa.", "Error ", "error");
                                }
                                 else{
                                     swal("Oh! Algo anda mal!", response.statusText, "error");
                                 }
                                });
                                
                                
                             return k;
                      }, function (dismiss) {
                        // dismiss can be 'cancel', 'overlay',
                        // 'close', and 'timer'
                        if (dismiss === 'cancel') {
                                       swal({ title: "La calificación no se elimino",
                                    text: "No se ha hecho ningún cambio!"                 
                                  
                                    });
                        }
                      });
                  
                
                 
            };     
            
        }
        
    ]);
    
})(window.angular);
