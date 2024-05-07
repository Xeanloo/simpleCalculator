package com.example.simpleCalculator.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Main")
@Route(value = "")
@RouteAlias(value = "")
public class MainView extends VerticalLayout {
    private class numberButton extends Button {
        public numberButton(String text) {
            super(text);
            addClickListener(e -> {
                outputField.setValue(Double.parseDouble(text));
            });
            //add shortcut key depending on the text of the button
            switch(text) {
                case "0":
                    addClickShortcut(Key.DIGIT_0);
                    break;
                case "1":
                    addClickShortcut(Key.DIGIT_1);
                    break;
                case "2":
                    addClickShortcut(Key.DIGIT_2);
                    break;
                case "3":
                    addClickShortcut(Key.DIGIT_3);
                    break;
                case "4":
                    addClickShortcut(Key.DIGIT_4);
                    break;
                case "5":
                    addClickShortcut(Key.DIGIT_5);
                    break;
                case "6":
                    addClickShortcut(Key.DIGIT_6);
                    break;
                case "7":
                    addClickShortcut(Key.DIGIT_7);
                    break;
                case "8":
                    addClickShortcut(Key.DIGIT_8);
                    break;
                case "9":
                    addClickShortcut(Key.DIGIT_9);
                    break;
            }
        }
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
    private NumberField outputField;

    private void initButtons() {
        button0 = new numberButton("0");
        button1 = new numberButton("1");
        button2 = new numberButton("2");
        button3 = new numberButton("3");
        button4 = new numberButton("4");
        button5 = new numberButton("5");
        button6 = new numberButton("6");
        button7 = new numberButton("7");
        button8 = new numberButton("8");
        button9 = new numberButton("9");
    }

    public MainView() {
        initButtons();
        outputField = new NumberField();
        outputField.setReadOnly(true);
        outputField.getStyle().set("--vaadin-input-field-readonly-border", "2px solid black");

        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, button1);

        add(new H1("Simple Calculator"), outputField, button1, button2, button3, button4, button5, button6, button7, button8, button9, button0);
    }

}
