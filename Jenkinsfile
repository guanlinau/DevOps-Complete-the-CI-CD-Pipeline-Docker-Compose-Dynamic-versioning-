#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
       
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("incrementVersion") {
            steps {
                script {
                    gv.incrementVersion()
                }
            }
        }
        stage ('commitVersionUpdate') {
            steps {
                script {
                    gv.commitVersionUpdate()
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {
            when {
                expression {
                    gv.masterBranch()
                }
            }
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
