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
                deploy adapters: [tomcat9(credentialsId: '6935815e-e80d-4392-aa5c-91d169841020', path: '', url: 'http://localhost:9000/')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
