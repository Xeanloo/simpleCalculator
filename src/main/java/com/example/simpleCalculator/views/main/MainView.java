package com.example.simpleCalculator.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Main")
@Route(value = "")
@RouteAlias(value = "")
public class MainView extends VerticalLayout {
    private class NumberButton extends Button {
        public NumberButton(String text) {
            super(text);
            addClickListener(e -> {
//                outputField.setValue(Double.parseDouble(text));
                if(outputField.getValue() == null || outputField.getValue() == 0.0 || isOperationClicked) {
                    outputField.setValue(Double.parseDouble(text));
                    isOperationClicked = false;
                    isNewNumberGiven = !isOperationClicked;
                } else {
                    outputField.setValue(outputField.getValue() * 10 + Double.parseDouble(text));
                }
            });
            addClickShortcut(getKey(text));
        }

        private Key getKey(String text) {
            return switch (text) {
                case "1" -> Key.DIGIT_1;
                case "2" -> Key.DIGIT_2;
                case "3" -> Key.DIGIT_3;
                case "4" -> Key.DIGIT_4;
                case "5" -> Key.DIGIT_5;
                case "6" -> Key.DIGIT_6;
                case "7" -> Key.DIGIT_7;
                case "8" -> Key.DIGIT_8;
                case "9" -> Key.DIGIT_9;
                case "0" -> Key.DIGIT_0;
                default -> null;
            };
        }
    }

    private class clearButton extends Button {
        public clearButton(String text) {
            super(text);
            addClickListener(e -> {
                outputField.setValue(0.0);
                storedNumber = null;
                secondNumber = null;
                currentOperation = null;
                isOperationClicked = false;
            });
            addClickShortcut(Key.ESCAPE);
        }
    }

    private class equalsButton extends Button {
        public equalsButton(String text) {
            super(text);
            addClickListener(e -> {
                if (storedNumber != null && secondNumber != null) {
                    storedNumber = performOperation();
                    outputField.setValue(storedNumber);
//                    secondNumber = null;
//                    currentOperation = null;
                } else if (secondNumber == null && outputField.getValue() != 0.0) {
                    secondNumber = outputField.getValue();
                    storedNumber = performOperation();
                    outputField.setValue(storedNumber);
                }
            });
            addClickShortcut(Key.ENTER);
        }
    }

    private class operationButton extends Button{
        public operationButton(String text) {
            super(text);
            addClickListener(e -> {
                isOperationClicked = true;
                if (storedNumber == null) {
                    storedNumber = outputField.getValue();
                    currentOperation = getKey(text);
                } else if (isNewNumberGiven) {
                    secondNumber = outputField.getValue();
                    storedNumber = performOperation();
                    currentOperation = getKey(text);
                    outputField.setValue(storedNumber);
                    isNewNumberGiven = false;
                } else {
                    currentOperation = getKey(text);
                }
            });
        }

        private Key getKey(String text) {
            return switch (text) {
                case "+" -> Key.ADD;
                case "-" -> Key.SUBTRACT;
                case "*" -> Key.MULTIPLY;
                case "/" -> Key.DIVIDE;
                case "=" -> Key.ENTER;
                default -> null;
            };
        }
    }

    private Double performOperation() {
        return switch (currentOperation.toString()) {
            case "Add" -> storedNumber + secondNumber;
            case "Subtract" -> storedNumber - secondNumber;
            case "Multiply" -> storedNumber * secondNumber;
            case "Divide" -> storedNumber / secondNumber;
            default -> null;
        };
    }

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonAdd;
    private Button buttonSubtract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonEquals;
    private Button buttonClear;
    private NumberField outputField;

    private Key currentOperation;
    private Double storedNumber = null;
    private Double secondNumber = null;
    private boolean isNewNumberGiven = false;
    private boolean isOperationClicked = false;

    private void initButtons() {
        button0 = new NumberButton("0");
        button1 = new NumberButton("1");
        button2 = new NumberButton("2");
        button3 = new NumberButton("3");
        button4 = new NumberButton("4");
        button5 = new NumberButton("5");
        button6 = new NumberButton("6");
        button7 = new NumberButton("7");
        button8 = new NumberButton("8");
        button9 = new NumberButton("9");
        buttonAdd = new operationButton("+");
        buttonSubtract = new operationButton("-");
        buttonMultiply = new operationButton("*");
        buttonDivide = new operationButton("/");
        buttonEquals = new equalsButton("=");
        buttonClear = new clearButton("C");
    }

    public MainView() {
        initButtons();
        outputField = new NumberField();
        outputField.setValue(0.0);
        outputField.setPlaceholder("0");
        outputField.setReadOnly(true);
        outputField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        outputField.getStyle().set("--vaadin-input-field-readonly-border", "2px solid black");

        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, button1);
        HorizontalLayout row1 = new HorizontalLayout(button7, button8, button9, buttonDivide);
        HorizontalLayout row2 = new HorizontalLayout(button4, button5, button6, buttonMultiply);
        HorizontalLayout row3 = new HorizontalLayout(button1, button2, button3, buttonSubtract);
        HorizontalLayout row4 = new HorizontalLayout(button0, buttonClear, buttonEquals, buttonAdd);
        add(outputField, row1, row2, row3, row4);
//        add(new H1("Simple Calculator"), outputField, button1, button2, button3, button4, button5,
//                button6, button7, button8, button9, button0, buttonAdd, buttonSubtract,
//                buttonMultiply, buttonDivide, buttonEquals, buttonClear);
    }

}
