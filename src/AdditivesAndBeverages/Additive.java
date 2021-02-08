package AdditivesAndBeverages;

/**
 * Additive class. All of the additives are subclasses of this class.
 * Each Additive maintains its name.
 */

public class Additive {
    private String name = "";

    /**
     * Creates an Additive
     */
    public Additive() {

    }//end constructor

    /**
     * Returns the name of the Additive
     * @return the name of the Additive
     */

    public String getName(){
        return this.name;
    }//end getName

    /**
     * Returns a formatted String with the Additive's name
     * @return String of the name
     */
    public String toString() {
        return this.getName();
    }//end toString

    //Sets the name of the Additive
    //Protected to only be used by subclasses
    protected void setName(String name) {
        this.name = name;
    }//end setName

}//end Additives