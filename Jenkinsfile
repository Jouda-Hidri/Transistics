pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment {
       DOCKERHUB_CRENDETIALS = credentials('dockerhub')
       DOCKERHUB_USERNAME = "${DOCKERHUB_CRENDETIALS_USR}"
       DOCKERHUB_PASSWORD = "${DOCKERHUB_CRENDETIALS_PSW}"
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
				sh '''
					docker build --build-arg JAR_FILE=target/app.jar -t transactions-statistics-image .
					docker tag transactions-statistics-image transactions-statistics-image:latest
					echo $DOCKERHUB_PASSWORD | docker login joudahidri/transactions-statistics-image -u $DOCKERHUB_USERNAME --password-stdin
					docker push transactions-statistics-image
					docker push transactions-statistics-image:latest
				sh '''
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