(function (ng) {
    var mod = ng.module("mapaModule");
    mod.constant("mapaContext", "api/divisiones");
    mod.controller('mapaCtrl', ['$scope', '$http', 'mapaContext', '$state', '$filter',
        function ($scope, $http, mapaContext, $state, $filter) {
            var alertas = [];
            $scope.alertas = [];

            function agregar(elemento) {
                alertas.push(elemento);
            }
            setInterval(function () {

                // Create the XHR object.
                function createCORSRequest(method, url) {
                    var xhr = new XMLHttpRequest();
                    if ("withCredentials" in xhr) {
                        // XHR for Chrome/Firefox/Opera/Safari.
                        xhr.open(method, url, true);
                    } else if (typeof XDomainRequest != "undefined") {
                        // XDomainRequest for IE.
                        xhr = new XDomainRequest();
                        xhr.open(method, url);
                    } else {
                        // CORS not supported.
                        xhr = null;
                    }
                    return xhr;
                }

                // Make the actual CORS request.
                function makeCorsRequest() {
                    // This is a sample server that supports CORS.
                    var url = 'http://localhost:8086/mesurements';

                    var xhr = createCORSRequest('GET', url);
                    if (!xhr) {
                        alert('CORS not supported');
                        return;
                    }

                    // Response handlers.
                    xhr.onload = function () {
                        console.log(this.responseText);
                    };

                    xhr.onerror = function () {
                        alert('Woops, there was an error making the request.');
                    };
                    xhr.addEventListener("load", function () {
                        //console.log(this.responseText);
                        console.log(this.responseText);
                        if (this.responseText.length != 2) {
                            var json = {};
                            json.temperature = this.responseText.time;
                            json.sensetime = this.responseText.value;
                            agregar(json);
                        }
                        //console.log($scope.alertas);
                    });

                    xhr.send();
                }
                $scope.alertas = alertas;
              // makeCorsRequest();
            }, 3000);

        }

    ]);
})(window.angular);



