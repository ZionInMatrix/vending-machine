package AdditivesAndBeverages;

/**
 * BlackTea is a subtype of Tea.
 */

public class BlackTea extends Tea {
    private String name = "Černý čaj";

    /**
     * Creates a Chamomile Tea object.
     * Chamomile uses its parent constructor and sets its own name.
     */
    public BlackTea() {
        super();
        this.setName(name);
    }//end constructor

    //Overrides Coffee's array of types
    //Chamomile has no subtypes
    protected void fillTypes() {
        this.types = null;
    }//end fillTypes
}