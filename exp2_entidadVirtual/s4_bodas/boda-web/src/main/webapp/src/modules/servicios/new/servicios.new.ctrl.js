(function (ng) {
    var mod = ng.module("serviciosModule");
    mod.constant("serviciosContext", "api/servicios");
    mod.controller('servicioNewCtrl', ['$scope', '$http', 'serviciosContext', '$state',  '$rootScope',
        function ($scope, $http, serviciosContext, $state,  $rootScope) {
            $rootScope.edit = false;
            $scope.createServicio = function () {
                $http.post(serviciosContext, {
                    name: $scope.servicioName,
                    descripcion: $scope.servicioDescripcion,
                    image:'https://images.pexels.com/photos/132694/pexels-photo-132694.jpeg'
                }).then(function (response) {
                    //Servicio created successfully
                    $state.go('serviciosList', {servicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);