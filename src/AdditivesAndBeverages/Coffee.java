package AdditivesAndBeverages;

/**
 * Coffee class. Coffee is a subclass of Beverage.
 */

import java.util.ArrayList;

public class Coffee extends Beverage {

    private String name = "Káva";
    private int price = 25;

    /**
     * Creates a Coffee object.
     * Coffee uses its parent constructor (Beverage) and sets its own name and
     * price.
     */

    public Coffee() {
        super();
        this.setName(name);
        this.setPrice(price);
    }//end constructor

    //Overrides Beverage's fillAdditives method
    protected void fillAdditives() {
        this.additives = new ArrayList<>();
        this.additives.add(new Milk());
        this.additives.add(new Sugar());
    }//end fillAdditives

    //Overrides beverage's fillTypes method
    protected void fillTypes() {
        this.types = new ArrayList<>();
        this.types.add(new Regular());
        this.types.add(new Decaf());
    }//end fillTypes

}//end Coffee