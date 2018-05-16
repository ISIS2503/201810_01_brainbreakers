(
        function (ng) {
            var mod = ng.module("bodasModule");
            mod.constant("invitadosContext", "invitados");
            mod.constant("bodasContext", "api/bodas");
            mod.controller('invitadosUpdateCtrl', ['$scope', '$http', 'bodasContext', '$state', '$rootScope', 'invitadosContext',
                function ($scope, $http, bodasContext, $state, $rootScope, invitadosContext ) {
                    $rootScope.edit = true;

                    var idBoda = $state.params.bodaId;
                    
                    var idInv = $state.params.invitadoId;

                    //Consulto el invitado a editar.
                    $http.get(bodasContext + "/" + idBoda + "/" + invitadosContext + "/" + idInv).then(function (response) {
                        var inv = response.data;
                        $scope.invitadoName = inv.name;
                        $scope.invitadoDocumento = inv.documento;
                        $scope.invitadoCorreo = inv.correo;
                        $scope.invitadoCategoria = inv.categoria;
                        $scope.invitadoAsistencia = inv.asistencia;
                        
                    });
                    

                  //Faltan los metodos de regalos e invitados

                    $scope.createInvitado = function () {
                        /*Se llama a la función newRegalos() para buscar cada uno de los ids de los regalos
                         en el array que tiene todos los regalos y así saber como queda la lista final de los regalos asociados a la boda.
                         */
                        //$scope.newRegalos();
                        $http.put(bodasContext + "/" + idBoda + "/" + invitadosContext+"/"+ idInv, {
                            name: $scope.invitadoName,
                            documento: $scope.invitadoDocumento,
                            correo: $scope.invitadoCorreo,
                            categoria: $scope.invitadoCategoria,
                            asistencia: $scope.invitadoAsistencia

                            }).then(function (response) {
                            $state.go('invitadosList',{invitadoId: response.data.id}, {reload: true});
                            
                        });
                    };


                      
                }
            ]);
        }
)(angular);


