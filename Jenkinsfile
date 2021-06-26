class Constants {
    static final String MASTER_BRANCH = 'master'

    static final String QA_BUILD = 'Debug'
    static final String RELEASE_BUILD = 'Release'

    static final String INTERNAL_TRACK = 'internal'
    static final String RELEASE_TRACK = 'release'
}

def getBuildType() {
    switch (env.BRANCH_NAME) {
        case Constants.MASTER_BRANCH:
            return Constants.RELEASE_BUILD
        default:
            return Constants.QA_BUILD
    }
}

def getTrackType() {
    switch (env.BRANCH_NAME) {
        case Constants.MASTER_BRANCH:
            return Constants.RELEASE_TRACK
        default:
            return Constants.INTERNAL_TRACK
    }
}

def isDeployCandidate() {
    if ("${env.VARIANT}" == Constants.QA_BUILD) {
        println "-------- This 's a QA build, we stopping here --------"
        return false
    }

    return true
}

pipeline {
    agent any
    environment {
        appName = 'Moovie'

//         KEY_PASSWORD = credentials('keyPassword')
//         KEY_ALIAS = credentials('keyAlias')
//         KEYSTORE = credentials('keystore')
//         STORE_PASSWORD = credentials('storePassword')
    }
    stages {
        stage('Run Unit Tests') {
            steps {
                echo "-------- Running Unit Tests --------"
                script {
                    sh "./gradlew test${VARIANT}UnitTest"
                }
            }
        }
        stage('Build Bundle') {
            when { expression { return isDeployCandidate() } }
            steps {
                echo "-------- Building Application --------"
                script {
                    sh "./gradlew bundle${VARIANT}"
                }
            }
        }
    }
}
