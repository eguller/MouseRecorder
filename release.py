import getpass
import subprocess
import argparse
from xml.dom.minidom import parse
import json

import requests
from requests.auth import HTTPBasicAuth


class Release:
    def __init__(self, username, password):
        self.username = username
        self.password = password
        self.default_branch = "master"
        self.release_prefix = "v"
        self.projectName = "MouseRecorder"
        self.tagName = self.tagName()

    def isRightBranch(self):
        output = subprocess.check_output(["git", "rev-parse", "--abbrev-ref", "HEAD"])
        output = output.strip(' \t\n\r')
        return output == self.default_branch

    def currentVersion(self):
        dom = parse("pom.xml")
        versionElement = dom.getElementsByTagName('version')
        return versionElement[0].firstChild.nodeValue

    def tagName(self):
        return self.currentVersion()

    def releaseName(self):
        return self.release_prefix + self.tagName

    def localTagExists(self):
        tagName = self.tagName
        output = subprocess.check_output(["git", "tag"])
        tags = output.split("\n")
        for tag in tags:
            _tag = tag.strip(' \t\n\r')
            if tagName == _tag:
                return True
        return False

    def releaseExists(self):
        release = self.releaseName()
        response = self.get("releases", {})
        respJson = response.json()
        for releaseJson in respJson:
            if releaseJson["name"] == release:
                return True
        return False

    def createAndPushTag(self):
        self.createTag()
        self.pushTag()

    def createRelease(self):
        data = {"tag_name": self.tagName, "target_commitish": self.default_branch, "name": self.releaseName(),
                "body": "Version " + self.tagName, "draft": False, "prerelease": False}
        response = self.post("releases", data)
        if response.status_code == 201:
            id = response.json["id"]
            files = ["MouseRecorder.exe", "MouseRecorder.dmg", "MouseRecorder.jar"]
            for file in files:
                self.upload(id, file, "target/" + file)
        else:
            print "Creating release failed. Status code: " + response.status_code

    def createTag(self):
        subprocess.call(["git", "tag", "-a", tagName, "-m", "Version " + tagName])

    def pushTag(self):
        subprocess.call(["git", "push", "origin", self.default_branch, tagName])

    def post(self, call, data, headers={}):
        headers = {"Accept": "application/vnd.github.v3+json"}
        auth = HTTPBasicAuth(username, password)
        r = requests.post("https://api.github.com/repos/" + self.username + "/" + self.projectName + "/" + call,
                          data=json.dumps(data), auth=auth, headers=headers);
        return HttpResponse(r.status_code, r.json())

    def get(self, call, data, headers={}):
        headers = {"Accept": "application/vnd.github.v3+json"}
        auth = HTTPBasicAuth(username, password)
        r = requests.get("https://api.github.com/repos/" + self.username + "/" + self.projectName + "/" + call,
                         params=data, auth=auth, headers=headers);
        return HttpResponse(r.status_code, r.json())

    def upload(self, id, assetName, assetPath):
        files = {"file": open(assetPath, "rb")}
        auth = HTTPBasicAuth(username, password)
        headers = {"Content-Type": "application/octet-stream", "Accept": "application/vnd.github.manifold-preview"}
        r = requests.post(
            "https://uploads.github.com/repos/" + self.username + "/" + self.projectName + "/releases/" + str(
                id) + "/assets?name=" + assetName, auth=auth, headers=headers, files=files, verify=True)
        if r.status_code == 201:
            print assetName + " is uploaded succesfully"
        else:
            print "Upload failed. Status Code: " + r.status_code


class HttpResponse:
    def __init__(self, status_code, json):
        self.status_code = status_code
        self.json = json


if __name__ == '__main__':

    parser = argparse.ArgumentParser(prog='PROG', usage='%(prog)s [options]', description='Makes a release on github.')
    parser.add_argument('-u', '--user', help='github user name')
    parser.add_argument('-p', '--password', help='github password')
    args = parser.parse_args()

    args = parser.parse_args()
    if args.user is None:
        username = raw_input("Github username: ");
    else:
        username = args.user

    if args.password is None:
        password = getpass.getpass("Github password");
    else:
        password = args.password

    release = Release(username, password)
    rightBranch = release.isRightBranch()

    if release.isRightBranch():
        tagName = release.tagName
        if release.localTagExists():
            print tagName + " exists. Will use tag"
            if release.releaseExists():
                print release.releaseName() + " release exists"
        else:
            print "Tag " + tagName + " does not exist. Tag " + tagName + " will be created"
            release.createAndPushTag()
            print "Tag " + tagName + " is created."
            print "Release " + release.releaseName() + " will be created"
            release.createRelease()
            print "Release " + release.releaseName() + " is created"
    else:
        print "Releases must be done from " + release.default_branch