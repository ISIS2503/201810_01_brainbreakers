(
        function (ng) {
            var mod = ng.module("bodasModule");
            mod.constant("regalosContext", "regalos");
            mod.constant("bodasContext", "api/bodas");
            mod.controller('regalosUpdateCtrl', ['$scope', '$http', 'bodasContext', '$state', '$rootScope', 'regalosContext',
                function ($scope, $http, bodasContext, $state, $rootScope, regalosContext ) {
                    $rootScope.edit = true;

                    var idBoda = $state.params.bodaId;
                    
                    var idReg = $state.params.regaloId;


                    $http.get(bodasContext + "/" + idBoda + "/" + regalosContext + "/" + idReg).then(function (response) {
                        var reg = response.data;
                        $scope.regaloName = reg.name;
                        $scope.regaloImagen = reg.imagen;
                        $scope.regaloComprado = reg.comprado;
                        
                    });
                    



                    $scope.createRegalo = function () {

                        $http.put(bodasContext + "/" + idBoda + "/" + regalosContext+"/"+ idReg, {
                            name: $scope.regaloName,
                            imagen: $scope.regaloImagem,
                            comprado: $scope.regaloComprado

                            }).then(function (response) {
                            $state.go('regalosList',{regaloId: response.data.id}, {reload: true});
                            
                        });
                    };


                      
                }
            ]);
        }
)(angular);


