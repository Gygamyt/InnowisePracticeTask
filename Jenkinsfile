pipeline {
    agent any
    stages {
        stage('Git') {
            steps {
                git url: 'https://github.com/Gygamyt/InnowisePracticeTask'
            }
        }
        stage('Tests') {
            steps {
                  bat "./gradlew clean test"
            }
        }
            }
        post {
               always {
                      allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
               }
            }
        }