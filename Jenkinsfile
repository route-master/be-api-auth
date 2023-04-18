pipeline {
  agent any
  stages {
    stage('Gradle Test') {
      steps {
        sh 'chmod +x ./gradlew'
        sh './gradlew test --info'
      }
    }

    stage('Gradle Boot Build Image') {
      steps {
        withCredentials([
          usernamePassword(
            credentialsId: "a32c6b58-2ba2-44ce-bade-3b7176e675f6",
            usernameVariable: 'USERNAME',
            passwordVariable: 'PASSWORD'
          )
        ]) {
          sh 'chmod +x ./gradlew'
          sh './gradlew bootBuildImage -Ddocker_id=$USERNAME -Ddocker_pw=$PASSWORD --info'
        }
      }
    }
  }
}