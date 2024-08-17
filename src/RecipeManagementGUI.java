import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeManagementGUI extends JFrame {
    private List<Recipe> localRecipes = new ArrayList<>();
    private RecipeSearchService recipeSearchService = new RecipeSearchService();
    private ShoppingList shoppingList = new ShoppingList();  // Initialize ShoppingList

    private JTextField nameField;
    private JTextField sourceField;
    private JTextField urlField;
    private JTextField caloriesField;
    private JTextField servingsField;  // New field for servings
    private JTextArea ingredientsArea;
    private JTextField searchField;
    private JPanel resultPanel;
    private JTextArea shoppingListArea;

    public RecipeManagementGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Recipe Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab for adding new recipes
        JPanel addRecipePanel = createAddRecipePanel();
        tabbedPane.addTab("Add Recipe", addRecipePanel);

        // Tab for searching recipes
        JPanel searchPanel = createSearchPanel();
        tabbedPane.addTab("Search Recipes", searchPanel);

        JPanel shoppingListPanel = createShoppingListPanel();
        tabbedPane.addTab("Shopping List", shoppingListPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createAddRecipePanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Recipe Name:");
        nameField = new JTextField();

        JLabel sourceLabel = new JLabel("Source:");
        sourceField = new JTextField();

        JLabel urlLabel = new JLabel("URL:");
        urlField = new JTextField();

        JLabel caloriesLabel = new JLabel("Calories:");
        caloriesField = new JTextField();

        JLabel servingsLabel = new JLabel("Servings:");  // Label for servings
        servingsField = new JTextField();  // Text field for servings

        JLabel ingredientsLabel = new JLabel("Ingredients (comma-separated):");
        ingredientsArea = new JTextArea();
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsArea);

        JButton addButton = new JButton("Add Recipe");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecipe();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(sourceLabel);
        panel.add(sourceField);
        panel.add(urlLabel);
        panel.add(urlField);
        panel.add(caloriesLabel);
        panel.add(caloriesField);
        panel.add(servingsLabel);  // Add servings label
        panel.add(servingsField);  // Add servings field
        panel.add(ingredientsLabel);
        panel.add(ingredientsScrollPane);
        panel.add(new JLabel());
        panel.add(addButton);

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRecipes();
            }
        });

        topPanel.add(new JLabel("Search Term:"), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        JScrollPane resultScrollPane = new JScrollPane(resultPanel);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(resultScrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addRecipe() {
        String name = nameField.getText();
        String source = sourceField.getText();
        String url = urlField.getText();
        int calories = Integer.parseInt(caloriesField.getText());
        int servings = Integer.parseInt(servingsField.getText());  // Get servings value
        String[] ingredientsArray = ingredientsArea.getText().split(",");

        List<Ingredient> ingredients = new ArrayList<>();
        for (String ingredientName : ingredientsArray) {
            ingredients.add(new Ingredient(ingredientName.trim(), 0.0, "unit", 0));
        }

        Recipe recipe = new Recipe(name, source, url, calories, servings, null, ingredients);  // Pass servings
        localRecipes.add(recipe);

        JOptionPane.showMessageDialog(this, "Recipe added successfully!");
    }

    private void searchRecipes() {
        String searchTerm = searchField.getText();
        resultPanel.removeAll();  // Clear previous results

        // Search locally stored recipes
        for (Recipe recipe : localRecipes) {
            if (recipe.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                addRecipeToPanel(recipe);
            }
        }

        // Search online using Edamam API
        try {
            List<Recipe> apiRecipes = recipeSearchService.searchRecipes(searchTerm);
            if (apiRecipes.isEmpty()) {
                JLabel noResultLabel = new JLabel("No recipes found.");
                resultPanel.add(noResultLabel);
            } else {
                for (Recipe recipe : apiRecipes) {
                    addRecipeToPanel(recipe);
                }
            }
        } catch (IOException e) {
            JLabel errorLabel = new JLabel("Error while searching for recipes: " + e.getMessage());
            resultPanel.add(errorLabel);
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void addRecipeToPanel(Recipe recipe) {
        JPanel recipePanel = new JPanel(new BorderLayout(10, 10));
        recipePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        try {
            URL url = new URL(recipe.getImageUrl());
            Image image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(icon);
            recipePanel.add(imageLabel, BorderLayout.WEST);
        } catch (IOException e) {
            JLabel noImageLabel = new JLabel("[No Image]");
            recipePanel.add(noImageLabel, BorderLayout.WEST);
        }

        int calories = (int) recipe.getCalories();  // Ensure getCalories() returns an int
        int servings = recipe.getServings();  // Ensure getServings() returns an int

        int caloriesPerServing = (servings > 0) ? (calories / servings) : calories;  // Ensure division is between ints

        String html = String.format(
                "<html><b>Recipe Name:</b> %s<br>" +
                        "<b>Source:</b> %s<br>" +
                        "<b>URL:</b> <a href='%s'>%s</a><br>" +
                        "<b>Calories (per serving):</b> %d<br><br></html>",
                recipe.getName(),
                recipe.getSource(),
                recipe.getUrl(),
                recipe.getUrl(),
                caloriesPerServing  // Display calories per serving
        );

        JLabel recipeDetailsLabel = new JLabel(html);
        recipeDetailsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URL(recipe.getUrl().toString()).toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        recipePanel.add(recipeDetailsLabel, BorderLayout.CENTER);
        resultPanel.add(recipePanel);
    }
    private JPanel createShoppingListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        shoppingListArea = new JTextArea();
        shoppingListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(shoppingListArea);

        JButton generateButton = new JButton("Generate Shopping List");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateShoppingList();
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(generateButton, BorderLayout.SOUTH);

        return panel;
    }

    private void generateShoppingList() {
        shoppingListArea.setText("");  // Clear the current shopping list

        for (Recipe recipe : localRecipes) {
            if (recipe.getIngredients() != null) {
                for (Ingredient ingredient : recipe.getIngredients()) {
                    shoppingList.addIngredient(ingredient);
                }
            }
        }

        shoppingListArea.setText(shoppingList.toString());  // Display the shopping list
    }

    public static void main(String[] args) {
        RecipeManagementGUI gui = new RecipeManagementGUI();
        gui.setVisible(true);
    }
}
