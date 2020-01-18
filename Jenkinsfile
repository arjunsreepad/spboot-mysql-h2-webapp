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
			}  // Stage 1




        stage('Build'){
            steps {
                script {
                    sh """
					    mvn install
					"""
                }
            }
        }  
        
        
        stage("Deploy"){
            steps{
                dir("ansible"){
                
    
                script{
                    
                    ansiblePlaybook become: true, credentialsId: '8af1f853-ef41-4039-a069-b22d86ae4929', disableHostKeyChecking: true, inventory: 'ansible/inventory.ini', playbook: 'ansible/deploy.yml'
                } // ansible-playbook -i inventory.ini deploy.yml ansible
                }    // Stage 4
            }
        }
        
	}

}
