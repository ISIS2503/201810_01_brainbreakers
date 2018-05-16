(
        function (ng) {
            var mod = ng.module("opcionesModule");
      
          mod.constant("opcionServicioContext","opcionServicios");
          mod.constant("proveedorContext","api/proveedores");
          mod.controller('opcionesUpdateCtrl', ['$scope', '$http', 'proveedorContext', '$state',  '$rootScope','opcionServicioContext',
                function ($scope, $http, proveedorContext, $state, $rootScope, opcionServicioContext) {
                    $rootScope.edit = true;

                    var idProveedor = $state.params.proveedorId;                  
                    var idOpcion = $state.params.opcionId;
                    $http.get( proveedorContext + "/" + idProveedor  + "/" + opcionServicioContext + "/" + idOpcion).then(function (response) {
                        var opcion = response.data;
                       
                        $scope.opcionDescripcion = opcion.descripcion;
                        $scope.opcionCosto = opcion.costo;
                        $scope.opcionDias = opcion.diasDisponibles;
                        $scope.opcionImage = opcion.image;
                      
                        
                    });
                    

                  //Faltan los metodos de regalos e invitados

                    $scope.createOpcionServicio = function () {
                      
                        $http.put(proveedorContext + "/" + idProveedor+ "/" + opcionServicioContext+ "/" +  idOpcion, {
                           descripcion: $scope.opcionDescripcion,
                            costo: $scope.opcionCosto,
                            diasDisponibles: $scope.opcionDias,
                            image: $scope.opcionImage
                         

                            }).then(function (response) {
                            $state.go('opcionesList',{opcionId: response.data.id}, {reload: true});
                            
                        });
                    };


                      
                }
            ]);
        }
)(angular);


