pipeline {
       agent any


       stages {
          stage('Build') {
             steps {
                sh 'gradle clean compileJava'
                sh './gradlew clean assemble'
             }
          }
          stage('Deploy'){
                     steps{
                         sh 'cf push shipment -p ./build/libs/shipment-0.0.1-SNAPSHOT.jar'
                     }
          }
       }
   }