package AdditivesAndBeverages;

/**
 * Tea class. Tea is a subclass of Beverage.
 */

import java.util.ArrayList;

public class Tea extends Beverage {

    private String name = "Čaj";
    private int price = 15;

    /**
     * Creates a Tea object.
     * Tea uses its parent constructor (Beverage) and sets its own name and
     * price.
     */
    public Tea() {
        super();
        this.setName(this.name);
        this.setPrice(this.price);
    }//end constructor

    //Overrides Beverage's fillAdditives method
    protected void fillAdditives() {
        this.additives = new ArrayList<Additive>();
        this.additives.add(new Sugar());
        this.additives.add(new Lemon());
    }//end fillAdditives

    //Overrides Beverage's fillTypes method
    protected void fillTypes() {
        this.types = new ArrayList<Beverage>();
        this.types.add(new BlackTea());
    }

}//end Tea