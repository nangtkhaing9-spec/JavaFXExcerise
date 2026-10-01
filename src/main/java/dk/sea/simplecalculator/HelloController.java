package dk.sea.simplecalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class HelloController {

    @FXML
    private TextField txtResult;


    @FXML
    public void onBtnNumberOrOperatorClicked(ActionEvent event) {
        Button btn = (Button) event.getSource();
        txtResult.setText(txtResult.getText() + btn.getText());
    }


    @FXML
    public void onBtnResultClicked(ActionEvent actionEvent) {
        String expr = txtResult.getText().replace(",", "."); // Change ',' to '.' for math
        double answer = 0;
        boolean calculated = false;


        if (expr.contains("±")) {
            answer = Double.parseDouble(expr.replace("±", "")) * -1;
            calculated = true;
        }
        else if (expr.contains("%")) {
            answer = Double.parseDouble(expr.replace("%", "")) / 100.0;
            calculated = true;
        }

        else if (expr.contains("+")) {
            String[] parts = expr.split("\\+");
            answer = Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
            calculated = true;
        }

       
        else if (expr.contains("-")) {
            
            int minusPos = expr.indexOf("-", 1);


            if (minusPos != -1) {
                String firstNum = expr.substring(0, minusPos);  // "-4"
                String secondNum = expr.substring(minusPos + 1); // "6"


                answer = Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
                calculated = true;
            }
        }

        else if (expr.contains("/")) {
            String[] parts = expr.split("/");
            answer = Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
            calculated = true;
        }


        if (calculated) {
            if (answer % 1 == 0) {
                txtResult.setText(String.valueOf((long) answer));
            } else {
                txtResult.setText(String.valueOf(answer).replace(".", ","));
            }
        }
    }


    @FXML
    public void onBtnClearClicked(ActionEvent actionEvent) {
        txtResult.clear();
    }



}
