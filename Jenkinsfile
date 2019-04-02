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
                // sh 'mvn -B -DskipTests clean package'
                echo "build ..."
            }
        }
        stage('Test') {
            steps {
                // sh 'mvn test'
                echo "test ..."
            }
            post {
                always {
                   // junit 'target/surefire-reports/*.xml'
                   echo "post test ..."
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