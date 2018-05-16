(function (ng) {
    var mod = ng.module("invitadosModule");
    mod.constant("invitadosContext", "invitados");
    mod.constant("bodasContext", "api/bodas");
    mod.controller('invitadosNewCtrl', ['$scope', '$http', 'bodasContext', '$state',  '$rootScope','invitadosContext',
        function ($scope, $http, bodasContext, $state,  $rootScope, invitadosContext) {
            $rootScope.edit = false;
            $scope.createInvitado = function () {
                $http.post(bodasContext + '/' + $state.params.bodaId + '/' + invitadosContext , {
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


