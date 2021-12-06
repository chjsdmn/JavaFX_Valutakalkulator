package com.example.fxvalutakalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

class Valuta {
    public String sort;
    private double kurs;
    public String[] valutasort = {"USD", "CNY", "EUR", "JPY", "NOK"};
    public boolean har;  //true er har det valuta

    public Valuta(String sort){
        this.sort = sort;
    }

    public boolean isHar(String sort) {
        for(int i = 0; i < valutasort.length; i++) {
            if (valutasort[i].equals(sort)) {
                return har = true;
            }
        }
        return har = false;
    }

    public double getKurs(String sort){
        for(int i = 0; i < valutasort.length; i++){
            if(isHar(sort)){
                switch (sort) {
                    case "USD":
                        kurs = 0.15;
                        break;
                    case "CNY":
                        kurs = 0.7;
                        break;
                    case "EUR":
                        kurs = 0.1;
                        break;
                    case "JPY":
                        kurs = 39.5;
                        break;
                    case "NOK":
                        kurs = 1;
                        break;
                    default:
                        kurs = 0;
                        break;
                }
                return kurs;
            }
        }
        return 0.0;
    }
}

class Kalkulator{

    public String beregn(Valuta valuta, String beløp){
        double amount;
        try{
            amount = Double.parseDouble(beløp);
            String.format("%.2f", amount);
        }
        catch (Exception e){
            return "Det er ikke et tall.";
        }
        return Double.toString(valuta.getKurs(valuta.sort) * amount);
    }
}

public class HelloController {
    Kalkulator kalkulator = new Kalkulator();

    @FXML
    private ToggleGroup cur;

    @FXML
    private Label lalRes;

    @FXML
    private TextField txtBeløp;

    @FXML
    void btnBeregn(ActionEvent event) {
        RadioButton current = (RadioButton) cur.getSelectedToggle();
        Valuta valuta = new Valuta(current.getText());
        String ut = "";
        if(valuta.isHar(current.getText())) {
            ut = txtBeløp.getText() + " NOK er " +
                    kalkulator.beregn(valuta, txtBeløp.getText())+ " " +
                    current.getText();
        }
        else {
            ut = current.getText() + " finnes ikke.";
        }
        lalRes.setText(ut);
    }

}
