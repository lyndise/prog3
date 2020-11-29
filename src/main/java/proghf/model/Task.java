package proghf.model;

import java.util.Set;

public abstract class Task {
    protected Set<Label> labels;
    protected String name;

    public Set<Label> getLabels() {
        return labels;
    }

    public String getName() {
        return name;
    }
}
