pipeline {
	agent {
		label 'aws'
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
                    
                    ansiblePlaybook become: true, credentialsId: 'da93f539-19e1-401a-a3cd-55bfea924ba8', disableHostKeyChecking: true, inventory: 'ansible/inventory.ini', playbook: 'ansible/deploy.yml'
                } // ansible-playbook -i inventory.ini deploy.yml ansible
                }    // Stage 4
            }
        }
        
	}

}
