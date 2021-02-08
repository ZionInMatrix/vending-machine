package AdditivesAndBeverages;

/**
 * Lemon class. Lemon is a subclass of Additive.
 */

public class Lemon extends Additive {

    private String name = "Citrón";

    /**
     * Creates a Lemon object.
     * Lemon uses its parent constructor and sets its own name.
     */
    public Lemon() {
        super();
        this.setName(name);
    }//end constructor

}//end Lemon