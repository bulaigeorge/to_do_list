import java.util.ArrayList;

public class Task {

    private String name;
    private boolean checked;
    private String description;
    private ArrayList<String> categories;

    public Task(String name) {
        this.name = name;
        this.checked = false;
        this.description = "no description available";
        this.categories = new ArrayList<>();
    }

    public Task(String name, String description) {
        this.name = name;
        this.checked = false;
        this.description = description;
        this.categories = new ArrayList<>();
    }

    public Task(String name, String description, String... categories) {
        this.name = name;
        this.checked = false;
        this.description = description;
        this.categories = new ArrayList<>();
        for(String s : categories) {
            this.categories.add(s);
        }
    }

    public void showCategories() {
        for(String s : categories) {
            System.out.println("- " + s);
        }
        System.out.println("\n");
    }

    public void showCategories(int i) {
        for(String s : categories) {
            System.out.println(i++ + ". " + s);
        }
        System.out.println("\n");
    }

    public String getCategory(int i) {
        return this.categories.get(i);
    }

    public void deleteCategory(int i) {
        this.categories.remove(i);
    }

    public void addCategory(String s) {
        this.categories.add(s);
    }

    public ArrayList<String> getCategories() {
        return this.categories;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return checked;
    }

    public void changeToChecked() {
        this.checked = true;
    }

    public void changeToUnchecked() {
        this.checked = false;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
