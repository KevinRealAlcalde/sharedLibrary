def call () {


    script {
        try {
            testFiles = ["1", "2", "3", "4"]
            def parallelStagesMap = [:]
            testFiles.each() {
                parallelStagesMap["Runs on ${it}"] = generateStage(it, "/tmp")
            }
            parallel parallelStagesMap
        } catch (Exception e) {
            echo 'Exception occurred with parallel stage: ' + e
        }
    }

  def generateStage(podLabel, customWorkspace) {
    return {
        stage("Runs on ${podLabel}") {
            podTemplate(
            inheritFrom: 'cloud') {
                node("test") { ws("${customWorkspace}") {
                        try {
                            stage("Before") {
                                echo 'look we ran'
                                sleep(60)
                            }
                        }catch(Exception e){
                            echo "Error " + e
                         }
                        }
                    }
                }
            }
        }
    }
}
