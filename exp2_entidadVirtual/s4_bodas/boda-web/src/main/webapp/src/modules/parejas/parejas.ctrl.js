(function (ng) {

    var mod = ng.module("parejasModule");
    mod.constant("parejasContext", "api/parejas"); 
    mod.controller("parejasCtrl", ['$scope', '$state', '$http', 'parejasContext',
        function ($scope, $state, $http, parejasContext) {
            $http.get(parejasContext).then(function (response) {
                $scope.parejasRecords = response.data;
            });

            //Para la vista de parejas individuales
            $scope.Open = function(item){                
                   if ($scope.isOpen(item)){
                    $scope.opened = undefined;
                } else {
                    $scope.opened = item;
                    }         
                
            };
            $scope.isOpen = function(item){
                   return $scope.opened === item;
               };
               
               //Para la vista del menu del create
              
               $scope.OpenCreate = function(){                
                   if ($scope.isOpenCreate()){
                    $scope.openCreate = false;
                } else {
                    $scope.openCreate = true;
                    }         
                
            };
            $scope.isOpenCreate = function(){
                   return $scope.openCreate === true;
               };
              
            // el controlador recibió un parejaId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($state.params.parejaId !== undefined) {
                // obtiene el dato del recurso REST
                $http.get(parejasContext + "/" + $state.params.parejaId)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentPareja = response.data;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentPareja = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }

             this.orderInvParejas= function(condicionNombre, tipoCondicion){
                 $scope.tipoOrdenPareja = condicionNombre;                
                 $scope.currentOrdenPa= tipoCondicion;
            };
            
            
            this.saveRecord = function (id) {
                currentPareja = $scope.currentPareja;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id === null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(parejasContext, currentPareja)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('parejasList');
                            });

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(parejasContext + "/" + currentPareja.id, currentPareja)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('parejasList');
                            });
                }
                ;
            };
            
            this.deleteRecord = function(parejaRecord) {
                swal({ title: "¿Desea eliminar la pareja con correo " + parejaRecord.correoElec +"?",
                    text: "No podrá recuperar la pareja!",
                    type: "warning",                   
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Si, eliminalo",
                    cancelButtonText: "Cancelar"
                  
                     }).then(function () {
                        var k=  $http.delete(parejasContext +"/" + parejaRecord.correoElec)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.parejasRecords.indexOf(parejaRecord);
                                if (index > -1) {
                                    $scope.parejasRecords.splice(index, 1);
                                }
                                 swal("Eliminada!", "Tu Pareja fue eliminada.", "success");
                            }, function (response) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.
                                if(response.status === 500){
                                    swal("La Pareja tiene una boda asociada, primero borre la boda.", "error ", "error");
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
                                       swal({ title: "La pareja no se elimino",
                                    text: "No se ha hecho ningún cambio!"                 
                                  
                                    });
                        }
                      });
                  
                
                 
            }
            

 // Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

