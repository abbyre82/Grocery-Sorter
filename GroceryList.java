import java.util.ArrayList;
import java.util.Iterator;

/**
 * TODO COMPLETE THIS CLASS
 *
 */
public class GroceryList implements ListADT<Ingredient>  {

    // you may use an ArrayList<Ingredient> as your internal data structure
   private ArrayList<Ingredient> ingredients;
   Iterator<Ingredient> iterator;
   public GroceryList(){
	   ingredients = new ArrayList<Ingredient>();
   }
   
   public void add(Ingredient item){
	   ingredients.add(item);
   }
   
   public void add(int pos, Ingredient item){
	   if(pos< 0 || pos>ingredients.size()){
		   throw new IndexOutOfBoundsException();
	   }
	   ingredients.add(pos,item);
   }
   
   public boolean contains(Ingredient item){
	   iterator = ingredients.iterator();
	   
	   while(iterator.hasNext()){
		   Ingredient tempIngred = iterator.next();
		   if(tempIngred.getName().equals(item.getName())){
			   return true;
		   }
	   }
	   return false;
	   
   }
   
   public int size(){
	   return ingredients.size();
   }
   
   public boolean isEmpty(){
	   if(ingredients.size() == 0){
		   return true;
	   }
	   return false;
   }
   
   public Ingredient get(int pos){
	   if (pos < 0 || pos >= ingredients.size()) {
	        throw new IndexOutOfBoundsException();
	    }
	   return ingredients.get(pos);
   }
   public Ingredient remove(int pos){
	   if (pos < 0 || pos >= ingredients.size()) {
	        throw new IndexOutOfBoundsException();
	   }
	   Ingredient ob = ingredients.get(pos);
	   ingredients.remove(pos);
	   // return the removed item
	   return ob;
   }
   public Iterator<Ingredient> iterator() {
	   Iterator<Ingredient> itr = ingredients.iterator();
	   return itr;
	   }
}

