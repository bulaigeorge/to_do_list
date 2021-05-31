import java.util.ArrayList;
import java.util.*;

public class User {
    private ArrayList<ToDoList> lists;
    private HashSet<String> listsNames;

    public User() {
        this.lists = new ArrayList<>();
        this.listsNames = new HashSet<>();
    }

    public void addToList(ToDoList list) {
        this.lists.add(list);
    }

    public void addToNameList(String s) {
        this.listsNames.add(s);
    }

    public void showLists() {
        int i = 1;
        for(ToDoList l : lists) {
            System.out.println(i++ + ". " + l.getName());
        }
    }

    public ArrayList<ToDoList> toDoList() {
        return this.lists;
    }

    public HashSet<String> listsNames() {
        return this.listsNames;
    }
}
