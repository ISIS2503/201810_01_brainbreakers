(function (ng) {
    var mod = ng.module("tarjetasCreditoModule");
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");
    mod.controller('tarjetasCreditoDeleteCtrl', ['$scope', '$http', 'parejasContext', 'tarjetasCreditoContext', '$state', '$rootScope',
        function ($scope, $http, parejasContext, tarjetasCreditoContext, $state, $rootScope) {
            $scope.deleteTarjetaCredito = function () {
                if ($rootScope.currentUser.rol === 'pareja') {
                    $http.delete(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId, {}).then(function () {
                    });
                }
                else if($rootScope.currentUser.rol === 'admin')
                {
                    $http.delete(parejasContext + '/' + $state.params.parejaId + '/' + tarjetasCreditoContext + '/' + $state.params.tarjetaId, {}).then(function () {
                    });
                }
            };
        }
    ]);
}
)(angular);

