package gb.lesson4.calculator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class Controller {

    private static double result = 0;

    @FXML
    private TextArea textArea;

    @FXML
    private void onButtonNumber(ActionEvent actionEvent) {
        int numberOnButton = Integer.parseInt(((Button) actionEvent.getSource()).getText());
        printNumber(numberOnButton);
    }

    private void printNumber(int number) {
        if (!textArea.getText().equals("0")) {
            textArea.appendText("" + number);
        } else {
            textArea.setText("" + number);
        }
        if (!checkCommand(textArea.getText())) {
            String textAreaText = textArea.getText().substring(0, String.valueOf(result).length() - 1);
            String subStr = textArea.getText().substring(String.valueOf(result).length() - 1);
            if (subStr.startsWith("0") && subStr.length() > 1 && number == 0) {
                textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
            } else if (subStr.startsWith("0") && number != 0) {
                textArea.setText(textAreaText + number);
            }
        }
        textArea.requestFocus();
    }

    @FXML
    private void onButtonPercent(ActionEvent actionEvent) {
        checkPercentage();
    }

    private void checkPercentage() {
        String textAreaText = textArea.getText();
        double subNumber = 0;
        if (!checkCommand(textAreaText) && checkCommandAtEnd(textAreaText)) {
            if (textAreaText.contains("+")) {
                subNumber = result * parsingString(textAreaText, '+') / 100;
            } else if (textAreaText.indexOf('-', 1) != -1) {
                subNumber = result * parsingString(textAreaText, '-') / 100;
            } else if (textAreaText.contains("*")) {
                subNumber = parsingString(textAreaText, '*') / 100;
            } else if (textAreaText.contains("/")) {
                subNumber = parsingString(textAreaText, '/') / 100;
            }
        }
        if (subNumber > 0) {
            output(textArea, textAreaText.substring(String.valueOf(result).length() - 2, String.valueOf(result).length() - 1) + rounded(subNumber));

        } else if (subNumber < 0) {
            output(textArea, "" + rounded(subNumber));
        }
        textArea.requestFocus();
    }

    @FXML
    private void onButtonCE(ActionEvent actionEvent) {
        cleanAll();
    }

    private void cleanAll() {
        textArea.setText("0");
        result = 0;
        textArea.requestFocus();
    }

    @FXML
    private void onButtonBack(ActionEvent actionEvent) {
        deleteOneSymbol();
    }

    private void deleteOneSymbol() {
        String textAreaText = textArea.getText();
        if (textAreaText.length() == 1 || (textAreaText.startsWith("-") && textAreaText.length() == 2)) {
            textArea.setText("0");
        } else {
            textArea.setText(textAreaText.substring(0, textAreaText.length() - 1));
        }
        textArea.requestFocus();
    }

    @FXML
    private void onButtonCommand(ActionEvent actionEvent) {
        String commandButton = ((Button) actionEvent.getSource()).getText();
        printCommand(commandButton);
    }

    private void printCommand(String command) {
        String textAreaText = textArea.getText();
        if (textAreaText.endsWith("+") || textAreaText.endsWith("-") || textAreaText.endsWith("*") || textAreaText.endsWith("/")) {
            textArea.setText(textAreaText.substring(0, textAreaText.length() - 1));
            textAreaText = textArea.getText();
        }
        if (checkCommand(textAreaText)) {
            result = Double.parseDouble(textAreaText);
            textArea.appendText(command);
        } else {
            if (!textAreaText.endsWith(".")) {
                summarize(textAreaText);
                output(textArea, command);
            }
        }
        textArea.requestFocus();
    }

    @FXML
    private void onButtonMark(ActionEvent actionEvent) {
        printMark();
    }

    private void printMark() {
        if (textArea.getText().indexOf('.') == -1) {
            textArea.appendText("" + '.');
        } else {
            if (!checkCommand(textArea.getText())) {
                String subStr = "" + result;
                String str = textArea.getText().substring(subStr.length(), textArea.getText().length());
                if (str.length() == 1) {
                    textArea.appendText("0");
                }
                if (str.indexOf('.') == -1) {
                    textArea.appendText("" + '.');
                }
            }
        }
        textArea.requestFocus();
    }

    @FXML
    private void onButtonSum(ActionEvent actionEvent) {
        sumNumbers();
    }

    private void sumNumbers() {
        String textAreaText = textArea.getText();
        if (!checkCommand(textAreaText) && checkCommandAtEnd(textAreaText)) {
            summarize(textAreaText);
            output(textArea, "");
        }
        textArea.requestFocus();
    }

    @FXML
    private void onButtonPlusMinus(ActionEvent actionEvent) {
        if (checkCommand(textArea.getText())) {
            result = (-1) * Double.parseDouble(textArea.getText());
            output(textArea, "");
        }
        textArea.requestFocus();
    }

    private boolean checkCommand(String string) {
        return !string.contains("+") && string.indexOf('-', 1) == -1 && !string.contains("*") && !string.contains("/");
    }

    private boolean checkCommandAtEnd(String string) {
        return !string.endsWith("+") && !string.endsWith("-") && !string.endsWith("*") && !string.endsWith("/") && !string.endsWith(".");
    }

    private void summarize(String string) {
        if (string.contains("+")) {
            result += parsingString(string, '+');
        } else if (string.indexOf('-', 1) != -1) {
            result -= parsingString(string, '-');
        } else if (string.contains("*")) {
            result *= parsingString(string, '*');
        } else if (string.contains("/")) {
            result /= parsingString(string, '/');
        }
    }

    private Double parsingString(String string, char command) {
        if (command == '+' || command == '*' || command == '/') {
            return Double.parseDouble(string.substring(string.indexOf(command) + 1, string.length()));
        } else {
            return Double.parseDouble(string.substring(string.indexOf(command, 1) + 1, string.length()));
        }
    }

    private void output(TextArea textArea, String str) {
        textArea.setText("" + rounded(result) + str);
    }

    private String rounded(double d) {
        return (result - (int) result == 0) ? "" + (int) d : "" + d;
    }

    @FXML
    private void onTypedOnKeyboard() {
        String numbers = "1234567890";
        String commands = "+-/*";
        KeyCodeCombination SHIFT_PERCENT = new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.SHIFT_DOWN);
        KeyCodeCombination SHIFT_PLUS = new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN);
        textArea.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    cleanAll();
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    deleteOneSymbol();
                } else if (SHIFT_PERCENT.match(event)) {
                    checkPercentage();
                } else if (numbers.contains(event.getText()) && event.getText().length() > 0) {
                    printNumber(Integer.parseInt(event.getText()));
                } else if (SHIFT_PLUS.match(event)) {
                    printCommand("+");
                } else if (commands.contains(event.getText()) && event.getText().length() > 0) {
                    printCommand(event.getText());
                } else if (event.getText().equals("=") || event.getCode() == KeyCode.ENTER) {
                    sumNumbers();
                } else if (event.getText().equals(".")) {
                    printMark();
                }
            }
        });
    }
}
