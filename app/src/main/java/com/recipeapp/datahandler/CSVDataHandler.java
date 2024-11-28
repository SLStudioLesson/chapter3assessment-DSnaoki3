package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler{

    private String filePath;

    public CSVDataHandler(){
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath){
        this.filePath = filePath;
    }
    
    @Override
    public String getMode(){
        return "CSV";
    }

    //設問5
    @Override
    public ArrayList<Recipe> readData(){
        //1.returnのArrayList<Recipe>のリストを作成
        //2.ファイルを読み込み[,]で分割したArrayListを作成
        //3.2.のArrayList0番目をrecipeNameに格納
        //4.2.のArrayListから0番目削除
        //5.Recipeインスタンス化時の引数に必要なArrayList<Ingredient>を作成
        //6.2.のリストを全てingredientにインスタンス化したものを5.で作成したリストにaddする
        //7.Recipeインスタンス化(recipeName , 5.のリスト)
        //8.1.のリストに格納していく
        //9.1.のリストを戻り値として返却
        ArrayList<Recipe> recipesArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] array = line.split(",");
                ArrayList<String> lineList = new ArrayList<>(Arrays.asList(array));
                String recipeName = lineList.get(0);
                lineList.remove(0);
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                lineList.forEach(ingre ->{
                    Ingredient ingredient = new Ingredient(ingre);
                    ingredients.add(ingredient);
                });
                Recipe recipe = new Recipe(recipeName, ingredients);
                recipesArrayList.add(recipe);
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        return recipesArrayList;
    }

    //設問6
    @Override
    public void writeData(Recipe recipe){
        //1.引数で受け取ったRecipe型をfilePathに書き込む
        //2.BufferWriterで書き込みの準備(trueで上書き)
        //3.引数のgetName()で受け取ったRecipeNameを書き込む
        //4.引数のgetIngredients()をforeach()のループでString sに格納し、ファイルに再度書き込む
        //5.最後に改行し、終了。
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String input = recipe.getName() + ",";
            writer.write(input);
            recipe.getIngredients().forEach(ingredient ->{
                String s = ingredient.getName() + ",";
                try{
                    writer.write(s);
                }catch (IOException e){
                    System.out.println("Failed to add new recipe:" + e.getMessage());
                }
            });
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to add new recipe:" + e.getMessage());
        }
    }

    //設問7
    @Override
    public ArrayList<Recipe> searchData(String keyword) {
        //1.
        return null;
    }
}
