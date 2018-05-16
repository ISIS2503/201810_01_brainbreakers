(function (ng) {
    var mod = ng.module("ubicacionesModule");
    mod.constant("ubicacionesContext", "api/ubicaciones");
    mod.controller('ubicacionDeleteCtrl', ['$scope', '$http', 'ubicacionesContext', '$state',
        function ($scope, $http, ubicacionesContext, $state) {
            var idUbicacion = $state.params.ubicacionId;
            $scope.deleteUbicacion = function () {
                $http.delete(ubicacionesContext + '/' + idUbicacion, {}).then(function (response) {
                    $state.go('ubicacionesList', {ubicacionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);