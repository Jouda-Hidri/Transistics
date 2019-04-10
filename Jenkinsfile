pipeline {
    
    agent any
    
    environment {       
       registry = "joudahidri/transactions-statistics-image"
       registryCredential = 'dockerhub'
    }
	parameters {
		string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
	}
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
		stage('Building image') {
			steps {
				script {
					docker.build registry + ":$BUILD_NUMBER"
        		}
			}
		}
		stage('Deploy') {
			steps{
					timeout(time: 15, unit: 'SECONDS') {
					echo "${params.Greeting} World!"
					input 'Deploy?'
					echo 'Deploying..'
				}
			}
        }
        stage('Post deploy') { 
           steps {
                echo 'Post deploy ...'
            }
        }
    }
}