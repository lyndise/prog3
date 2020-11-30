package proghf.model;

public class Reminder extends Task{
    private String text;

    public Reminder(String name) {
        super(name);
    }

    public String getText() {
        return text;
    }
}
