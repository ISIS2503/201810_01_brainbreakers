(function (ng) {
    var mod = ng.module("bodasModule");
    mod.constant("bodasContext", "api/bodas");
    mod.controller('bodaDeleteCtrl', ['$scope', '$http', 'bodasContext', '$state',
        function ($scope, $http, bodasContext, $state) {
            var idBoda = $state.params.bodaId;
            $scope.deleteBoda = function () {
                $http.delete(bodasContext + '/' + idBoda, {}).then(function (response) {
                    $state.go('bodasList', {bodaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);