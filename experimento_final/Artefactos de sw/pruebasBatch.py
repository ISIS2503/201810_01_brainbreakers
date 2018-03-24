from kafka import KafkaConsumer
import requests
import json
consumer= KafkaConsumer('torre1.1-201',
                        group_id='my-group',
                        bootstrap_servers=['172.24.42.28:8090'])

for message in consumer:
                            print("Llego !!!!")
                            url = "http://localhost:8080/sensors/TAS01/measurements"
                            payload = {

                                'name': message.value.decode('utf-8')
                            }

                            response = requests.post(url,data=json.dumps(payload),headers={'Content-type': 'application/json'})

                            print("Peticion1 ResponseStatusCode: " +str(response.status_code))


                            url = "http://localhost:8080/rooms/TAR01/consolidateddata"
                            payload = {
                                'id': message.value.decode('utf-8')
                            }
                            response = requests.post(url,data=json.dumps(payload),headers={'Content-type': 'application/json'})

                            print("Peticion2 ResponseStatusCode: " +str(response.status_code))
