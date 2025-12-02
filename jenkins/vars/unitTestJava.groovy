def call(Map config = [:]) {
    def defaults = [
        testGoal: 'test',
        reportPath: 'target/surefire-reports/**/*.xml',
        skipTests: false
    ]
    
    config = defaults + config
    
    if (config.skipTests) {
        echo "⚠️ Skipping unit tests"
        return
    }
    
    echo "🚀 Starting Unit Tests..."
    
    // SIMPLIFIED: Remove withMaven wrapper
    sh "mvn clean ${config.testGoal}"
    
    junit config.reportPath
    
    echo "✅ Unit Tests completed!"
}