import FactoryAndInventory.BeverageFactory;
import GUIModelOfVendingMachine.Bank;
import GUIModelOfVendingMachine.CoffeePotController;
import GUIModelOfVendingMachine.CoffeePotModel;

public class RunnerOFVendingMachine {
    public static void main(String[] args) {
        CoffeePotModel model = new CoffeePotModel(new Bank(), new BeverageFactory());
        CoffeePotController controller = new CoffeePotController(model);
    }
}
