package GUIModelOfVendingMachine;


import AdditivesAndBeverages.Beverage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class CoffeePotView {
    private CoffeePotController controller;
    private CoffeePotModel model;

    JFrame frame;
    Container contentPane;
    JButton orderAgainBtn;
    JPanel orderPanel;
    JTextArea orderDisplay;
    JLabel orderLabel;
    JPanel beverageOptionPanel;
    JPanel beveragePanel;
    JButton[] beverageBtns;
    JPanel typePanel;
    JButton[] typeBtns;
    JPanel additivePanel;
    JButton[] additiveBtns;
    JPanel dispenserPanel;
    JPanel actionPanel;
    JTextArea actionField;
    JButton dispenserBtn;
    JButton dispenseBtn;
    JButton returnChangeBtn;
    JPanel returnChangePanel;
    JPanel coinPanel;
    JPanel paymentPanel;
    JLabel paymentLabel;
    JButton[] coinBtns;
    Dimension beverageBtnDim = new Dimension(160, 50);

    /**
     * Creates a CoffeePotView.
     *
     * @param controller
     * @param model
     */
    public CoffeePotView(CoffeePotController controller, CoffeePotModel model) {
        this.controller = controller;
        this.model = model;
    }

    /**
     * Sets up the basic CoffeePotView.
     * Creates the payment, action, order, and option panels.
     */
    public void createBasicView() {
        frame = new JFrame("Jaké máte přání?");
        contentPane = frame.getContentPane();

        createPaymentView();
        createActionView();
        createOrderView();
        createBeverageOptionView();

        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Creates an interactable panel to collect payment.
     */
    public void createPaymentView() {
        paymentPanel = new JPanel();
        orderAgainBtn = new JButton("Objednat znovu");
        JButton spacer = new JButton();
        spacer.setPreferredSize(new Dimension(80, 0));
        paymentLabel = new JLabel("Aktuální platba: " + model.getPayment());
        paymentPanel.add(orderAgainBtn);
        paymentPanel.add(spacer);
        paymentPanel.add(paymentLabel);

        ActionListener orderAgainListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.resetCoffeePot();
            }
        };
        orderAgainBtn.addActionListener(orderAgainListener);

        coinBtns = new JButton[model.getAvailablePayment().size()];
        for (int i = 0; i < coinBtns.length; i++) {
            coinBtns[i] = new JButton(model.getAvailablePayment().get(i).toString());
            coinBtns[i].setPreferredSize(new Dimension(100, 20));
            paymentPanel.add(coinBtns[i]);
        }
        ActionListener paymentListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Coin coin = null;
                for (int i = 0; i < coinBtns.length; i++) {
                    if (e.getSource() == coinBtns[i]) {
                        coin = model.getAvailablePayment().get(i);
                    }
                }
                controller.updatePayment(coin);
            }
        };
        for (JButton coinBtn : coinBtns) {
            coinBtn.addActionListener(paymentListener);
        }

        paymentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.add(paymentPanel, BorderLayout.NORTH);
    }

    /**
     * Updates the payment display to reflect the current payment.
     */
    public void updatePaymentDisplay() {
        paymentLabel.setText("Aktuální platba: " + model.getPayment());
    }

    /**
     * Creates an interactable panel to dispense the beverage and
     * collect change.
     */
    public void createActionView() {
        actionField = new JTextArea(7, 40);
        actionField.setEditable(false);
        actionField.setLineWrap(true);
        actionField.setWrapStyleWord(true);
        JScrollPane actionScroller = new JScrollPane(actionField);

        dispenserBtn = new JButton("Vydat");
        dispenserBtn.setPreferredSize(new Dimension(130, 40));
        dispenserBtn.setEnabled(false); //False until beverage is selected
        ActionListener dispenseListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.dispenseBeverage();
            }
        };
        dispenserBtn.addActionListener(dispenseListener);

        returnChangeBtn = new JButton("Vrátit zmenu");
        returnChangeBtn.setPreferredSize(new Dimension(130, 40));
        returnChangeBtn.setEnabled(false); //false until change is entered
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(130, 0));
        ActionListener returnChangeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.returnChange();
            }
        };
        returnChangeBtn.addActionListener(returnChangeListener);

        actionPanel = new JPanel();
        actionPanel.add(dispenserBtn);
        actionPanel.add(actionScroller);
        actionPanel.add(returnChangeBtn);
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.add(actionPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the enabled status of the Dispenser button
     *
     * @param boolean
     */
    public void enableDispenserBtn(Boolean bool) {
        dispenserBtn.setEnabled(bool);
    }

    /**
     * Sets the enabled status of the return change button
     *
     * @param boolean
     */
    public void enableReturnChangeBtn(Boolean bool) {
        returnChangeBtn.setEnabled(bool);
    }

    /**
     * Updates the action panel to reflect a message about the ordered beverage.
     */
    public void updateDispenserDisplay() {
        actionField.setText(actionField.getText() + "\n" + model.dispenseBeverage());
    }

    /**
     * Updates the action panel to reflect a message about the change.
     */
    public void updateChangeDisplay() {
        actionField.setText(actionField.getText() + "\n" + model.returnChange());
    }

    /**
     * Creates an interactable panel to order beverages and additives.
     */
    public void createBeverageOptionView() {
        beverageOptionPanel = new JPanel(new GridLayout(1, 3));
        beveragePanel = new JPanel();
        typePanel = new JPanel();
        additivePanel = new JPanel();
        beverageOptionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        ;
        beverageOptionPanel.add(beveragePanel);
        beverageOptionPanel.add(typePanel);
        beverageOptionPanel.add(additivePanel);
        createBeverageView();
        contentPane.add(beverageOptionPanel, BorderLayout.CENTER);
    }

    /**
     * Creates an interactable panel displaying the available beverages.
     */
    public void createBeverageView() {
        beverageBtns = new JButton[model.getBeverageList().size()];
        for (int i = 0; i < beverageBtns.length; i++) {
            beverageBtns[i] = new JButton(model.getBeverageList().get(i).toString());
            beverageBtns[i].setPreferredSize(beverageBtnDim);
            beveragePanel.add(beverageBtns[i]);
        }

        ActionListener beverageListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.getOrderedBeverage() != null)
                    resetBeverageOptionView();
                for (int i = 0; i < beverageBtns.length; i++) {
                    if (e.getSource() == beverageBtns[i]) {
                        //Set ordered beverage to selected beverage
                        controller.setBeverage(model.getBeverageList().get(i));
                        createTypesView();
                        createAdditiveView();
                        beverageBtns[i].setEnabled(false);
                        //beverageBtns[i].setBackground(Color.blue);
                    } else {
                        beverageBtns[i].setEnabled(true);
                        beverageBtns[i].setBackground(null);
                    }
                }
            }
        };
        for (int i = 0; i < beverageBtns.length; i++) {
            this.beverageBtns[i].addActionListener(beverageListener);
        }
    }//end createBeverageView

    /**
     * Creates an interactable panel displaying the available beverage types.
     */
    public void createTypesView() {
        if (model.getOrderedBeverage().getTypes() == null) {
            JLabel label = new JLabel("Tento nápoj nemá žádné typy");
            typePanel.add(label);
        } else {
            ArrayList<Beverage> typeArray = model.getOrderedBeverage().getTypes();
            typeBtns = new JButton[typeArray.size()];
            for (int i = 0; i < typeBtns.length; i++) {
                this.typeBtns[i] = new JButton(typeArray.get(i).getName());
                typeBtns[i].setPreferredSize(beverageBtnDim);
                this.typePanel.add(this.typeBtns[i]);
            }

            ActionListener typeListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < typeBtns.length; i++) {
                        if (e.getSource() == typeBtns[i]) {
                            controller.setBeverage(typeArray.get(i));
                            //typeBtns[i].setBackground(Color.blue);
                            typeBtns[i].setEnabled(false);
                        } else {
                            typeBtns[i].setEnabled(true);
                        }
                    }
                }
            };
            for (int i = 0; i < typeBtns.length; i++) {
                this.typeBtns[i].addActionListener(typeListener);
            }
        }
        typePanel.setVisible(true);
    }//end createTypesView

    /**
     * Creates an interactable panel dispalying the available additives based
     * from the selected beverage.
     */
    public void createAdditiveView() {
        if (model.getOrderedBeverage().getAdditives() == null) {
            JLabel label = new JLabel("Neobsahuje žádné přísady");
            additivePanel.add(label);
        } else {
            this.additiveBtns = new JButton[model.getOrderedBeverage().getAdditives().size()];
            //additivePanel.setLayout(new GridLayout(additiveBtns.length, 1));
            for (int i = 0; i < additiveBtns.length; i++) {
                this.additiveBtns[i] = new JButton(model.getOrderedBeverage().getAdditives().get(i).getName());
                additiveBtns[i].setPreferredSize(beverageBtnDim);
                this.additivePanel.add(this.additiveBtns[i]);
                //Disable button if not in stock
                if (!model.isAvailable(model.getOrderedBeverage().getAdditives().get(i)))
                    additiveBtns[i].setEnabled(false);
            }

            ActionListener additivesListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < additiveBtns.length; i++) {
                        if (e.getSource() == additiveBtns[i]) {
                            //Add additive to ordered additive list
                            controller.setOrderedAdditives(model.getOrderedBeverage().getAdditives().get(i));
                            additiveBtns[i].setEnabled(false);
                            //additiveBtns[i].setBackground(Color.blue);
                        } else {
                            //If previously selected, still disabled
                            if (!additiveBtns[i].isEnabled()) additiveBtns[i].setEnabled(false);
                            else {
                                additiveBtns[i].setEnabled(true);
                            }
                        }
                    }
                }
            };
            for (int i = 0; i < additiveBtns.length; i++) {
                this.additiveBtns[i].addActionListener(additivesListener);
            }
        }
        additivePanel.setVisible(true);
    }//end createAdditiveView

    /**
     * Resets the interactable beverage selection panel.
     */
    public void resetBeverageOptionView() {
        beverageOptionPanel.remove(typePanel);
        beverageOptionPanel.remove(additivePanel);
        beverageOptionPanel.add(typePanel = new JPanel());
        beverageOptionPanel.add(additivePanel = new JPanel());

        //Reset beverage buttons
        for (int i = 0; i < beverageBtns.length; i++) {
            beverageBtns[i].setEnabled(true);
            beverageBtns[i].setBackground(null);
        }
    }

    /**
     * Resets the action panel.
     */
    public void resetActionView() {
        actionField.setText(null);
    }

    /**
     * Creates a panel displaying a description of the current order.
     */
    public void createOrderView() {
        orderPanel = new JPanel();
        orderDisplay = new JTextArea(model.printOrder(), 5, 15);
        orderDisplay.setEditable(false);
        orderDisplay.setLineWrap(true);
        orderDisplay.setWrapStyleWord(true);
        orderPanel.add(orderDisplay);
        orderPanel.setPreferredSize(new Dimension(200, 300));
        orderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.add(orderPanel, BorderLayout.EAST);
    }

    /**
     * Updates the panel displaying information of the current order.
     */
    public void updateOrderDisplay() {
        orderDisplay.setText(model.printOrder());
    }

    /**
     * Resets the entire view.
     */
    public void resetAll() {
        updateOrderDisplay();
        resetActionView();
        resetBeverageOptionView();
        enableDispenserBtn(false);
        if (model.getPayment().equals("Kč 0.00")) enableReturnChangeBtn(false);
        updatePaymentDisplay();
    }

}//end CoffeePotView