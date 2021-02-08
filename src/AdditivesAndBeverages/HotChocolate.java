package AdditivesAndBeverages;

/**
 * Hot Chocolate class. Hot Chocolate is a subclass of Beverage.
 */

import java.util.ArrayList;

public class HotChocolate extends Beverage {

    private String name = "Horká čokoláda";
    private int price = 35;

    /**
     * Creates a Hot Chocolate object.
     * Hot Chocolate uses its parent constructor (Beverage) and sets its own name and
     * price.
     */
    public HotChocolate() {
        super();
        this.setName(this.name);
        this.setPrice(this.price);
    }//end constructor

    //Overrides beverage's fillTypes method
    protected void fillTypes() {
        this.types = new ArrayList<>();
        this.types.add(new OriginalHotChocolate());
        this.types.add(new PeppermintHotChocolate());
    }//end fillTypes

    //Overrides Beverage's available additives
    //Hot Chocolate can have marshmallows
    protected void fillAdditives() {
        this.additives = new ArrayList<>();
        this.additives.add(new Cinnamon());
    }//end fillAdditives

}//end HotChocolate
