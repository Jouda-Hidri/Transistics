pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
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
        /*
        stage ('Docker Push') {
        	steps {
				sh '''
        		mvn clean install 
        		./mvnw package
        		java -jar target/app.jar
        		docker build -t transactions-statistics-image .
        		docker login
				docker tag transactions-statistics-image joudahidri/transactions-statistics-image
				docker push joudahidri/transactions-statistics-image:latest
        		'''
            }
        }
        */
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