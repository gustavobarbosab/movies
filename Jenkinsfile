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
    return ("${env.BRANCH_NAME}" =~ /(develop|master)/)
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
        stage('Run Tests') {
            steps {
                echo "-------- Running Unit Tests --------"
                script {
                    VARIANT = getBuildType()
                    sh "./gradlew test${VARIANT}UnitTest"
                }
            }
        }
        stage('Build Bundle') {
            echo "Build inside branch ${env.BRANCH_NAME}"
            when { expression { return isDeployCandidate() } }
            steps {
                echo "-------- Building Application --------"
                script {
                    VARIANT = getBuildType()
                    sh "./gradlew bundle${VARIANT}"
                }
            }
        }
    }
}
