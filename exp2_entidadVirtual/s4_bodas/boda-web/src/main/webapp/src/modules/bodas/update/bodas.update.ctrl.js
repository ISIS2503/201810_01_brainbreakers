(
        function (ng) {
            var mod = ng.module("bodasModule");
            mod.constant("bodasContext", "api/bodas");
           // mod.constant("regalosContext", "api/regalos");
            mod.controller('bodaUpdateCtrl', ['$scope', '$http', 'bodasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, bodasContext, $state, $rootScope ) {
                    

                    var idBoda = $state.params.bodaId;

                    // Este arreglo guardara los ids de los regalos asociados y por asociar al autor.
                    //var idsRegalo = [];

                    // Este arreglo mostrará los regalos una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
                    //$scope.allRegalosShow = [];

                    //Consulto la boda a editar.
                    $http.get(bodasContext + '/' + idBoda).then(function (response) {
                        var boda = response.data;
                        $scope.bodaName = boda.name;
                        $scope.bodaFecha = new Date(boda.fecha);
                        $scope.bodaReligion = boda.description;
                        $scope.bodaTema = boda.description;
                        $scope.bodaTipo = boda.description;
                        $scope.bodaImage = boda.image;
                        
                        //$scope.allRegalosBoda = boda.regalos;
                        //$scope.mergeRegaloss($scope.allRegalosBoda);
                    });
                    

                  //Faltan los metodos de regalos e invitados

                    $scope.createBoda = function () {
                        /*Se llama a la función newRegalos() para buscar cada uno de los ids de los regalos
                         en el array que tiene todos los regalos y así saber como queda la lista final de los regalos asociados a la boda.
                         */
                        //$scope.newRegalos();
                        $http.put(bodasContext + "/" + idBoda, {
                            fecha: $scope.bodaFecha,
                            image: $scope.bodaImage,
                            name: $scope.bodaName,
                            religion: $scope.bodaReligion,
                            tema: $scope.bodaTema,
                            tipo: $scope.bodaTipo
                        //}).then(function (response) {
                        //    if (idsRegalos.length >= 0) {
                         //       $http.put(bodasContext + "/" + response.data.id + "/regalos", $scope.allRegalosBoda).then(function (response) {
                           //     });
                            //}
                            //Boda created successfully
                            }).then(function (response) {
                            $state.go('bodasList', {bodaId: response.data.id}, {reload: true});
                            
                        });
                    };

                   // $scope.newRegalos = function () { ... implementar
                      
                }
            ]);
        }
)(angular);