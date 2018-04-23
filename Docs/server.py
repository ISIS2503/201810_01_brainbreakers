from flask import Flask
import json


class Server(object):

    app = None

    def __init__(self):
        self.app = Flask(__name__)
        self.app.secret_key = '7q%3=;8J+X5:f.+pU9e!;6x:E*n_9^Ky0~.R'

        @self.app.route('/', methods=['GET'])
        def home():
            return json.dumps([])
		@self.app.route('/', methods=['POST'])
		def notificar():
			return json.dumps([])
