import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskDAO taskDAO = new TaskDAO();
        AIService aiService = new AIService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("  🤖 AI-Powered Task Management System  ");
        System.out.println("=========================================");

        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View All Tasks");
            System.out.println("2. Auto-Generate Tasks with AI");
            System.out.println("3. Update Task Status");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- All Tasks ---");
                    List<Task> tasks = taskDAO.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        for (Task t : tasks) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 2:
                    System.out.print("\nEnter project goal for AI breakdown: ");
                    String goal = scanner.nextLine();
                    System.out.println("🤖 AI is processing...");

                    String aiResult = aiService.generateSubTasks(goal);
                    if (aiResult != null && !aiResult.isEmpty()) {
                        String[] lines = aiResult.split("\n");
                        for (String line : lines) {
                            if (line.contains("|")) {
                                String[] parts = line.split("\\|");
                                if (parts.length >= 3) {
                                    Task task = new Task(1L, parts[0].trim(), parts[1].trim(), parts[2].trim().toUpperCase());
                                    taskDAO.addTask(task);
                                }
                            }
                        }
                    } else {
                        System.err.println("Failed to generate tasks.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter Task ID to update: ");
                    Long updateId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter New Status (TODO, IN_PROGRESS, DONE): ");
                    String status = scanner.nextLine().toUpperCase();

                    taskDAO.updateTaskStatus(updateId, status);
                    break;

                case 4:
                    System.out.print("\nEnter Task ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    scanner.nextLine();

                    taskDAO.deleteTask(deleteId);
                    break;

                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}