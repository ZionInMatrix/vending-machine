package GUIModelOfVendingMachine;

/**
 * Bank class that maintains all financial transactions
 * Tracks coins and total amount in Bank
 */

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

public class Bank {

    private int payment = 0;
    private final CoinInventory coinInventory;

    /**
     * Creates a Bank object.
     * The Bank object instantiates an inventory of Coins, and calculates the
     * total value in the Bank.
     */
    public Bank() {
        coinInventory = createCoinInventory();
    }//end Bank constructor

    /**
     * Returns an array of Coin types in the bank.
     *
     * @return Array of Coin types
     */
    public ArrayList<Coin> getCoinList() {
        ArrayList<Coin> coinTypes = new ArrayList<>();
        for (Map.Entry<Coin, Integer> entry : coinInventory.getEntrySet()) {
            coinTypes.add(entry.getKey());
        }
        return coinTypes;
    }//end getCoinList

    /**
     * Adds a payment to the bank.
     * The payment is given in the form of a coin enum. The coin is
     * added to the coin inventory. The payment is incremented by the appropriate
     * Coin type value.
     *
     * @param a Coin
     */
    public void collectPayment(Coin coin) {
        coinInventory.fillCoin(coin, 1);
        this.payment += coin.value();
    }//end collectPayment

    /**
     * Returns the current value of the payment.
     *
     * @return The payment in integer form.
     */
    public int getPayment() {
        return this.payment;
    }//end get payment

    /**
     * Compares the current payment to the given price. If the payment is enough
     * to cover the price, then the method returns true. It returns false otherwise.
     *
     * @param The price to be checked against the payment.
     * @return Boolean value of whether the payment is enough to cover the price.
     */
    public boolean checkPayment(int price) {
        return this.payment >= price;
    }//end checkPayment

    /**
     * Calculate the change from a given price.
     * The change is evaluated as the difference between the current payment and the
     * given price.
     *
     * @param price
     * @return change
     */
    public int calculateChange(int price) {
        return this.payment - price;
    }//end calculateChange

    /**
     * Returns the change, or leftover amount between the payment and the given price.
     * Change is returned in the format of a String of coins. The payment is reset to zero for following transactions.
     *
     * @param The price
     * @return String of coins
     */
    public String returnChange(int price) {
        StringBuilder output = new StringBuilder();
        int change = this.calculateChange(price);
        output.append("Vaše změna je : ");
        //Return coins if change is greater than 0
        while (change > 0 && !this.isEmpty()) {
            int temp = change;
            for (Map.Entry<Coin, Integer> entry : coinInventory.getEntrySet()) {
                if (change % entry.getKey().value() == 0
                        && change - entry.getKey().value() >= 0
                        && coinInventory.hasEnough(entry.getKey())) {
                    //Use coin
                    this.coinInventory.useCoin(entry.getKey());
                    //Update change
                    change -= entry.getKey().value();
                    //Add coin to change list
                    output.append(entry.getKey().toString()).append(" ");
                }
            }
            //If it goes through the available change and nothing happens
            //The bank does not have enough of the right change
            if (temp == change || this.isEmpty()) {
                output.append("\nJsme mimo změnu.");
                break;
            }

        }
        //Reset payment
        this.resetPayment();
        return output.toString();
    }//end returnChange

    /**
     * Returns the change.
     *
     * @return String for change or an error message
     */
    public String returnChange() {
        StringBuilder output = new StringBuilder();
        int change = this.payment;
        output.append("Vaše změna je : ");
        //Return coins if change is greater than 0
        while (change > 0 && !this.isEmpty()) {
            int temp = change;
            for (Map.Entry<Coin, Integer> entry : coinInventory.getEntrySet()) {
                if (change % entry.getKey().value() == 0
                        && change - entry.getKey().value() >= 0
                        && coinInventory.hasEnough(entry.getKey())) {
                    //Use coin
                    this.coinInventory.useCoin(entry.getKey());
                    //Update change
                    change -= entry.getKey().value();
                    //Add coin to change list
                    output.append(entry.getKey().toString()).append(" ");
                }
            }
            //If it goes through the available change and nothing happens
            //The bank does not have enough of the right change
            if (temp == change || this.isEmpty()) {
                output.append("\nJsme mimo změnu.");
                break;
            }

        }
        //Reset payment
        this.resetPayment();
        return output.toString();
    }

    /**
     * Resets the current payment.
     */
    public void resetPayment() {
        this.payment = 0;
    }//end resetPayment

    /**
     * Sets the payment to the given amount
     *
     * @param payment
     */
    public void setPayment(int payment) {
        this.payment = payment;
    }

    /**
     * Checks if the bank is out of coins.
     *
     * @return Boolean value if the bank is empty
     */
    public boolean isEmpty() {
        return coinInventory.getTotalValue() == 0;
    }//end isEmpty

    /**
     * Prints a formatted String of the Coins in the coin inventory.
     * The coin inventory maintains a list of coin types and the number of each
     * type. This is returned as a formatted output.
     *
     * @return String of the coin inventory
     */
    public String printCoinInventory() {
        return coinInventory.toString();
    }//end printCoinInventory

    /**
     * Returns a formatted String of the total value in the bank.
     *
     * @return Formatted String of the total value of the bank.
     */
    public String toString() {
        return "Stroj má " + coinInventory.getTotalValue() + " v bance.";
    }//end toString

    //Returns a new Coin Inventory
    private CoinInventory createCoinInventory() {
        return new CoinInventory();
    }//end createCoinInventory

    /**************************************************/
    private static class CoinInventory {
        private final HashMap<Coin, Integer> coinInventory;
        private int totalValue = 0;

        //Creates a coin inventory
        //Instantiates a HashMap of Coin values
        public CoinInventory() {
            coinInventory = new HashMap<>(3); //3 types of Coins
            this.instantiateCoins();
            this.fillAll();
        }

        //Returns the current value of the coin inventory
        public double getTotalValue() {
            return totalValue; //Convert coins to dollars
        }

        //Used by the constructor.
        //Instantiates the HashMap of coins and adds the four Coin enum values
        private void instantiateCoins() {
            coinInventory.put(Coin.DVACET, 0);
            coinInventory.put(Coin.DESET, 0);
            coinInventory.put(Coin.PĚT, 0);
        }

        //Returns a formatted output of the coin inventory
        //Coin type and number of coins
        public String toString() {
            StringBuilder output = new StringBuilder();
            for (Map.Entry<Coin, Integer> entry : coinInventory.entrySet()) {
                output.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }
            return output.toString();
        }//end toString

        //Use coins
        //Decrements number of coin type in bank
        public void useCoin(Coin coin) {
            //Decrement coin
            coinInventory.put(coin, coinInventory.get(coin) - 1);
            //Update total value in Bank
            totalValue -= coin.value();
        }//end useCoin

        //Returns entry set of inventory
        //Allows HashMap to be iterated in Bank
        public Set<Map.Entry<Coin, Integer>> getEntrySet() {
            return coinInventory.entrySet();
        }//end getEntrySet

        //Checks if inventory has more than 0 of coin type
        public boolean hasEnough(Coin coin) {
            return coinInventory.get(coin) > 0;
        }//end hasEnough

        //Adds to coin type in inventory
        public void fillCoin(Coin coin, int numOfCoins) {
            coinInventory.put(coin, coinInventory.get(coin) + numOfCoins);
            totalValue += coin.value() * numOfCoins;
        }

        public void fillAll() {
            for (Map.Entry<Coin, Integer> entry : coinInventory.entrySet()) {
                int defaultFill = 10;
                entry.setValue(defaultFill);
                totalValue += entry.getKey().value() * defaultFill;
            }
        }
    }//end CoinInventory
}//end Bank