pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'Github-Jenkins', url: 'git@github.com:maxiparshu/FirstHomework.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                   scp -o StrictHostKeyChecking=no target/*.war user@192.168.1.103:/opt/tomcat/webapps/
                   ssh user@192.168.1.103 "ls -l /opt/tomcat/webapps/"
                   '''
            }
        }
    }
}
