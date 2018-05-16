(function (ng) {
    var mod = ng.module("invitadosModule");
    mod.constant("invitadosContext", "invitados");
    mod.constant("bodasContext", "api/bodas");
    mod.controller('invitadosCtrl', ['$scope', '$http', 'bodasContext', '$state', 'invitadosContext',
        function ($scope, $http, bodasContext, $state, invitadosContext) {

            $http.get(bodasContext + '/' + $state.params.bodaId + '/' + invitadosContext).then(function (response) {
                $scope.invitadosRecords = response.data;
                var a = 0;
                for (var i = 0; i < response.data.length; i++) {

                    if (response.data[i].asistencia === "Confirmado") {
                        a++;
                    }
                    $scope.rta = a;
                    $scope.otra = response.data.length-a;
                }
                ;
                
            });

            if ($state.params.invitadoId !== undefined) {
                $http.get(bodasContext + '/' + $state.params.bodaId + '/' + invitadosContext + '/' + $state.params.invitadoId).then(function (response) {
                    $scope.currentInvitado = response.data;
                });
            }

            $scope.orderInv = function (condicion, tipoCondicion) {
                $scope.tipoOrden = condicion;
                $scope.criterioOrdenar = tipoCondicion;
            };

            $scope.imgAsi = function (asistencia) {
                if (asistencia === "Confirmado") {
                    $scope.imagenAsistencia = "resources/icons/checked.png";
                } else if (asistencia === "Pendiente") {
                    $scope.imagenAsistencia = "resources/icons/stopwatch.png";
                }
            };

            $scope.imgCat = function (categoria) {
                if (categoria === "Familia") {
                    $scope.im = "resources/icons/familia.png";
                } else if (categoria === "Amigos") {
                    $scope.im = "resources/icons/couple.png";
                } else if (categoria === "Trabajo") {
                    $scope.im = "resources/icons/employee.png";
                }
            };

        }
    ]);
}
)(angular);