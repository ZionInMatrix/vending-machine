package FactoryAndInventory;

/**
 * Beverage Factory class. Maintains a list of available beverages and
 * provides methods for the creation of the beverages and addition of additives.
 */

import AdditivesAndBeverages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BeverageFactory {
    private ArrayList<Additive> additiveList;
    private ArrayList<Beverage> beverageList;
    private final AdditiveInventory additiveInventory;

    /**
     * Creates a Beverage Factory
     * The instantiation fills the list of available beverages.
     */
    public BeverageFactory() {
        this.fillLists();
        this.additiveInventory = this.createAdditiveInventory();
    }//end constructor

    /**
     * Creates a beverage.
     * Creates a beverage from a given order number. Each beverage has an order
     * number associated with it. The order number corresponds to the beverage's
     * position in the list of available beverages.
     * @param The order number of the beverage
     */
    public Beverage createBeverage(int order) {
        Beverage beverage = beverageList.get(order);
        return beverage;
    }//end createBeverage

    /**
     * Creates a beverage subtype. Only used when applicable.
     * The method is similar to how the createBeverage method works, but the order
     * number is from the list of subtypes instead of the list of all beverages.
     * @param array of beverage types
     * @param order number of the beverage
     * @return the ordered beverage
     */
    public Beverage createBeverageType(ArrayList<Beverage> types, int order) {
        return types.get(order);
    }

    /**
     * Returns the list of available beverages.
     * The Beverage Factory has a pre-set list of beverages it can create. The
     * returned list is of those available beverages.
     * @return list of available beverages
     */
    public ArrayList<Beverage> getBeverageList(){
        return this.beverageList;
    }

    /**
     * Returns the list of available additives.
     * The Beverage Factory has a pre-set list of additives it can add
     * to beverages. The actual available additives for a selected beverage
     * depends on the beverage. The returned list is of those available additives.
     * @return list of available additives
     */
    public ArrayList<Additive> getAdditiveList() {
        return this.additiveList;
    }

    /**
     * Prepares a beverage order given the beverage and the ordered additives.
     * @param The beverage ordered
     * @param List of additives ordered with the beverage
     * @return String of beverage preparation process
     */
    public String prepare(Beverage beverage, ArrayList<Additive> orderedAdditives) {
        StringBuilder output = new StringBuilder();
        output.append("Výdej ").append(beverage.getName()).append("...\n");
        if(orderedAdditives != null) {
            for(Additive additive : orderedAdditives) {
                output.append("Adding ").append(additive.getName()).append("...\n");
                this.additiveInventory.useAdditive(additive);
            }
        }
        return output.toString();
    }//end prepare

    //Fills the list of available beverages that the factory can create
    private void fillLists() {
        beverageList = new ArrayList<>();
        beverageList.add(new Coffee());
        beverageList.add(new Soup());
        beverageList.add(new Tea());
        beverageList.add(new HotChocolate());


        additiveList = new ArrayList<>();
        additiveList.add(new Milk());
        additiveList.add(new Lemon());
        additiveList.add(new Cinnamon());
        additiveList.add(new Sugar());
    }//end fillList

    /**
     * Returns if an additive is available.
     * Available refers to its stock level. If the additive is
     * in stock, it is available.
     * @param additive
     * @return Boolean if the additive is in stock
     */
    public boolean isAvailable(Additive additive) {
        return additiveInventory.isInStock(additive);
    }

    public String printAdditiveInventory() {
        return this.additiveInventory.toString();
    }

    //Instantiate additive inventory used for the factory
    private AdditiveInventory createAdditiveInventory() {
        return new AdditiveInventory();
    }

    /************************************/
    private class AdditiveInventory {
        private final HashMap<String, Integer> additiveInventory;

        //Creates a coin inventory
        //Instantiates a HashMap of Additive stock level
        public AdditiveInventory() {
            additiveInventory = new HashMap<>();
            this.fillStock();
        }

        //Used by the constructor.
        //Instantiates the HashMap of additives and and fills their stock
        private void fillStock() {
            for(int i = 0; i < getAdditiveList().size(); i++) {
                int defaultFill = 3;
                additiveInventory.put(getAdditiveList().get(i).getName(), defaultFill);
            }
        }//end fillStock

        //Returns a formatted output of the additive inventory
        //Additive and its stock level (in number of units)
        public String toString() {
            StringBuilder output = new StringBuilder();
            for(Map.Entry<String,Integer> entry : additiveInventory.entrySet()){
                output.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }
            return output.toString();
        }//end toString

        //Use Additive
        //Decrements stock level of the additive
        public void useAdditive(Additive additive) {
            //Decrement stock
            additiveInventory.put(additive.getName(), additiveInventory.get(additive.getName())-1);
        }//end useAdditive

        //Returns true if additive is in stock
        public boolean isInStock(Additive additive) {
            return additiveInventory.get(additive.getName()) > 0;
        }//end isInStock

    }//end AdditiveInventory
}//end BeverageFactory