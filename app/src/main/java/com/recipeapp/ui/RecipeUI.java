package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    //設問5
    private void displayRecipes() throws IOException{
        //1.dataHandlerをCSVDataHandle型にダウンキャスト。
        //2.if分岐を生成（readData()の戻り値が無い場合とある場合)
        //3.ある場合の処理を記述
        //4.readData()で受け取った戻り値(リスト)をforeachメソッドで全て仕様の表示に変換する。
        
        CSVDataHandler csvDataHandler = (CSVDataHandler) dataHandler;
        if(csvDataHandler.readData().size() == 0){
            System.out.println("No recipes available.");
        }else{
            System.out.println("Recipes:");
            csvDataHandler.readData().forEach(data ->{
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + data.getName());
                System.out.print("Main Ingredients:  ");
                data.getIngredients().forEach(ingredient ->{
                    System.out.print(ingredient.getName() + ", ");
                });
                System.out.println();
            });
        }
        
    }

    //設問6
    private void addNewRecipe() throws IOException{
        //1.このメソッドは相手から入力してもらったデータをRecipe型に変換し、CSVDataHandlerのwriteDataメソッドを
        //  呼び出し、引数として変換したRecipe型を渡す。
        //2.Recipe型作成には（String型 newRecipeName,ArrayList<Ingredient>)が必要
        //3.最初BufferReaderでnewRecipeNameを入力してもらう。
        //4.次にIngredientの入力。(無限ループを作成し、doneと入力されたとき、無限ループをぬける)
        //5.Ingredientの入力をIngredient型に変換し、ArrayList<Ingredient>に格納
        //6.入力データをRecipe型に変換。
        //7.dataHandlerをCSVDataHandle型にダウンキャスト。
        //8.CSVDataHandlerのwriteDataメソッドに引数として6.で変換したRecipe型を渡して終了
        System.out.println("Adding a new recipe.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter recipe name:");
        String newRecipeName = reader.readLine();
        ArrayList<Ingredient> newIngredientsList = new ArrayList<>();
        System.out.println("Enter ingredients (type 'done' when finished):");
        while (true) {
            System.out.print("Ingredient: ");
            String newIngredient = reader.readLine();
            if(newIngredient.equals("done")){
                System.out.println("Recipe added successfully.");
                break;
            }else{
                Ingredient ingredient = new Ingredient(newIngredient);
                newIngredientsList.add(ingredient);
            }
        }
        Recipe newRecipe = new Recipe(newRecipeName, newIngredientsList);
        CSVDataHandler csvDataHandler = (CSVDataHandler) dataHandler;
        csvDataHandler.writeData(newRecipe);
    }

    //設問7
    private void searchRecipe(){
        
    }

    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }
}
