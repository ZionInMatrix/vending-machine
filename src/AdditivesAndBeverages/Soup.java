package AdditivesAndBeverages;

/**
 * Soup class. Soup is a subclass of Beverage.
 */

import java.util.ArrayList;

public class Soup extends Beverage {

    private String name = "Polévka";
    private int price = 50;

    /**
     * Creates a Soup object.
     * Soup uses its parent constructor (Beverage) and sets its own name and
     * price.
     */
    public Soup() {
        super();
        this.setName(name);
        this.setPrice(price);
    }//end constructor

    //Overrides beverage's fillTypes method
    protected void fillTypes() {
        this.types = new ArrayList<>();
        this.types.add(new ChickenBouillon());
        this.types.add(new GarlicSoup());
    }//fillTypes

}//end Soup