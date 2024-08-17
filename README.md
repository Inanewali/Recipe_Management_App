
# Recipe Management System

## Overview

The Recipe Management System is a Java-based desktop application that allows users to manage recipes, generate shopping lists, and search for recipes online. The application uses the Edamam API to search for recipes, including displaying recipe details such as images, calories, and servings.

## Features

- **Add Recipes**: Users can add new recipes with details such as name, source, URL, calories, servings, and ingredients.
- **Search Recipes**: Users can search for recipes online using the Edamam API. The search results include images, recipe details, and clickable URLs.
- **Generate Shopping List**: Automatically generate a shopping list based on the ingredients of the added recipes.
- **Modern User Interface**: The application uses a modern Look and Feel to enhance the user experience.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or a simple text editor and command-line tools.
- An Edamam API key for recipe search functionality.

### Setting Up the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/recipe-management-system.git
   cd recipe-management-system
   ```

2. **Import the Project**:
   - Open your Java IDE.
   - Import the project as a Maven or Gradle project (if applicable).

3. **Add the Edamam API Key**:
   - Obtain an API key from [Edamam](https://developer.edamam.com/).
   - Add your API key to the `RecipeSearchService` class.
   - Replace `your_api_key_here` with your actual API key.

4. **Build and Run the Project**:
   - Use your IDE to build and run the project.
   - Alternatively, use the command line:
     ```bash
     javac -d bin src/**/*.java
     java -cp bin RecipeManagementGUI
     ```

## Usage

### Adding a Recipe

1. Navigate to the "Add Recipe" tab.
2. Enter the recipe details, including name, source, URL, calories, servings, and ingredients.
3. Click "Add Recipe" to save the recipe.

### Searching for Recipes

1. Navigate to the "Search Recipes" tab.
2. Enter a search term (e.g., "biryani") and click "Search".
3. View the results, which include images, clickable URLs, and calories per serving.

### Generating a Shopping List

1. Navigate to the "Shopping List" tab.
2. Click "Generate Shopping List" to compile a list of ingredients from the added recipes.

## Project Structure

- **src/**: Contains the Java source files.
  - `Recipe.java`: The Recipe class representing a recipe.
  - `Ingredient.java`: The Ingredient class representing an ingredient.
  - `ShoppingList.java`: The ShoppingList class for managing ingredients.
  - `RecipeSearchService.java`: The service class that handles API requests to the Edamam API.
  - `RecipeManagementGUI.java`: The main GUI class.
- **resources/**: Contains any additional resources such as images, configuration files, etc.

## Dependencies

- **Java Standard Library**: For core Java functionality.
- **Swing**: For building the graphical user interface.
- **Edamam API**: For online recipe search functionality.

## Screenshots

### Main Interface

![Main Interface](path/to/screenshot.png)

### Search Results

![Search Results](path/to/screenshot.png)

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any improvements or bug fixes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
