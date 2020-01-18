pipeline {
	agent {
		label 'AWS'
	}


    environment {
        // This can be nexus3 or nexus2
        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running
        NEXUS_URL = "repository.emirates.com"
        // Repository where we will upload the artifact
        NEXUS_REPOSITORY = "cicd"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIAL_ID = "GirGenericPwd"
    }
   
	stages {
		stage('Git Checkout') {
			steps {
				script {
					git credentialsId: 'GitGeneticSSH', url: 'ssh://git@git.emirates.com/talktech/springboot-helloworld.git'
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
        }  // Stage 2
					

        /* stage('Nexus Upload'){
            steps{
             withCredentials([
                        [$class: 'UsernamePasswordMultiBinding', credentialsId: "GirGenericPwd", passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME']
                ]) {
                    script {
                        sh "curl -v -u $USERNAME:$PASSWORD  --upload-file target/helloworld-1.0.jar http://repository.emirates.com/repository/cicd/aws/springboot/helloworld.jar"
                    }

                }
            }
        } */
stage("publish to nexus") {
            steps {
                script {
                    // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                    pom = readMavenPom file: "pom.xml";
                    // Find built artifact under target folder
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    // Print some info from the artifact found
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    // Extract the path from the File found
                    artifactPath = filesByGlob[0].path;
                    // Assign to a boolean response verifying If the artifact name exists
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                // Artifact generated such as .jar, .ear and .war files.
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                // Lets upload the pom.xml file for additional information for Transitive dependencies
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }  // Stage 3
        
        
        stage("Deploy"){
            steps{
                dir("ansible"){
                input 'Is environment provisioned via Terraform Already? Can we go ahead and Deploy ?'
    
                script{
                    sh '''
                    curl http://repository.emirates.com/repository/cicd/orbartal/helloworld/1.0/helloworld-1.0.jar -o helloworld.jar
                   ansible-playbook -i inventory.ini deploy.yml
                    '''
                } // ansible-playbook -i inventory.ini deploy.yml ansible
                }    // Stage 4
            }
        }
        
	}

}
