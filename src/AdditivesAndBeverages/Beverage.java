package AdditivesAndBeverages;

/**
 * Beverage class. All of the beverages are subclasses of this class.
 * Each Beverage maintains its name, its price, its types, and its
 * available additives.
 */

import java.util.ArrayList;
import java.text.NumberFormat;

public abstract class Beverage {
    private String name = "";
    private int price = 0;
    protected ArrayList<Additive> additives;
    protected ArrayList<Beverage> types;

    /**
     * Creates a Beverage.
     * The beverage has a list of its additives and subtypes.
     */
    public Beverage() {
        this.fillAdditives();
        this.fillTypes();
    }//end constructor

    /**
     * Returns the available additives for the beverage.
     *
     * @return Array of additives
     */
    public ArrayList<Additive> getAdditives() {
        return this.additives;
    }//end getAdditives

    /**
     * Returns the available types for the beverage
     *
     * @return Array of beverages
     */
    public ArrayList<Beverage> getTypes() {
        return this.types;
    }//end getTypes

    /**
     * Returns the price of the beverage
     *
     * @return price of the beverage
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the price of a beverage.
     *
     * @param The new price of the beverage
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the name of the beverage.
     *
     * @return The name of the beverage
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the name and price of the beverage
     *
     * @return formatted String containing the name of price
     */
    public String toString() {
        NumberFormat currencyFormatter = NumberFormat.getIntegerInstance();
        return this.getName() + "   " + currencyFormatter.format(this.getPrice()) + " Kč";
    }

    //Sets the name of the beverage
    protected void setName(String name) {
        this.name = name;
    }//end setName

    //Fills the list of additives for the beverage
    //Null to be overridden by subclasses
    protected void fillAdditives() {
        this.additives = null;
    }

    //Fills the types of of the beverage
    //Null to be overridden by subclasses
    protected void fillTypes() {
        this.types = null;
    }

}//end Beverage