(
        function (ng) {
            var mod = ng.module("serviciosModule");
            mod.constant("serviciosContext", "api/servicios");
           // mod.constant("regalosContext", "api/regalos");
            mod.controller('servicioUpdateCtrl', ['$scope', '$http', 'serviciosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, serviciosContext, $state, $rootScope ) {
                    $rootScope.edit = true;

                    var idServicio = $state.params.servicioId;



                    //Consulto la servicio a editar.
                    $http.get(serviciosContext + '/' + idServicio).then(function (response) {
                        var servicio = response.data;
                        $scope.servicioName = servicio.name;
                        $scope.servicioDescripcion = servicio.descripcion;
                        
                    });
                    

                  //Faltan los metodos de regalos e invitados

                    $scope.createServicio = function () {
   
                        //$scope.newServicios();
                        $http.put(serviciosContext + "/" + idServicio, {
                            name: $scope.servicioName,
                            descripcion: $scope.servicioDescripcion
                            }).then(function (response) {
                            $state.go('serviciosList', {servicioId: response.data.id}, {reload: true});
                            
                        });
                    };

                   // $scope.newRegalos = function () { ... implementar
                      
                }
            ]);
        }
)(angular);