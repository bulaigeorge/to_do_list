import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static String exitMenu = "q", backMenu = "b";
    private static ArrayList<String> mainMenu, listMenu;
    private static String mainMenuChoice = "\nChoose a number or press 'q' to exit: ";
    private static Scanner keyboard = new Scanner(System.in);
    private static User user = new User();

   public static void main(String[] args) {

       //create Menu
       mainMenu = new ArrayList<>();
       mainMenu.add("Create to do list");
       mainMenu.add("Show a list");
       mainMenu.add("Delete a list");

       //create the menu for a list
       listMenu = new ArrayList<>();
       listMenu.add("Add task");
       listMenu.add("Check or uncheck a task");
       listMenu.add("Delete a task");
       listMenu.add("Modify a task");
       listMenu.add("See task description");
       listMenu.add("See tasks of a category");


       //data for testing
       ToDoList home = new ToDoList("home");
       ToDoList work = new ToDoList("work");

       user.addToList(home);
       user.addToNameList(home.getName());
       user.addToList(work);
       user.addToNameList(work.getName());

       home.addTask(new Task("buy groceries", "get ingredients to make a pizza"));
       home.addTask(new Task("fix the sink", "the bathroom sink is leaking", "maintenance", "fixing"));
       home.addTask(new Task("buy new coffee table" , "get a suitable coffee table for the living room", "design", "shopping"));
       work.addTask(new Task("call superior"));
       work.addTask(new Task("find missing file"));
       //end of data

       menu();
   }


   private static void menu() {
       String choice = "";
       while(!choice.equals(exitMenu)) {
           System.out.println("--------------------------------------");
           System.out.println("\nMain menu:");
           showMenu(mainMenu);
           System.out.println(mainMenuChoice);

           choice = keyboard.next();
           if(!choice.equals(exitMenu)) {
               int choiceInt = Integer.parseInt(choice);
               switch (choiceInt) {
                   case 1: createToDoListMenu();
                       break;
                   case 2: showListsMenu();
                       break;
                   case 3: deleteListMenu();
                       break;
               }
           }
       }
   }

   private static void showMenu(ArrayList<String> list) {
       int i = 1;
       System.out.println();
       for(String s : list) {
           System.out.println(i + ". " + s);
           i++;
       }
   }

   private static void createToDoListMenu() {
       System.out.println("\nChose a name for your list:");
       keyboard.nextLine();
       String name = keyboard.nextLine();
       while(user.listsNames().contains(name)) {
           System.out.println("A list with that name already exists! Chose another name: ");
           name = keyboard.nextLine();
       }
       ToDoList list = new ToDoList(name);

       user.addToList(list);
       user.addToNameList(list.getName());

       System.out.println("Do you want to add a task? yes/no");
       String answer = keyboard.nextLine();
       while(!answer.equals("no")) {
           addTask(list);
           System.out.println("Do you want to add a task? yes/no");
           answer = keyboard.nextLine();
       }
   }


   private static void showListsMenu() {
       System.out.println("\nChose a list you want to see or press 'b' to go back\n");
       if(!user.toDoList().isEmpty()) {
           user.showLists();
           String input = keyboard.next();
           while(!input.equals(backMenu)) {
               int inputInt = Integer.parseInt(input);
               ToDoList currentList = user.toDoList().get(inputInt-1);
               System.out.println("\n" + currentList.getName() + " :");
               currentList.showTasks();

               //enter the list
               System.out.println("\n\nChose an action or press 'b' to go back");
               String choice = "";
               while(!choice.equals(backMenu)) {

                   showMenu(listMenu);
                   choice = keyboard.next();
                   if(!choice.equals(backMenu)) {
                       int choiceInt = Integer.parseInt(choice);
                       switch (choiceInt) {
                           case 1:
                               keyboard.nextLine();
                               addTask(currentList);
                               break;
                           case 2: checkOrUncheck(currentList);
                               break;
                           case 3: deleteTask(currentList);
                               break;
                           case 4: modifyTask(currentList);
                               break;
                           case 5: seeTaskDescription(currentList);
                               break;
                           case 6: seeTasksOfCategory(currentList);
                               break;
                       }
                   }
                   System.out.println("Chose an action or press 'b' to go back");
               }
               //exit the list
               System.out.println("\nPress b to go back or chose another list to see\n");
               user.showLists();
               input = keyboard.next();
           }
       } else {
           System.out.println("There are no lists created");
       }
   }

   private static void deleteListMenu() {
       System.out.println("Chose a list you want to delete or press 'b' to go back\n");
       if(!user.toDoList().isEmpty()) {
           user.showLists();
           String input = keyboard.next();
           while(!input.equals(backMenu)) {
               int inputInt = Integer.parseInt(input);
               user.listsNames().remove(user.toDoList().get(inputInt-1).getName());
               user.toDoList().remove(inputInt-1);

               System.out.println("\nPress b to go back or chose another list to delete\n");
               user.showLists();
               input = keyboard.next();
           }
       } else {
           System.out.println("There are no lists to be deleted");
       }
   }

   private static void addTask(ToDoList currentList) {
       System.out.println("Type the name of the task you want to add");
       String taskName = keyboard.nextLine();
       while(currentList.getTasksNames().contains(taskName)) {
           System.out.println("A task with that name already exists! Chose another name: ");
           taskName = keyboard.nextLine();
       }
       System.out.println("You can add a description or press b to skip");
       String description = keyboard.nextLine();
       if(!description.equals(backMenu)) {
           currentList.addTask(new Task(taskName, description));
       } else {
           currentList.addTask(new Task(taskName));
       }
       System.out.println("You can add categories or press b when you are done to skip");
       String category = "";
       while(!category.equals(backMenu)) {
           category = keyboard.nextLine();
           if(!category.equals(backMenu)) {
               currentList.getTasks().get(currentList.getTasks().size()-1).addCategory(category);
               currentList.addCategory(category);
           }
       }
       System.out.println("Task added\n");
   }

   private static void checkOrUncheck(ToDoList currentList) {
       keyboard.nextLine();
       System.out.println("\nChose a task u want to check or uncheck");
       currentList.showTasks();
       String checkOrUncheck = keyboard.nextLine();
       int checkInt = Integer.parseInt(checkOrUncheck);
       Task currentTask = currentList.getTasks().get(checkInt-1);
       if(!(currentList.getTasks().get(checkInt-1)).getStatus()) {
           System.out.println("This task is unchecked, press 1 to check it or 'b' to go back");
           String mark = keyboard.nextLine();
           if(mark.equals("1")) {
               currentTask.changeToChecked();
               System.out.println("Task checked\n");
               currentList.showTasks();
           }
       } else {
           System.out.println("This task is checked, press 1 to uncheck it or 'b' to go back");
           String mark = keyboard.nextLine();
           if(mark.equals("1")) {
               currentTask.changeToUnchecked();
               System.out.println("Task unchecked\n");
               currentList.showTasks();
           }
       }
   }

   private static void deleteTask(ToDoList currentList) {
       currentList.showTasks();
       keyboard.nextLine();
       System.out.println("Chose a task you want to delete or press 'b' to go back");
       String taskChosen = keyboard.nextLine();
       int taskIndex = Integer.parseInt(taskChosen);
       currentList.getTasks().remove(taskIndex-1);
       currentList.getTasksNames().remove(taskIndex-1);
       System.out.println("Task deleted\n");
       currentList.showTasks();
   }

   private static void modifyTask(ToDoList currentList) {
       currentList.showTasks();
       keyboard.nextLine();
       System.out.println("Chose a task you want to modify or press 'b' to go back");
       String taskToModify = keyboard.nextLine();
       while(!taskToModify.equals(backMenu)) {
           int taskPlace = Integer.parseInt(taskToModify);

           //change name
           System.out.println("Rename the task or press 'b' to skip");
           String newName = keyboard.nextLine();
           if(!newName.equals(backMenu)) {
               currentList.getTasks().get(taskPlace-1).changeName(newName);
               currentList.getTasksNames().set(taskPlace-1, newName);
           }

           //change description
           System.out.println("Change description, press enter to clear description or press 'b' to skip");
           String descriptionModify = keyboard.nextLine();
           if(!descriptionModify.equals(backMenu)) {
               currentList.getTasks().get(taskPlace-1).setDescription(descriptionModify);
           }

           //change categories

           String categoryModifier = "";
           while(!categoryModifier.equals(backMenu)) {
               System.out.println("Pres 1 to add a category, press 2 to delete a category or press b to skip");
               categoryModifier = keyboard.nextLine();
               if(categoryModifier.equals("1")) {
                   System.out.println("Category you want to add: \n");
                   String newCategory = keyboard.nextLine();
                   currentList.getTasks().get(taskPlace-1).addCategory(newCategory);
               } else if (categoryModifier.equals("2")) {
                   System.out.println("Chose a task you want to delete");
                   currentList.getTasks().get(taskPlace-1).showCategories(1);
                   String catDelete = keyboard.nextLine();
                   int categoryDelete = Integer.parseInt(catDelete);
                   System.out.println("Category " + currentList.getTasks().get(taskPlace-1).getCategory(categoryDelete-1) + " deleted");
                   currentList.getTasks().get(taskPlace-1).deleteCategory(categoryDelete-1);

               }
           }
           taskToModify = "b";
       }
       System.out.println("Task changed\n");
       currentList.showTasks();
   }

   private static void seeTaskDescription(ToDoList currentList) {
       System.out.println("\n");
       currentList.showTasks();
       keyboard.nextLine();
       System.out.println("Chose a task you want to see the description for or press 'b' to go back");
       String seeDescription = keyboard.nextLine();
       if(!seeDescription.equals(backMenu)) {
           int descriptionIndex = Integer.parseInt(seeDescription);
           System.out.println("\nDescription: " + currentList.getTasks().get(descriptionIndex-1).getDescription() + "\n");
           System.out.println("Categories: ");

           if(!currentList.getTasks().get(descriptionIndex-1).getCategories().isEmpty()) {
               currentList.getTasks().get(descriptionIndex-1).showCategories();
           } else {
               System.out.println("No categories found\n");
           }
       }
   }

   private static void seeTasksOfCategory(ToDoList currentList) {
       System.out.println("\n");
       currentList.showCategories();
       keyboard.nextLine();
       System.out.println("Chose a category to see tasks or press 'b' to go back");
       String category = keyboard.nextLine();
       if(!category.equals(backMenu)) {
           int catIndex = Integer.parseInt(category);
           System.out.println("\nTasks in the same category ("+ currentList.getCategories().get(catIndex-1) + "):");
           for(Task t : currentList.getTasks() ) {
               if(t.getCategories().contains(currentList.getCategories().get(catIndex-1))){
                   System.out.println(t.getName());
               }
           }
       }
   }
}
