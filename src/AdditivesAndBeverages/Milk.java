package AdditivesAndBeverages;

/**
 * Milk class. Mil is a subclass of Additive.
 */

public class Milk extends Additive {

    private String name = "Mleko";

    /**
     * Creates a Milk object.
     * Milk uses its parent constructor and sets its own name.
     */
    public Milk() {
        super();
        this.setName(name);
    }

}//end Milk