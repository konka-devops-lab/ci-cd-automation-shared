def call(Map config = [:]) {
    // Default configuration
    def defaults = [
        mavenVersion: 'M3',
        testGoal: 'test',
        reportPath: 'target/surefire-reports/**/*.xml',
        skipTests: false
    ]
    
    // Merge defaults with user config
    config = defaults + config
    
    // If skipTests is true, just return
    if (config.skipTests) {
        echo "⚠️ Skipping unit tests as per configuration"
        return
    }
    
    echo "🚀 Starting Unit Tests..."
    
    // Use configured Maven version if available
    if (config.mavenVersion) {
        withMaven(maven: config.mavenVersion) {
            sh "mvn clean ${config.testGoal}"
        }
    } else {
        sh "mvn clean ${config.testGoal}"
    }
    
    // Publish test results
    junit config.reportPath
    
    echo "✅ Unit Tests completed!"
}