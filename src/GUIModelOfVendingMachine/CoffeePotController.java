package GUIModelOfVendingMachine;


import AdditivesAndBeverages.Additive;
import AdditivesAndBeverages.Beverage;

public class CoffeePotController {
    private CoffeePotModel model;
    private CoffeePotView view;

    /**
     * Creates a CoffeePotController from the given model.
     * Instantiates a view for the model.
     *
     * @param CoffeePotModel
     */
    public CoffeePotController(CoffeePotModel model) {
        this.model = model;
        this.view = new CoffeePotView(this, model);
        view.createBasicView();
    }

    /**
     * Updates the payment.
     * Adds the payment to the model and updates the view.
     *
     * @param coin
     */
    public void updatePayment(Coin coin) {
        model.addToPayment(coin);
        view.updatePaymentDisplay();
        view.enableReturnChangeBtn(true);
    }

    /**
     * Updates the view's change display
     */
    public void returnChange() {
        view.updateChangeDisplay();
        view.updatePaymentDisplay();
        view.enableReturnChangeBtn(false);
    }

    /**
     * Sets the ordered beverage.
     * Informs the model of the ordered beverage and updates the
     * order display.
     *
     * @param bev
     */
    public void setBeverage(Beverage bev) {
        model.setOrderedBeverage(bev);
        view.updateOrderDisplay();
        view.enableDispenserBtn(true);
    }

    /**
     * Sets the orderedAdditives.
     * Informs the model of the ordered additives and updates the
     * order display.
     *
     * @param additive
     */
    public void setOrderedAdditives(Additive additive) {
        model.addToOrderedAdditives(additive);
        view.updateOrderDisplay();
    }

    /**
     * Updates the view to reflect the beverage dispensable.
     */
    public void dispenseBeverage() {
        view.updateDispenserDisplay();
        if (model.beverageIsDispensed) view.enableDispenserBtn(false);
        view.updatePaymentDisplay();
    }

    /**
     * Resets the model and the view for another order.
     */
    public void resetCoffeePot() {
        model.resetOrder();
        view.resetAll();
    }

}//end CoffeePotController