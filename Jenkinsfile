pipeline {
	agent {
		label 'ec2'
	}

   
	stages {
		stage('Git Checkout') {
			steps {
				script {
					git  url: 'https://github.com/arjunsreepad/spboot-mysql-h2-webapp.git'
					}	
				}
			} 

        stage('Build'){
            steps {
                script {
                    sh "mvn install"
                }
            }
        }  
                
        stage("Deploy"){
            steps{
                script{
                    sh "cp target/TodoDemo-1.0.war ansible/todo.war"
                    ansiblePlaybook become: true, credentialsId: '8af1f853-ef41-4039-a069-b22d86ae4929', disableHostKeyChecking: true, inventory: 'ansible/inventory.ini', playbook: 'ansible/deploy.yml'
                }   
            }
        }
    }
}
