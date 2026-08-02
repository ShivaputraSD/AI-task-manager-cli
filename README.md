1. ABOUT
The AI Task Manager CLI is a modular, production-ready developer tool built in pure Core Java (JDK  17+) and MySQL, designed to bring intelligent task orchestration.
Built around the Model-DAO-Service-Presentation design pattern, the application cleanly decouples presentation logic, persistent storage, and external API communication.
At its core, the CLI provides complete database persistence via JDBC, utilizing PreparedStatement interfaces to execute safe CRUD operations against a local MySQL database while mitigating SQL injection risks.
What elevates the application beyond a standard task tracker is its integration with the Grok REST API using Java's native HttpClient, When given complex software goals—such as "Build a Spring Boot Auth Service"—the AI engine dynamically decomposes the task into actionable sub-steps and evaluates urgency to offer smart priority recommendations.

3. BUILT WITH
  • Core Language: Java (JDK 17+)
  • AI Integration: Grok REST API(via Java Native HttpClient and JSON Parsing)
  • Database Management: MySQL Server 8.0+
  • Architecture: Model-DOA-Service-Presentation Layered Pattern

4. GETTING STARTED
   • Follow these steps to set up and run the project on your local system

   PRE-REQUISITIES
   • Java Development Kit (JDK): Version17+
   • Database: MySQL Server 8.0+
   • IDE: VS Code or any Java-supported IDE
   • API Key: A Grok API Key or any Model key you have

   Step 1. Clone the Repository
   • open terminal in IDE or System Terminal execute the belove command in it
   git clone https://github.com/your-username/ai-task-manager-cli.git
   cd ai-task-manager-cli
