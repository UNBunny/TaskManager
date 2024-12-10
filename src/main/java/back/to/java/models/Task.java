package back.to.java.models;

import java.time.LocalDate;

public class Task {
    String title;
    String description;
    LocalDate startDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.startDate = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Задача: " + title +
                "\nОписание: " + description +
                "\nДата создания: " + startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
