(
        function (ng) {
            var mod = ng.module("proveedoresModule");
            mod.constant("proveedoresContext", "api/proveedores");
           // mod.constant("regalosContext", "api/regalos");
            mod.controller('proveedorUpdateCtrl', ['$scope', '$http', 'proveedoresContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, proveedoresContext, $state, $rootScope ) {
                    $rootScope.edit = true;

                    var idProveedor = $state.params.proveedorId;

                    // Este arreglo guardara los ids de los regalos asociados y por asociar al autor.
                    //var idsRegalo = [];

                    // Este arreglo mostrará los regalos una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
                    //$scope.allRegalosShow = [];

                    //Consulto el proveedor a editar.
                    $http.get(proveedoresContext + '/' + idProveedor).then(function (response) {
                        var proveedor = response.data;
                        $scope.proveedorName = proveedor.name;
                        $scope.proveedorEspecialidad = proveedor.especialidad;
                    });
                    

                  //Faltan los metodos de regalos e invitados

                    $scope.createProveedor = function () {
                        /*Se llama a la función newRegalos() para buscar cada uno de los ids de los regalos
                         en el array que tiene todos los regalos y así saber como queda la lista final de los regalos asociados a la proveedor.
                         */
                        //$scope.newRegalos();
                        $http.put(proveedoresContext + "/" + idProveedor, {
                            name: $scope.proveedorName,
                            especialidad: $scope.proveedorEspecialidad
                        //}).then(function (response) {
                        //    if (idsRegalos.length >= 0) {
                         //       $http.put(proveedoresContext + "/" + response.data.id + "/regalos", $scope.allRegalosProveedor).then(function (response) {
                           //     });
                            //}
                            //Proveedor created successfully
                            }).then(function (response) {
                            $state.go('proveedoresList', {proveedorId: response.data.id}, {reload: true});
                            
                        });
                    };

                   // $scope.newRegalos = function () { ... implementar
                      
                }
            ]);
        }
)(angular);