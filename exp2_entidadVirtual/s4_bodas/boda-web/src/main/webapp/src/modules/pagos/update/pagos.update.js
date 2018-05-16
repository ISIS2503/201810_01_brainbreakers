(function (ng) {
    var mod = ng.module("tarjetasCreditoModule");
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");
    mod.constant("pagosContext", "pagos");

    mod.controller('pagosUpdateCtrl', ['$scope', '$http', 'parejasContext', 'tarjetasCreditoContext', 'pagosContext', '$state', '$rootScope', '$filter',
        function ($scope, $http, parejasContext, tarjetasCreditoContext, pagosContext, $state, $rootScope) {
            $rootScope.edit = true;

            $http.get(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId + '/' + pagosContext + '/' + $state.params.pagoId).then(function (response) {
                var pago = response.data;
                $scope.pagoMontoTotal = pago.montoTotal;
                $scope.pagoFecha = new Date(pago.fecha);
                $scope.pagoNombrePago = pago.nombrePago;
            });
            $scope.updatePago = function () {
                $http.put(parejasContext + "/" + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId + '/' + pagosContext + '/' + $state.params.pagoId, {
                    montoTotal: $scope.pagoMontoTotal,
                    fecha: $scope.pagoFecha,
                    nombrePago: $scope.pagoNombrePago,
                }).then(function (response) {
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);