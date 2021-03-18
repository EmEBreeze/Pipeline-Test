pipeline {
  agent any
  stages {

    stage('Build') {
      steps {
        echo 'Building...'
        sh 'cd seleniumgenc && mvn clean package -DskipTests'
        archiveArtifacts artifacts: '**/target/*.jar'
      }
    }

    stage('Test') {
      steps {
        echo 'Testing....'
        sh 'cd seleniumgenc && mvn test -B'
      }
    }

//     stage('Deploy') {
//       steps {
//         withCredentials([[$class          : 'UsernamePasswordMultiBinding',
//                           credentialsId   : 'PCF_LOGIN',
//                           usernameVariable: 'USERNAME',
//                           passwordVariable: 'PASSWORD']]){
//             sh '/usr/bin/cf login -u $USERNAME -p $PASSWORD'
//             sh '/usr/bin/cf target -o uki-engineering -s Academy-S2'
//             sh '/usr/bin/cf push'
//         }
//       }
//     }
  }
}