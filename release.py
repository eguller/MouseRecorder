import getpass
import subprocess
import argparse
from xml.dom.minidom import parse


class Release:
    def __init__(self, username, password):
        self.username = username
        self.password = password
        self.default_branch = "deploy_script"
        self.release_prefix = "v"

    def isRightBranch(self):
        output = subprocess.check_output(["git", "rev-parse", "--abbrev-ref", "HEAD"])
        output = output.strip(' \t\n\r')
        return output == self.default_branch

    def currentVersion(self):
        dom = parse("pom.xml")
        versionElement = dom.getElementsByTagName('version')
        return versionElement[0].firstChild.nodeValue

    def tagName(self):
        return self.release_prefix + self.currentVersion()

    def tagExists(self):
        tagName = self.tagName()
        output = subprocess.check_output(["git", "tag"])
        tags = output.split("\n")
        for tag in tags:
            _tag = tag.strip(' \t\n\r')
            if tagName == _tag:
                return True
        return False


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
        tagName = release.tagName()
        if release.tagExists():
            print tagName + " exists. Will use tag"
        else:
            print tagName + " does not exist. " + tagName + " will be created"
            #print "right branch"
            #s = requests.Session()
            #s.auth = (username, password)
            #data = {"tagname": "testtag", "name": "test release", "body" : "testrelease"}
            #auth = HTTPBasicAuth(username, password)
            #headers = {"Accept": "application/vnd.github.v3+json"}
            #r = requests.post("https://api.github.com/repos/eguller/MouseRecorder/releases", data=json.dumps(data), auth=auth, headers=headers);
            #print r.status_code
            #print r.json()