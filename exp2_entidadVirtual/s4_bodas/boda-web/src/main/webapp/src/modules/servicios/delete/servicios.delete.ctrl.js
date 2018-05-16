(function (ng) {
    var mod = ng.module("serviciosModule");
    mod.constant("serviciosContext", "api/servicios");
    mod.controller('servicioDeleteCtrl', ['$scope', '$http', 'serviciosContext', '$state',
        function ($scope, $http, serviciosContext, $state) {
            var idServicio = $state.params.servicioId;
            $scope.deleteServicio = function () {
                $http.delete(serviciosContext + '/' + idServicio, {}).then(function (response) {
                    $state.go('serviciosList', {servicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);