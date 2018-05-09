import paho.mqtt.client as mqtt
import requests
import json

# The callback for when the client receives a CONNACK response from the server.
def on_connect(client, userdata, flags, rc):
    print("Connected with result code "+str(rc))

    # Subscribing in on_connect() means that if we lose the connection and
    # reconnect then subscriptions will be renewed.
    client.subscribe("zona4/alertas/#")

# The callback for when a PUBLISH message is received from the server.
def on_message(client, userdata, msg):
    print(msg.topic+" "+str(msg.payload))
    url = 'http://172.24.41.220:8080/'
    payload = {
        'correoEnvio':'fg',
        'correoDestino':'fg',
        'asunto':'fg',
        'mensaje': str(msg.payload)
    }
    response = requests.post(url,data=json.dumps(payload),headers={'Content-type': 'application/json'})
    print("ResponseStatusCode: " +str(response.status_code))

client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message

client.connect("172.24.42.29", 8083,60)

# Blocking call that processes network traffic, dispatches callbacks and
# handles reconnecting.
# Other loop*() functions are available that give a threaded interface and a
# manual interface.
client.loop_forever()
