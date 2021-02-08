package AdditivesAndBeverages;

/**
 * Decaf class. Decaf is a subclass of Coffee.
 */

public class Decaf extends Coffee {

    private String name = "Káva bez kofeinu";

    /**
     * Creates a Decaf object.
     * Decaf uses its parent constructor and sets its own name.
     */
    public Decaf() {
        super();
        this.setName(name);
    }//end constructor

    //Overrides Coffee's array of types
    //Decaf has no subtypes
    protected void fillTypes() {
        this.types = null;
    }//end fillTypes

}//end Decaf