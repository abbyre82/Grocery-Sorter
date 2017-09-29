import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class provides the file reader methods for reading ingredient data files and recipe data files 
 * for the GroceryMatch program.  Do not change method signatures.  Do implement the missing method bodies.
 */
public class Loaders {
    
    // DO NOT CHANGE THESE CLASS CONSTANTS
    public static final String GROCERY_FILE_IO_ERROR_MSG = "IOError when loading grocery lists";
    public static final String RECIPE_FILE_IO_ERROR_MSG = "IOError when loading recipes";
    public static final String OUTPUT_FILENAME = "remaining.txt" ;
    
    /**
     * 1. Load groceries from file, each line of the file indicates an ingredient and its quantity.
     * 2. Each ingredient is in the format of "name : quantity", the number of spaces between name, colon and quantity can be any.
     *    And there may be leading and trailing spaces in a line.
     * 3. Name of ingredient may have duplicate, this means there may be multiple lines with the same ingredient name.
     *    If names are duplicated, their quantities should be summed up.
     * 4. If a line does not match the above mentioned format, ignore the line and continue reading the next line of ingredients.
     * 5. If an IOException happens, print GROCERY_FILE_IO_ERROR_MSG, and throw the exception.
     * 
     * @param filename The name of the file that contains the list of ingredients for the grocery.
     * @return A grocery list that includes all of the ingredients that were were properly read from the file.
     * @throws IOException if the filename does not exist, the error msg is displayed and the exception is thrown to calling method
     */
    public static GroceryList loadGroceriesFromFile(String filename) throws IOException {
    	Scanner input = null; 
    	GroceryList grocerylist = new GroceryList();
    	try{
    	
    	File inFile = new File(filename);
    	
    	input = new Scanner(inFile);	
    	while(input.hasNextLine()){ //continues to read until there isn't
			//any content
        	Iterator<Ingredient> groceryItr = grocerylist.iterator();

    		String line = input.nextLine().trim();
    		String[] parts = line.split(":");
    		
    		if(parts.length == 2){
    					String name = parts[0].trim();
    					double quantity = Double.parseDouble(parts[1].trim());
    					boolean flag = false;
    					int count = 0;
    					while(groceryItr.hasNext()){ 
    						count++;
    						Ingredient temp = groceryItr.next();
    						if(temp.getName().equals(name)){
    							temp.setQuantity(temp.getQuantity() + quantity);
    							flag = true;
    							break;
    						}   										
    					}
    					if(!flag || count == 0){
    						Ingredient store = new Ingredient(name,quantity);
    						grocerylist.add(store);				
    					}
    				}
    			}
    	
    	
    	
    	}catch(IOException e){
    		System.out.println(GROCERY_FILE_IO_ERROR_MSG);
    	}finally{
    		input.close();
    	}
    	return grocerylist;		
    	//Double error?
    			//IO Exception?
    			//close scanner
    	}	
   
    
    /**
     * 1. Load recipes from file, each line of the file indicates a recipe.
     * 2. Each recipe is in the format "name -> ingredient1-name: ingredient1-quantity, ingredient2-name: ingredient2-quantity"
     * 3. The number of ingredients in a recipe can be any.
     * 4. The number of spaces between name and quantity can be any, and there may be leading and trailing spaces.
     * 5. For simplicity, names of recipes will not have duplication, names of ingredients in a recipe will not have duplication, the format of the recipe is guaranteed to be correct.
     * 6. Names of ingredients might not be in GroceryList, this means you need to buy this ingredient if you want to use this recipe.
     * 7. If an IOException happens, print RECIPE_FILE_IO_ERROR_MSG, and throw the exception.
     * 
     * @param filename The name of a file containing recipe information.
     * @return A recipe list containing the recipes read from the named file.
     * @throws IOException if the filename does not exist, the error msg is displayed and the exception is thrown to calling method
     */
    public static RecipeList loadRecipesFromFile( String filename ) throws IOException {
    	Scanner input = null; 
    	RecipeList recipelist = new RecipeList();
    	try{
    	File inFile = new File(filename);
    	
    	input = new Scanner(inFile);
    	
    	while(input.hasNextLine()){ //continues to read until there isn't
			//any content
    		
    		String line = input.nextLine();
    		String[] parts = line.split("-> ");
    		String recipeName = parts[0].trim();
    		ArrayList<Ingredient> ingredList = new ArrayList<Ingredient>();
    		String[] parts2 = parts[1].split(", ");
    		
    		for(int i = 0; i<parts2.length; i++){
    			String item = parts2[i].trim();
    			String[] parts3 = item.split(": ");
    			String ingredientName = parts3[0];
    			Double ingredientQuantity = Double.parseDouble(parts3[1]);
    			Ingredient ingredient = new Ingredient(ingredientName, ingredientQuantity);
    			ingredList.add(ingredient);
    		}
    		
    		Recipe recipe = new Recipe(recipeName,ingredList);
    		recipelist.add(recipe);	
    	}
    	} catch(IOException e){
    		System.out.println(RECIPE_FILE_IO_ERROR_MSG);
    	} finally{
    		input.close();
    	}
    	return recipelist;
    	//#6??
    	//IOException?
    	//close scanner
    	

    }

    /** 
     * Write the GroceryList items to the specified file.
     *
     * Each ingredient is written to the file in the order that the ingredient is found in the GrocerList
     * the format for each line is:
     *
     * ingredient_name: amount
     *
     * @param grocery list of ingredients
     * @param name of the file to write them to.
     * @throws FileNotFoundException 
     */
    public static void write(GroceryList groceries, String filename){
    	Iterator<Ingredient> groceryItr = groceries.iterator();
    	File file = new File(filename);
	    PrintWriter printWriter = null; 
    		try{
    	    printWriter = new PrintWriter(file);
    	    while(groceryItr.hasNext()){
    	    	Ingredient temp = groceryItr.next();
    	    	printWriter.println(temp.getName() + ": " 
    	    			+ temp.getQuantity());
    	    }
    		}catch(FileNotFoundException e){
    			System.out.println(OUTPUT_FILENAME);
    		}finally{
    			printWriter.close();
    		}
    	}
    }
       
//throws exception??
//closing printWriter


