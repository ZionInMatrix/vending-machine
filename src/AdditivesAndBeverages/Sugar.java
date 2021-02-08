package AdditivesAndBeverages;

/**
 * Sugar class. Sugar is a subclass of Additive.
 */

public class Sugar extends Additive {

    private String name = "Cukr";

    /**
     * Creates a Sugar object.
     * Sugar uses its parent constructor and sets its own name.
     */
    public Sugar() {
        super();
        this.setName(name);
    }//end constructor

}//end Sugar