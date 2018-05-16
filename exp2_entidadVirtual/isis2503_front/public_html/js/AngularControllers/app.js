aplicacionMundial.directive('competitorInfo', function(){
        return{
            restrict:'E',
            templateUrl:'partials/competitor-info.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                    $http.get('URL del Servicio').success(function(data){
                        self.competitors=data;
                    });
            }],
            controllerAs:'getCompetitors'
        };
    });
    
    