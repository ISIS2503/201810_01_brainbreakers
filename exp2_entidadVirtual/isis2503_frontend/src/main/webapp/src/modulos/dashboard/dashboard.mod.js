(function (ng) {
    // Definición del módulo
    var mod = ng.module("dashboardModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modulos/dashboard/';

            $urlRouterProvider.otherwise("/dashboard");


            $stateProvider.state('dashboard', {
                url: '/dashboard',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'dashboard.html',
                        controller: 'dashboardCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

