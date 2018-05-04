from flask import Flask
import json
app = Flask(__name__)

@app.route('/', methods=['POST'])
#@app.run("",port=8080)
def helloWorld():
	data = []
	return json.dumps(data)
