import java.util.ArrayList;
import java.util.Iterator;

/**
 * TODO COMPLETE THIS CLASS
 *
 */
public class RecipeList implements ListADT<Recipe> {
    
    // You may use an ArrayList<Recipe> as your internal data structure
	   private ArrayList<Recipe> recipes;
	   Iterator<Recipe> iterator;
	   public RecipeList(){
		   recipes = new ArrayList<Recipe>();
	   }
	   
	   public void add(Recipe item){
		   recipes.add(item);
	   }
	   
	   public void add(int pos, Recipe item){
		   if(pos< 0 || pos>recipes.size()){
			   throw new IndexOutOfBoundsException();
		   }
		   recipes.add(pos,item);
	   }
	   
	   public boolean contains(Recipe item){
		   iterator = recipes.iterator();
		   
		   while(iterator.hasNext()){
			   Recipe tempRecipe = iterator.next();
			   if(tempRecipe.getRecipeName().equals(item.getRecipeName())){
				   return true;
			   }
		   }
		   return false;
	   }
	   
	   public int size(){
		   return recipes.size();
	   }
	   
	   public boolean isEmpty(){
		   if(recipes.size() == 0){
			   return true;
		   }
		   return false;
	   }
	   
	   public Recipe get(int pos){
		   if (pos < 0 || pos >= recipes.size()) {
		        throw new IndexOutOfBoundsException();
		    }
		   return recipes.get(pos);
	   }
	   public Recipe remove(int pos){
		   if (pos < 0 || pos >= recipes.size()) {
		        throw new IndexOutOfBoundsException();
		   }
		   Recipe ob = recipes.get(pos);
		   recipes.remove(pos);
		   // return the removed item
		   return ob;
	   }
	   public Iterator<Recipe> iterator() {
		   Iterator<Recipe> itr = recipes.iterator();
		   return itr;
		   }
    
}
