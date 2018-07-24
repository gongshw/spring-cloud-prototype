#!/usr/bin/env python

import os
import subprocess

from urllib.parse import urlparse

for k, v in os.environ.items():
    print(k, ':', v)

app_name = os.environ['APP_NAME']
port = os.environ['SERVER_PORT']

print('starting', app_name)

start_app = [
    'java',
    '-Djava.security.egd=file:/dev/./urandom ',
    '-Dserver.port={}'.format(port),
    '-jar',
    '/app.jar'
]

config_server = urlparse(os.environ['CONFIG_SERVER'])

if config_server.hostname == 'localhost':
    print('NO external CONFIG_SERVER set, start app directly.')
    subprocess.call(start_app)
else:
    print('waiting for CONFIG_SERVER up.')
    wait_for_config = [
        'bash',
        '/wait-for-it.sh',
        '{}:{}'.format(config_server.hostname, 80 if config_server.port is None else config_server.port),
        '-t', '120',
        '--strict',
        '--'
    ]
    subprocess.call(wait_for_config + start_app)
