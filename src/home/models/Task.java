package home.models;

public class Task {
    private String name;
    private TaskStates taskState;

    public Task( String name) {
        this.name = name;
        taskState = TaskStates.PLAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStates getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskStates taskState) {
        this.taskState = taskState;
    }
}
