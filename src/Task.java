public class Task {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private String status;
    private String priority;

    // Constructor for creating new tasks
    public Task(Long projectId, String title, String description, String priority) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = "TODO";
        this.priority = priority;
    }

    // Constructor for retrieving existing tasks from database
    public Task(Long id, Long projectId, String title, String description, String status, String priority) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Long getId() { return id; }
    public Long getProjectId() { return projectId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }

    @Override
    public String toString() {
        return "Task [ID=" + id + ", Title=" + title + ", Status=" + status + ", Priority=" + priority + "]";
    }
}