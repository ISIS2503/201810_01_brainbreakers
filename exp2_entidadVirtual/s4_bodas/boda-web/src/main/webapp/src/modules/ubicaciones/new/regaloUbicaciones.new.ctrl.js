(function (ng) {
    var mod = ng.module("ubicacionesModule");
    mod.controller('regaloUbicacionNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
            $rootScope.edit = false;
            var idRegalo = $state.params.regaloId;
            var idBoda = $state.params.bodaId;
            $scope.createUbicacion = function (idUbi) {
                $http.post('api/bodas' + idBoda + '/regalos/' + idRegalo, +'ubicaciones/' + idUbi).then(function () {
                    $state.go('regalosList', {idBoda}, {reload: true});
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
)(angular);

