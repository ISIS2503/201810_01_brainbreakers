(function (ng) {
    var mod = ng.module("mapaModule");
    mod.constant("mapaContext", "api/divisiones");
    mod.controller('mapaCtrl', ['$scope', '$http', 'mapaContext', '$state', '$filter',
        function ($scope, $http, mapaContext, $state, $filter) {
            $http.get(mapaContext).then(function (response) {
                $scope.bodasRecords = response.data;
            });
            
            if ($state.params.bodaId !== undefined) {
                $http.get(mapaContext + '/' + $state.params.bodaId).then(function (response) {
                   
                    $scope.currentBoda = response.data;
                    $scope.currentFecha = $scope.currentBoda.fecha;
                   
                        
                    var estadoDetail = 'bodaDetail';
                    if ($state.current.name === estadoDetail) {
                        
                        try {
                             var fechita = $filter('date')($scope.currentBoda.fecha, 'medium', '');
                            var countDownDate = new Date(fechita).getTime();
                            var x = setInterval(function () {
                                if($state.current.name !== estadoDetail )
                                {
                                    clearInterval(x);
                                }                           
                               
                                var now = new Date().getTime();
                                var distance = countDownDate - now;
                                var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                                document.getElementById("demo").innerHTML = days + "días " + hours + "horas " + minutes + "minutos " + seconds + "s ";

                                if (distance < 0) {
                                    clearInterval(x);
                                    document.getElementById("demo").innerHTML = "Aún puedes aplazar la fecha";
                                }
                                
                            }, 1000);
                        } catch (err) {
                        }
                    }
                });


            }

        }]);
})(angular);



