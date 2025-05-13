pipelineJob('job1-pre') {
    description('Job auto-creado desde Git que hace despliegue con try/catch')

    definition {
        cps {
            script('''
                node {
                    try {
                        stage('Deploy') {
                            echo "Desplegando a JBOSS..."
                            // sh 'jboss-cli.sh --connect --command="deploy app.war"'
                        }
                    } catch (err) {
                        echo "Error capturado: ${err}"
                        currentBuild.result = 'FAILURE'
                    }
                }
            '''.stripIndent())
            sandbox(true)
        }
    }

    parameters {
        stringParam('ENTORNO', 'dev', 'Nombre del entorno de despliegue')
    }
}
