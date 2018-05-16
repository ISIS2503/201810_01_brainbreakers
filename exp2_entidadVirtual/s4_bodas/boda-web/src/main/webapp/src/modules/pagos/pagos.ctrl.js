(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext", "pagos");
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");
    mod.controller('pagosCtrl', ['$scope', '$http','parejasContext', 'tarjetasCreditoContext', '$state', 'pagosContext',
        function ($scope, $http, parejasContext, tarjetasCreditoContext, $state, pagosContext) {
                $http.get(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId + '/' + pagosContext)
                    .then(function (response) {
                    $scope.pagosRecords = response.data;
            });
            if (($state.params.pagoId !== undefined) && ($state.params.pagoId !== null)) {
                $http.get(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId + '/' + pagosContext + '/' + $state.params.pagoId).then(function (response) {
                    $scope.currentPago = response.data;
                });
            }
    }
    ]);
}
)(angular);