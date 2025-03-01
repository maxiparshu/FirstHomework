pipeline {
    agent any

    environment {
        TOMCAT_USER = 'user'  // Твой пользователь на сервере
        TOMCAT_HOST = '192.168.1.111'  // IP адрес сервера
        TOMCAT_PORT = '8085'
        TOMCAT_WEBAPPS = '/opt/tomcat/webapps/'
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
        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Копируем WAR файл в папку webapps на сервере Tomcat
                    sh "scp ${RENAMED_FILE} ${TOMCAT_USER}@${TOMCAT_HOST}:${TOMCAT_WEBAPPS}"
                    // Перезапускаем Tomcat, чтобы он развернул новый WAR
                    sh "ssh ${TOMCAT_USER}@${TOMCAT_HOST} 'sudo systemctl restart tomcat'"
                }
            }
        }
    }

    post {
        always {
            // Можно добавить очистку или другие действия после деплоя
            echo 'Deployment finished'
        }
    }
}
