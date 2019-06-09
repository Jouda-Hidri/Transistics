pipeline {
    
    agent any
    
    environment {       
       registry = "joudahidri/transactions-statistics-image"
       registryCredential = 'dockerhub'
       dockerImage = ''
       DAY=sh(returnStdout: true, script: 'date +"%a"').trim()
       HOUR=sh(returnStdout: true, script: 'date +"%H"').trim() 
    }
	parameters {
		string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
	}
    stages {
	    stage('validation') {
            steps {
            	script {
	            	if ( GIT_BRANCH == 'master'
						&& (DAY == "Sun" || HOUR.toInteger() > 9) ) {
						timeout(time: 15, unit: 'SECONDS') {
							input 'Validation is required'
							echo 'Validated!'
						}
					}
	            	else {
						echo "No validation required, today is $Day time is $Hour"
	            	}
	            }
            }
	    }
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
	      steps{
	        script {
	          dockerImage = docker.build registry + ":$BUILD_NUMBER"
	        }
	      }
	    }
	    stage('Deploy Image') {
	      steps{
	        script {
	          docker.withRegistry( '', registryCredential ) {
	            dockerImage.push()
	          }
	        }
	      }
	    }
	    stage('Remove Unused docker image') {
	      steps{
	        sh "docker rmi $registry:$BUILD_NUMBER"
	      }
	    }
		stage('Deploy') { 
           steps {
                echo 'Deploy ...'
            }
        }
        stage('Post deploy') { 
           steps {
                echo 'Post deploy ...'
            }
        }
    }
}