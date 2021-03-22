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
  }
}