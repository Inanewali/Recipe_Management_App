import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RecipeManagementApp {

    private RecipeSearchService recipeSearchService = new RecipeSearchService();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Recipe Management System!");
        while (true) {
            System.out.println("1. Search for a recipe");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter a search term: ");
                String searchTerm = scanner.nextLine();
                searchAndDisplayRecipes(searchTerm);
            } else if (choice.equals("2")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    private void searchAndDisplayRecipes(String searchTerm) {
        try {
            List<Recipe> recipes = recipeSearchService.searchRecipes(searchTerm);
            if (recipes.isEmpty()) {
                System.out.println("No recipes found.");
            } else {
                for (Recipe recipe : recipes) {
                    System.out.println(recipe);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while searching for recipes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        RecipeManagementApp app = new RecipeManagementApp();
        app.run();
    }
}
