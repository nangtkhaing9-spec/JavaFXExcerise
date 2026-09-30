package dk.sea.simplecalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class HelloController {

    @FXML
    private TextField txtResult;

    // 1. Appends whatever button you click to the text field
    @FXML
    public void onBtnNumberOrOperatorClicked(ActionEvent event) {
        Button btn = (Button) event.getSource();
        txtResult.setText(txtResult.getText() + btn.getText());
    }

    // 2. Calculates the answer when '=' is clicked
    @FXML
    public void onBtnResultClicked(ActionEvent actionEvent) {
        String expr = txtResult.getText().replace(",", "."); // Change ',' to '.' for math
        double answer = 0;
        boolean calculated = false;

        // Handle ± (Toggle positive/negative)
        if (expr.contains("±")) {
            answer = Double.parseDouble(expr.replace("±", "")) * -1;
            calculated = true;
        }
        else if (expr.contains("%")) {
            answer = Double.parseDouble(expr.replace("%", "")) / 100.0;
            calculated = true;
        }
        // Handle Addition (+)
        else if (expr.contains("+")) {
            String[] parts = expr.split("\\+");
            answer = Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
            calculated = true;
        }
        // Handle Subtraction (-)
        else if (expr.contains("-") && !expr.startsWith("-")) {
            String[] parts = expr.split("-");
            answer = Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
            calculated = true;
        }
        // Handle Multiplication (X)
        else if (expr.contains("x")) {
            String[] parts = expr.split("x");
            answer = Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
            calculated = true;
        }
        // Handle Division (/)
        else if (expr.contains("/")) {
            String[] parts = expr.split("/");
            answer = Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
            calculated = true;
        }

        // Show full number if there is no decimal (e.g. 3 instead of 3.0)
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
