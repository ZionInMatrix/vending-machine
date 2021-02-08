package AdditivesAndBeverages;

/**
 * GarlicSoup class. The class is a subclass of Soup.
 */

public class GarlicSoup extends Soup {

    private String name = "Česneková polévka";

    /**
     * Creates a GarlicSoup object.
     * The object uses it's parent constructor (Soup) and sets its own name.
     */
    public GarlicSoup() {
        super();
        this.setName(name);
    }//end constructor

    //Overrides the Soup's list of types
    //GarlicSoup has no subtypes
    protected void fillTypes() {
        this.types = null;
    }//end fillTypes

}//end GarlicSoup