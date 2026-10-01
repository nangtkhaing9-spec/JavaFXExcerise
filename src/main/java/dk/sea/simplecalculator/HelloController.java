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
    private void onBtnNumberOrOperatorClicked(ActionEvent event) {
        Button btn = (Button) event.getSource();
        txtResult.setText(txtResult.getText() + btn.getText());
    }


    @FXML
    private void onBtnResultClicked(ActionEvent actionEvent) {
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

            String formatted = expr.replace("-", "+-");


            if (formatted.startsWith("+")) {
                formatted = formatted.substring(1);
            }


            String[] parts = formatted.split("\\+");


            answer = 0;
            for (String part : parts) {
                if (!part.isEmpty()) {
                    answer += Double.parseDouble(part); // -2 + (-4) + (-6) + (-10)
                }
            }
            calculated = true;
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
    private void onBtnClearClicked(ActionEvent actionEvent) {
        txtResult.clear();
    }



}
