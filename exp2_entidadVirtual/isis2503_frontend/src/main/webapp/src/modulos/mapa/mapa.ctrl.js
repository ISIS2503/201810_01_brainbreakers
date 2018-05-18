(function (ng) {
    var mod = ng.module("mapaModule");
    mod.constant("mapaContext", "api/divisiones");
    mod.controller('mapaCtrl', ['$scope', '$http', 'mapaContext', '$state', '$filter',
        function ($scope, $http, mapaContext, $state, $filter) {
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

// Helper method to parse the title tag from the response.
                function getTitle(text) {
                    return text.match('<title>(.*)?</title>')[1];
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
                        
                    };

                    xhr.onerror = function () {
                        alert('Woops, there was an error making the request.');
                    };
                    xhr.addEventListener("load", function () {
                        console.log(this.responseText);
                    });
                    xhr.send();
                }
                makeCorsRequest();
            }, 3000);

        }

    ]);
})(angular);



