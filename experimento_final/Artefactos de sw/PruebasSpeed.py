from kafka import KafkaConsumer
import requests
import json
consumer= KafkaConsumer('torre1.1-201',
                        group_id='my-group',
                        bootstrap_servers=['172.24.42.28:8090'])
for message in consumer:
                            url = "http://172.24.42.42:8080/notificarPorCorreo"
                            payload = {
                                'correoEnvio':'fg',
                                'correoDestino':'fg',
                                'asunto':'fg',
                                'mensaje': message.value.decode('utf-8')
                            }
                            response = requests.post(url,data=json.dumps(payload),headers={'Content-type': 'application/json'})
                            print(message.topic)
                            print("ResponseStatusCode: " +str(response.status_code))
                            print(message.value.decode('utf-8'))
