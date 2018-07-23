#! /usr/bin/env python3

import os
import subprocess

from jinja2 import Template


def get_root_dir():
    return os.path.abspath(os.path.join(__file__, os.pardir, os.pardir))


def get_applications():
    result = subprocess.run(['bash', 'for_each_app', 'echo'], stdout=subprocess.PIPE)
    for app in bytes.decode(result.stdout).split("\n"):
        if app != '':
            yield app


def build_ci_file():
    with open('tools/.gitlab-ci.template.yml') as f:
        template = Template(f.read())
    apps = list(get_applications())
    print(template.render(apps=apps))


if __name__ == '__main__':
    os.chdir(get_root_dir())
    build_ci_file()
