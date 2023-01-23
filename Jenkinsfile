pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                bat 'mvn clean install'
                bat 'mvn package'
            }
            post{
                success{
                    echo 'Archiving the artifcacts'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying on tomcat server'
            }
        }
    }
}
