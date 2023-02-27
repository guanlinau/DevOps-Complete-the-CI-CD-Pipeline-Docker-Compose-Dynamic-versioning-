def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 
def incrementVersion() {
   sh 'mvn build-helper:parse-version versions:set \
   -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
   versions:commit'
   def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
   def version = matcher[0][1]
   env.IMAGE_NAME = "jason8746/my-app:$version-java-multi-pipeline-amd64-$BUILD_NUMBER"
   echo "something"
   echo "${IMAGE_NAME}"
}

def commitVersionUpdate(){
    withCredentials([usernamePassword(credentialsId: 'Jenkins-github-pat', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'git config --global user.email "jason.guanlin.cao@gmail.com"'
        sh 'git config --global user.name "jason"'
        sh 'git status'
        sh 'git branch'

        sh "git remote set-url origin https://${USER}:${PASS}@github.com/CGL-DevOps/DevOps-Complete-the-CI-CD-Pipeline-Docker-Compose-Dynamic-versioning-.git"
        sh 'git add .'
        echo "${USER} ${PASS}"
        sh 'git commit -m "ci:version bump ${BUILD_NUMBER}"'
        sh 'git push origin HEAD:master'
    }
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'Docker-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ${IMAGE_NAME} .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push ${IMAGE_NAME}'
    }
} 

def deployApp() {
    echo 'deploying the application by docker-compose...'
    
    // def copyDocker_composeCMD = 
    // def dockerComposeCMD = 'docker-compose up -d'
    def runServerCmd = "bash ./server-cmd.sh ${IMAGE_NAME}" 
    sshagent(['ec2-server']) {
        sh 'scp docker-compose.yaml ec2-user@3.25.180.251:/home/ec2-user'
        // sh 'scp docker-compose.yaml ec2-user@3.25.180.251:~/'
        sh 'scp server-cmd.sh ec2-user@3.25.180.251:/home/ec2-user'
        sh "ssh -o StrictHostkeyChecking=no ec2-user@3.25.180.251 ${runServerCmd}"
    } 
}

def masterBranch(){
    BRANCH_NAME == "master"
}

return this
