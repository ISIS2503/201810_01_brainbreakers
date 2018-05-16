(function (ng) {
    var mod = ng.module("regalosModule");
    mod.constant("regalosContext", "regalos");
    mod.constant("bodasContext", "api/bodas");
    mod.controller('regalosCtrl', ['$scope', '$http', 'bodasContext', '$state', 'regalosContext',
        function ($scope, $http, bodasContext, $state, regalosContext) {
            $http.get(bodasContext + '/' + $state.params.bodaId + '/' + regalosContext).then(function (response) {
                $scope.regalosRecords = response.data;
            });
            if ($state.params.regaloId !== undefined) {
                $http.get(bodasContext + '/' + $state.params.bodaId + '/' + regalosContext + '/'+ $state.params.regaloId).then(function (response) {
                    $scope.currentRegalo = response.data;
                });   
            }
            
                $scope.imgComprado = function (comprado) {
                if (comprado === false) {
                    $scope.imagenComprado = "resources/icons/cancel.png";
                } else if (comprado === true) {
                    $scope.imagenComprado = "resources/icons/checked.png";
                }
            };
            
            var idRegalo = $state.params.regaloId;
            var idBoda = $state.params.bodaId;
            $scope.createUbicacion = function (idUbi) {
                $http.post('api/bodas/' + idBoda + '/regalos/' + idRegalo +'/ubicaciones/' + idUbi).then(function () {
                    $state.go('regaloDetail', {idRegalo}, {reload: true});
                });
            };

            $scope.agregar = function () {
                swal({
                    title: 'Agregar ubicacion',
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
                    if (!Number.isNaN(result)) {
                        console.log(result);
                        $scope.createUbicacion(result);
                        swal({
                            type: 'success',
                            title: 'Ubicacion agregada',
                            html: 'Ubicacion agregada: ' + result
                        });
                    }


                });
            };
            
        }
    ]);
}
)(window.angular);