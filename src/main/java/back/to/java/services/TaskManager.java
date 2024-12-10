package back.to.java.services;

import back.to.java.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String title, String description) {
        tasks.add(new Task(title, description));
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Задача с указанным номером не найдена.");
        }
        return tasks.get(index);
    }

    public void updateTask(int index, String newTitle, String newDescription) {
        Task task = getTask(index);
        if (!newTitle.isEmpty()) {
            task.setTitle(newTitle);
        }
        if (!newDescription.isEmpty()) {
            task.setDescription(newDescription);
        }
    }

    public void deleteTask(int index) {
        tasks.remove(getTask(index));
    }
}
