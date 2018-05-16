(function (ng) {
    var mod = ng.module("tarjetasCreditoModule");
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");
    mod.controller('tarjetasCreditoCtrl', ['$scope', '$http', 'parejasContext', '$state', 'tarjetasCreditoContext',
        function ($scope, $http, parejasContext, $state, tarjetasCreditoContext) {
            $http.get(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext).then(function (response) {
                $scope.tarjetasCreditoRecords = response.data;
            });
            if (($state.params.tarjetaId !== undefined) && ($state.params.tarjetaId !== null)) {
                $http.get(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId).then(function (response) {
                    $scope.currentTarjeta = response.data;
                });
            }
        }
    ]);
}
)(angular);

