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
}
