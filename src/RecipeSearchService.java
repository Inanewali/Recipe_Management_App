import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchService {
    private static final String APP_ID = "a2bc592b";
    private static final String APP_KEY = "21aa7a6be92385a43f3a1da3559f2514";
    private static final String API_URL = "https://api.edamam.com/search";


    public List<Recipe> searchRecipes(String query) throws IOException, IOException {
        String urlString = String.format("%s?q=%s&app_id=%s&app_key=%s", API_URL, query, APP_ID, APP_KEY);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        connection.disconnect();

        return parseRecipes(content.toString());
    }

    private List<Recipe> parseRecipes(String jsonResponse) {
        List<Recipe> recipes = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray hits = jsonObject.getAsJsonArray("hits");

        for (int i = 0; i < hits.size(); i++) {
            JsonObject recipeJson = hits.get(i).getAsJsonObject().getAsJsonObject("recipe");

            String label = recipeJson.get("label").getAsString();  // This is the recipe name
            String imageUrl = recipeJson.get("image").getAsString();  // Recipe image URL
            String source = recipeJson.get("source").getAsString();  // Recipe source
            String url = recipeJson.get("url").getAsString();  // Recipe URL
            int calories = recipeJson.get("calories").getAsInt();  // Calories

            // Instantiate Recipe object
            Recipe recipe = new Recipe(label, source, url, calories, imageUrl);
            recipes.add(recipe);
        }

        return recipes;
    }

}
