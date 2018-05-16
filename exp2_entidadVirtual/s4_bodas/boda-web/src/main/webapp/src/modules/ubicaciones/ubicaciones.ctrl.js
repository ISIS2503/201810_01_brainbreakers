(function (ng) {
    var mod = ng.module("ubicacionesModule");
    mod.constant("ubicacionesContext", "api/ubicaciones");
    mod.controller('ubicacionesCtrl', ['$scope', '$http', 'ubicacionesContext', '$state',
        function ($scope, $http, ubicacionesContext, $state, GoogleMapApiProviders) {
            
            $http.get(ubicacionesContext).then(function (response) {
                $scope.ubicacionesRecords = response.data;
            });
            if ($state.params.ubicacionId !== undefined) {
                $http.get(ubicacionesContext + '/' + $state.params.ubicacionId).then(function (response) {
                    $scope.currentUbicacion = response.data;
                    console.log($scope.currentUbicacion );
                    $scope.map={};
                    $scope.map = { center: { latitude: $scope.currentUbicacion.latitud, longitude: $scope.currentUbicacion.longitud }, zoom: 16 ,bounds: {},refresh:true};
                    $scope.options = {scrollwheel: false};
                    var createRandomMarker = function(bounds){
                       

                    var ret  = {
                        latitude: $scope.currentUbicacion.latitud,
                        longitude: $scope.currentUbicacion.longitud,
                        title: '</strong>Direccion:</strong> '+ $scope.currentUbicacion.direccion,
                        show: false,
                        id: $scope.currentUbicacion.id
                    };
                    return ret;
                   };
                   $scope.onClick = function(marker, eventName, model) {
                    model.show = !model.show;
                };
                    $scope.randomMarkers = [];
                    $scope.$watch(function() { return $scope.map.bounds; }, function(nv, ov) {
                    // Only need to regenerate once
                    if (!ov.southwest && nv.southwest) {
                        var markers = [];
                        markers.push(createRandomMarker( $scope.map.bounds))
                        $scope.randomMarkers = markers;
                    }
                    }, true);
                        });
                    }
        }]);
})(angular);