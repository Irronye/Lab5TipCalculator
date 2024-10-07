package com.example.tipcalculator_jason_yi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
    // formatters for currency and percentages
    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    // calculates and displays the tip and total amounts


    public void calculate(){
        String amountText = amountTextField.getText();
        if (amountText == null || amountText.isEmpty()) {
            tipTextField.setText("");
            totalTextField.setText("");
            return;
        }
        try {

            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }
        catch (NumberFormatException ex) {
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }
    // called by FXMLLoader to initialize the controller
    public void initialize() {
        // 0-4 rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);


        StringBinding tipPercentageBinding = Bindings.createStringBinding(() ->
                        percent.format(tipPercentageSlider.getValue() / 100.0),
                tipPercentageSlider.valueProperty()
        );

        tipPercentageLabel.textProperty().bind(tipPercentageBinding);


        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            tipPercentage = BigDecimal.valueOf(newValue.doubleValue() / 100.0);
            calculate();
        });
        amountTextField.textProperty().addListener((observable) -> {
            calculate();
            }
        );
    }
}
