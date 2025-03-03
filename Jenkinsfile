pipeline {
    agent any

    environment {
        TOMCAT_USER = 'user'  // Твой пользователь на сервере
        TOMCAT_HOST = '192.168.1.111'  // IP адрес сервера
        TOMCAT_PORT = '8085'
        TOMCAT_WEBAPPS = '/opt/tomcat/webapps/'
        PRIVATE_KEY = '"C:\\Program Files (x86)\\Jenkins\\.ssh\\jenkins_ssh_key"'
    }

    stages {
        stage('Check User') {
            steps {
                script {
                    sh "whoami"
                }
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'Github-Jenkins', url: 'git@github.com:maxiparshu/FirstHomework.git'
            }
        }
        
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package'  // или другая команда для сборки, если у тебя Gradle
                }
            }
        }
        stage('Rename WAR File') {
            steps {
                script {
                    // Переименовываем WAR файл
                    WAR_FILE = sh(script: 'echo target/*.war', returnStdout: true).trim()
                    RENAMED_FILE = "target/firsthomework.war"  // Новое имя файла
                    sh "mv ${WAR_FILE} ${RENAMED_FILE}"  // Переименовываем файл
                }
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                script {
                    sshagent(['jenkins-ssh-key']) { // Убедитесь, что добавили ключ SSH в Jenkins
                        sh """
                            ssh -o StrictHostKeyChecking=no ${SERVER_USER}@${SERVER_HOST} -p ${SERVER_SSH_PORT} << 'EOF'
                            echo "Stopping Tomcat..."
                            ${TOMCAT_DIR}/bin/shutdown.sh || echo "Tomcat is not running"

                            echo "Removing old WAR file..."
                            rm -rf ${DEPLOY_PATH}/${RENAMED_FILE} ${DEPLOY_PATH}/${WAR_NAME%.war}

                            echo "Copying new WAR file..."
                            scp -P ${SERVER_SSH_PORT} ${RENAMED_FILE} ${SERVER_USER}@${SERVER_HOST}:${DEPLOY_PATH}/

                            echo "Starting Tomcat..."
                            ${TOMCAT_DIR}/bin/startup.sh
                            EOF
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            
            echo 'Deployment finished'
        }
    }
}
