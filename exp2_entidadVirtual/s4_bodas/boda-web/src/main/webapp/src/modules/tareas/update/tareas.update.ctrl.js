(
        function (ng) {
            var mod = ng.module("tareasModule");
            mod.constant("tareasContext", "tareas");
            mod.constant("proveedoresContext", "api/proveedores");
            mod.constant("opcionServiciosContext", "opcionServicios");
            mod.controller('tareaUpdateCtrl', ['$scope', '$http', 'proveedoresContext','opcionServiciosContext','$state', '$rootScope', 'tareasContext',
                function ($scope, $http, proveedoresContext,opcionServiciosContext, $state, $rootScope, tareasContext ) {
                    $rootScope.edit = true;

                    var idProveedor = $state.params.proveedorId;
                     var idOpcion = $state.params.opcionId;
                     var idTarea = $state.params.tareaId;
                   
                    $http.get(proveedoresContext + "/" + idProveedor + "/" + opcionServiciosContext + "/" + idOpcion + "/" +tareasContext +"/" +idTarea).then(function (response) {
                        var tarea = response.data;
                        $scope.tareaNombre = tarea.nombre;
                        $scope.tareaAprobada = tarea.aprobada;
                        $scope.tareaImage = tarea.image;
                        $scope.tareaDia = tarea.dia;    
                    });
                    
                    $scope.createTarea = function () {
                        $http.put(proveedoresContext + "/" + idProveedor + "/" + opcionServiciosContext + "/" + idOpcion + "/" + tareasContext +  "/"+ idTarea,  {
                            nombre: $scope.tareaNombre,
                            dia : $scope.tareaDia,
                            aprobada: $scope.tareaAprobada,
                            image: $scope.tareaImage
                           

                            }).then(function (response) {
                            $state.go('tareasList',{tareaId: response.data.id}, {reload: true});
                            
                        });
                    };


                      
                }
            ]);
        }
)(angular);


