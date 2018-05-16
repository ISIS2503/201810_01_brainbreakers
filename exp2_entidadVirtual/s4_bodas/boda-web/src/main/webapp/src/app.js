(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'uiGmapgoogle-maps',
        //'ui.bootstrap',
        // Internal modules dependencies   
        'bodasModule',

        'tarjetasCreditoModule',

        'pagosModule',

        'serviciosModule',

        'proveedoresModule',

        'ubicacionesModule',

        'opcionesModule',

        'tareasModule',

        'regalosModule',

        'invitadosModule',

        'calificaionesModule',
        'parejasModule',
        'loginModule'

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', 'uiGmapGoogleMapApiProvider', function ($qProvider, uiGmapGoogleMapApiProvider) {
            $qProvider.errorOnUnhandledRejections(false);
            uiGmapGoogleMapApiProvider.configure({
                china:true,
                key:'AIzaSyCnye9NF09bP8ZPLRTqbATIn9Yn_bYsPrg',
                libraries: 'weather,geometry,visualization',
                v: '3.25'
            });
        }]);
    app.run(['$rootScope', '$transitions', function ($rootScope, $transitions) {



            $transitions.onSuccess({to: '*'}, function (trans) {


                var $state = trans.router.stateService;
                var requireLogin = $state.current.data.requireLogin
                var roles = $state.current.data.roles


                $rootScope.isAuthenticated = function () {

                    if (sessionStorage.getItem("username") != null) {
                        $rootScope.currentUser = {name: sessionStorage.getItem("name"), rol: sessionStorage.getItem("rol"), username: sessionStorage.getItem("username")};
                        return true;
                    } else {
                        return false;
                    }
                };

                $rootScope.hasPermissions = function () {
                    if (($rootScope.isAuthenticated) && (roles.indexOf(sessionStorage.getItem("rol")) > -1)) {
                        return true;
                    } else {
                        return false;
                    }
                };


                if (requireLogin && (sessionStorage.getItem("username") === null)) {
                    event.preventDefault();
                    $state.go('login', $state.params);
                }

            });

        }]);
})(window.angular);

jQuery(document).on('ready', function ($) {
    "use strict";

    /*----------------------------
     LOADING
     ------------------------------*/
    $(window).load(function () {
        $(".loader").fadeOut("slow");
    });

}(jQuery));
