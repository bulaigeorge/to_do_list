import java.util.ArrayList;

public class ToDoList {
    private String listName;
    private static int id = 1;
    private int listId;
    private ArrayList<Task> list;
    private ArrayList<String> tasksNames;
    private ArrayList<String> categories;

    public ToDoList(String name) {
        this.listName = name;
        this.listId = id++;
        this.list = new ArrayList<>();
        this.tasksNames = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void addCategory(String s) {
        this.categories.add(s);
    }

    public void showCategories() {
        int i = 1;
        for(String s : this.categories) {
            System.out.println(i++ + ". " + s);
        }
    }

    public ArrayList<String> getCategories() {
        return this.categories;
    }

    public void showId() {
        System.out.println(this.listId);
    }

    public ArrayList<Task> getTasks() {
        return list;
    }

    public ArrayList<String> getTasksNames() {
        return this.tasksNames;
    }

    public String getName() {
        return this.listName;
    }

    public void addTask(Task task) {
        this.list.add(task);
        this.tasksNames.add(task.getName());
        for(String category : task.getCategories()) {
            if(!this.categories.contains(category)) {
                this.categories.add(category);
            }
        }
    }

    public void showTasks() {
        int i = 1;
        for(Task s : list) {
            System.out.println(i++ + ". " + s.getName());
        }
    }
}
