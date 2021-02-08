package AdditivesAndBeverages;

/**
 * Chicken Bouillon class. The class is a subclass of Soup.
 */

public class ChickenBouillon extends Soup {

    /**
     * Creates a Chicken Bouillon object.
     * The object uses it's parent constructor (Soup) and sets its own name.
     */
    public ChickenBouillon() {
        super();
        String name = "Kuřecí vývar";
        this.setName(name);
    }//end constructor

    //Overrides the Soup's list of types
    //Chicken Bouillon has no subtypes
    protected void fillTypes() {
        this.types = null;
    }//end fillTypes

}//end ChickenBouillon