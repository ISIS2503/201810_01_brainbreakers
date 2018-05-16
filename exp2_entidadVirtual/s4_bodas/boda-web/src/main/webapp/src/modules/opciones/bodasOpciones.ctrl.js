(function (ng) {
    var mod = ng.module("opcionesModule");
    mod.constant("bodasContext", "api/bodas");  
    mod.controller('bodaOpcionesCtrl', ['$scope', '$http', 'bodasContext', '$state', '$rootScope',

        function ($scope, $http, bodasContext, $state,  $rootScope) {
            $scope.parametrobodaId=$state.params.bodaId;
            $http.get(bodasContext +'/'+$state.params.bodaId+ '/opcionesServicio' ).then(function (response) {
                $scope.opcionesRecords = response.data;
                
                
            });
           
            $scope.orderOpciones = function (condicionNombre, tipoCondicion) {
                $scope.tipoOrdenOpcion = condicionNombre;
                $scope.currentOrdenOp = tipoCondicion;
            };
            
            $scope.contratarServicio=function(opcionI){
              $http.post(bodasContext + "/" +$scope.parametrobodaId + '/opcionesServicio/'+opcionI , {
                    
                    
                }).then(function (response) {
                    //Boda created successfully
                    $state.go('bodasOpcionesList', {bodaId: response.data.id}, {reload: true});
                });
            };
            $scope.contrato =function(){
                           swal({
                            title: 'Contratar Opcion',
                            input: 'text',
                            showCancelButton: true,
                            confirmButtonText: 'Submit',
                            showLoaderOnConfirm: true,
                            preConfirm: (text) => {
                              return new Promise((resolve) => {
                                setTimeout(() => {
                                  if (text === 'taken@example.com') {
                                    swal.showValidationError(
                                      'No existe esta Opcion.'
                                    )
                                  }
                                  resolve()
                                }, 500)
                              })
                            },
                            allowOutsideClick: false
                          }).then((result) => {
                              
                            
                                if(!Number.isNaN(result)){
                                    console.log(result);
                                     $scope.contratarServicio(result);
                                        swal({
                                          type: 'success',
                                          title: 'Opcion contratado',
                                          html: 'Opcion contratado: ' + result
                                        });
                                }
                               
                            
                          });
            };

        }
    ]);

})(window.angular);