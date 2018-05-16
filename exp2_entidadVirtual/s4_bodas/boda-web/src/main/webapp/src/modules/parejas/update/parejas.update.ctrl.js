(
        function (ng) {
            var mod = ng.module("parejasModule");          
            mod.constant("parejasContext", "api/parejas"); 
            mod.controller('parejaUpdateCtrl', ['$scope', '$http', 'parejasContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, parejasContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    
                    //Inicializo el data que se muestra en el formulario
                    $scope.data ={};
                   
                   //Obtengo el id de la pareja el cual debio pasarse en parejasList 
                   var idPareja= $state.params.parejaId;
                   
                   //Pido los datos de la pareja para mostrarlos y que el rol administrador pueda determinar modificarlos
                   $http.get(parejasContext + '/' + idPareja ).then(function(response){
                       var pareja= response.data;
                       
                     $scope.data.correoElec = pareja.correoElec;
                     $scope.data.contrasenia= pareja.contrasenia;
                     $scope.data.nombreInd1= pareja.nombreInd1;
                     $scope.data.nombreInd2= pareja.nombreInd2;
                     $scope.data.nombreAbreviado= pareja.nombreAbreviado;
                     $scope.data.direccion= pareja.direccion;
                     $scope.data.telefono= pareja.telefono;
                   });
                   
                   //Cuando se da submit en el formulario de crearParejas
                   //Llama a crear pareja, aqui reescribimos esa función
                   $scope.createPareja = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */
                        
                        $http.put(parejasContext + '/' + idPareja , $scope.data).then(function (response) {
                            
                            //la Pareja fue creada 
                             swal("Actualizada!", "La Pareja fue "+ idPareja+ " modificada.", "success");
                            $state.go('parejasList', {parejaId: response.data.correElec}, {reload: true});
                        });
                    };

                   
                }
            ]);
        }
)(angular);