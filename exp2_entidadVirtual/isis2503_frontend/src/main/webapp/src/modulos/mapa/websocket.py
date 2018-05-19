import json
import time
import threading
from flask import Flask, render_template, Response
from kafka import KafkaConsumer
from flask_cors import CORS, cross_origin
 
# Set this variable to "threading", "eventlet" or "gevent" to test the
async_mode = None
 
app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret_thermalcomfort'
current_mesurement = ''
 
# Rutina que se ejecuta antes de la primera solicitud realizada por un cliente
# a un servicio REST. Inicia el consumidor del topic de Kafka "alta.piso1.local1"
# y asigna el último valor a la variable global "current_mesurement".
@app.before_first_request
def activate_job():
    def background_thread_rest():
        consumer = KafkaConsumer('Iot_topic', group_id='temperature', bootstrap_servers=['localhost:8090'])
        for message in consumer:
            json_data = json.loads(message.value.decode('utf-8'))
            sensetime = json_data.sensetime
            sense = json_data.temperature
 
            payload = {
                'time': sensetime,
                'value': sense
            }
            global current_mesurement
            current_mesurement = payload
    thread = threading.Thread(target=background_thread_rest)
    thread.start()
 
# Ruta del dashboard
@app.route('/')
def index():
    return render_template('index_rest.html')
 
# Ruta del servicio REST que permite obtener (GET) la último valor de la medición
@app.route('/mesurements', methods=['GET'])
@cross_origin()
def get_mesurement():
    global current_mesurement
    response = Response(content_type='application/json', status=200,response=json.dumps(current_mesurement))
    current_mesurement =''
    return response
 
# Iniciar el servicio en el puerto 8086
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8086, debug=True)