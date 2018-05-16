(
        function (ng) {
            var mod = ng.module("ubicacionesModule");
            mod.constant("ubicacionesContext", "api/ubicaciones");
           // mod.constant("regalosContext", "api/regalos");
            mod.controller('ubicacionUpdateCtrl', ['$scope', '$http', 'ubicacionesContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, ubicacionesContext, $state, $rootScope ) {
                    $rootScope.edit = true;

                    var idUbicacion = $state.params.ubicacionId;

                    //Consulto la ubicacion a editar.
                    $http.get(ubicacionesContext + '/' + idUbicacion).then(function (response) {
                        var ubicacion = response.data;
                        $scope.ubicacionDireccion = ubicacion.direccion;
                        $scope.ubicacionName =  ubicacion.name;
                        $scope.ubicacionTelefono = ubicacion.telefono;
                    });

                    $scope.createUbicacion = function () {

                        $http.put(ubicacionesContext + "/" + idUbicacion, {
                            direccion: $scope.ubicacionDireccion,
                            name: $scope.ubicacionName,
                            telefono: $scope.ubicacionTelefono,

                            //ubicacion created successfully
                            }).then(function (response) {
                            $state.go('ubicacionesList', {ubicacionId: response.data.id}, {reload: true});
                            
                        });
                    };

                   // $scope.newRegalos = function () { ... implementar
                      
                }
            ]);
        }
)(angular);