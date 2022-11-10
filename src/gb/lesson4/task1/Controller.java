package gb.lesson4.task1;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField userText;

    @FXML
    private void onSendButtonClick() {
        setTextArea();
    }

    @FXML
    private void onSendEnter() {
        userText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() ==  KeyCode.ENTER){
                   setTextArea();
                }
            }
        });
    }

    private void setTextArea(){
        textArea.appendText(userText.getText() + "\n");
        userText.setText("");
        userText.requestFocus();
    }
}
