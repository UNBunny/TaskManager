import back.to.java.models.Task;
import back.to.java.services.FileStorage;
import back.to.java.services.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static final String FILE_NAME = "tasks.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        FileStorage fileStorage = new FileStorage(FILE_NAME);

        try {
            fileStorage.load(taskManager);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n--- Менеджер задач ---");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Просмотреть все задачи");
            System.out.println("3. Редактировать задачу");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Сохранить и выйти");

            System.out.print("Введите номер команды: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addTask(taskManager);
                case 2 -> viewTasks(taskManager);
                case 3 -> editTask(taskManager);
                case 4 -> deleteTask(taskManager);
                case 5 -> saveAndExit(taskManager, fileStorage);
            }
        }
    }

    private static void saveAndExit(TaskManager taskManager, FileStorage fileStorage) {
        try {
            fileStorage.save(taskManager.getAllTasks());
            System.out.println("Задачи сохранены. До свидания!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
        }

    }

    private static void deleteTask(TaskManager taskManager) {
        viewTasks(taskManager);
        System.out.println("Введите номер задачи для удаления: ");
        int taskId = Integer.parseInt(scanner.nextLine()) - 1;
        try {
            taskManager.deleteTask(taskId);
            System.out.println("Задача удалена!");
            System.out.println("Список задач после удаления:\n ");
            viewTasks(taskManager);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void editTask(TaskManager taskManager) {
        viewTasks(taskManager);
        System.out.println("Введите номер задачи для редактирования: ");
        int taskId = Integer.parseInt(scanner.nextLine()) - 1;

        try {
            Task task = taskManager.getTask(taskId); // Проверяем существование задачи
            System.out.print("Введите новое название (или оставьте пустым): ");
            String newTitle = scanner.nextLine();
            System.out.print("Введите новое описание (или оставьте пустым): ");
            String newDescription = scanner.nextLine();

            taskManager.updateTask(taskId, newTitle, newDescription);
            System.out.println("Задача обновлена!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Данной задачи не существует! Убедитесь, что номер задачи указан корректно.");
        }
    }

    private static void viewTasks(TaskManager taskManager) {
        if (taskManager.getAllTasks().isEmpty()) {
            System.out.println("Задач пока нет.");
            return;
        }
        System.out.println("\nСписок задач:");
        int index = 1;
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(index++ + ". " + task);
        }
    }

    private static void addTask(TaskManager taskManager) {
        System.out.print("Введите название задачи: ");
        String title = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();
        taskManager.addTask(title, description);
        System.out.println("Задача добавлена!");
    }
}
