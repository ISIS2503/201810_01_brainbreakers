(function (ng) {
    var mod = ng.module("tarjetasCreditoModule");
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");

    mod.controller('tarjetasUpdateCtrl', ['$scope', '$http', 'parejasContext', 'tarjetasCreditoContext', '$state', '$rootScope', '$filter',
        function ($scope, $http, parejasContext, tarjetasCreditoContext, $state, $rootScope) {
            $rootScope.edit = true;

            $http.get(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId).then(function (response) {
                var tarjeta = response.data;
                $scope.tarjetaBanco = tarjeta.nombreBanco;
                $scope.tarjetaFechaVen = new Date(tarjeta.fechaVen);
                $scope.tarjetaNumDeSeg = tarjeta.numDeSeg;
            });
            $scope.createTarjeta = function () {
                $http.put(parejasContext + "/" + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId, {
                    nombreBanco: $scope.tarjetaBanco,
                    fechaVen: $scope.tarjetaFechaVen,
                    numDeSeg: $scope.tarjetaNumDeSeg
                }).then(function (response) {
                    $state.go('tarjetasList', {parejaId:$state.params.parejaId},{reload: true});
                });
            };
        }
    ]);
}
)(angular);