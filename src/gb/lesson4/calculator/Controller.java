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
        String textAreaText = textArea.getText();
        if (!textAreaText.equals("0")) {
            textArea.appendText("" + number);
        } else {
            textArea.setText("" + number);
        }
        if (!isCommand(textAreaText)) {
            int valueLength = String.valueOf(result).length() - 1;
            String textAreaTextSub = textAreaText.substring(valueLength);
            boolean startsWithZeros = textAreaTextSub.startsWith("0");
            if (startsWithZeros && textAreaTextSub.length() > 1 && number == 0) {
                textArea.setText(textAreaText.substring(0, textAreaText.length() - 1));
            } else if (startsWithZeros && number != 0) {
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
        if (!isCommand(textAreaText) && isCommandAtEnd(textAreaText)) {
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
        if (isCommand(textAreaText)) {
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
        String textAreaText = textArea.getText();
        if (textAreaText.indexOf('.') == -1) {
            if (isCommandAtEnd(textAreaText)) {
                textArea.appendText(".");
            } else {
                textArea.appendText("0.");
            }
        } else {
            if (!isCommand(textAreaText)) {
                String subStr = "" + result;
                String str = textAreaText.substring(subStr.length(), textAreaText.length());
                if (str.length() == 1) {
                    textArea.appendText("0");
                }
                if (str.indexOf('.') == -1) {
                    textArea.appendText(".");
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
        if (!isCommand(textAreaText) && isCommandAtEnd(textAreaText)) {
            summarize(textAreaText);
            output(textArea, "");
        }
        textArea.requestFocus();
    }

    @FXML
    private void onButtonPlusMinus(ActionEvent actionEvent) {
        String textAreaText = textArea.getText();
        if (isCommand(textAreaText)) {
            result = (-1) * Double.parseDouble(textAreaText);
            output(textArea, "");
        }
        textArea.requestFocus();
    }

    private boolean isCommand(String string) {
        return !string.contains("+") && string.indexOf('-', 1) == -1 && !string.contains("*") && !string.contains("/");
    }

    private boolean isCommandAtEnd(String string) {
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
        KeyCodeCombination SHIFT_PERCENT = new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.SHIFT_DOWN);
        KeyCodeCombination SHIFT_PLUS = new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN);
        KeyCodeCombination SHIFT_MULTIPLY = new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN);
        textArea.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {
                    case ESCAPE:
                        cleanAll();
                        break;
                    case BACK_SPACE:
                        deleteOneSymbol();
                        break;
                    case ENTER:
                        sumNumbers();
                        break;
                    default:
                        if (SHIFT_PERCENT.match(event)) {
                            checkPercentage();
                        } else if (SHIFT_PLUS.match(event)) {
                            printCommand("+");
                        } else if (SHIFT_MULTIPLY.match(event)) {
                            printCommand("*");
                        } else if (event.getText().length() > 0) {
                            switch (event.getText()) {
                                case "1":
                                case "2":
                                case "3":
                                case "4":
                                case "5":
                                case "6":
                                case "7":
                                case "8":
                                case "9":
                                case "0":
                                    printNumber(Integer.parseInt(event.getText()));
                                    break;
                                case "+":
                                case "-":
                                case "*":
                                case "/":
                                    printCommand(event.getText());
                                    break;
                                case "=":
                                    sumNumbers();
                                    break;
                                case ".":
                                    printMark();
                                    break;
                            }
                        }
                }
            }
        });
    }
}
