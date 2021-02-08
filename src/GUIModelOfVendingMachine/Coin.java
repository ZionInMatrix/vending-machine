package GUIModelOfVendingMachine;

/**
 * Coin class.
 * Coin types that can be used in the Bank.
 */
public enum Coin {
    PĚT(5),
    DESET(10),
    DVACET(20);

    private final int value;

    private Coin(int v) {
        this.value = v;
    }//end Constructor

    /**
     * Returns the value of the Coin.
     * Each coin is associated with its respective value. For example, a PÉT
     * is 5 Kč.
     *
     * @return value of coin
     */
    public int value() {
        return this.value;
    }//end value

}//end Coin