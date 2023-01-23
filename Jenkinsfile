pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                bat 'mvn clean - f ("bike-sharing-master/RentBike/pom.xml") compile'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
