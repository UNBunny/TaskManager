package back.to.java.services;

import back.to.java.models.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    public final String fileName;

    public FileStorage(String fileName) {
        this.fileName = fileName;
    }

    public void save(List<Task> tasks) throws IOException {
        List<String> existingLines = readAllLines();


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Task task : tasks) {
                String serializedTask = task.getTitle() + ";" + task.getDescription() + ";" + task.getStartDate();
                if (!existingLines.contains(serializedTask)) {
                    writer.write(serializedTask);
                    writer.newLine();
                }
            }
        }
    }

    public void load(TaskManager taskManager) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    taskManager.addTask(parts[0], parts[1]);
                }
            }
        }
    }
    public List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return lines;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
