import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private String player = "X"; //marker
    private Button[][] buttons = new Button[3][3];

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        // Create the buttons for the game board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setFont(Font.font("Arial", FontWeight.BOLD, 60));
                button.setOnAction(e -> {
                    if (button.getText().isEmpty()) {
                        button.setText(player);
                        if (checkWin()) {
                            showEndMessage(player + " wins!");
                        } else if (checkDraw()) {
                            showEndMessage("Draw!");
                        } else {
                            player = player.equals("X") ? "O" : "X";
                        }
                    }
                });
                buttons[i][j] = button;
                pane.add(button, j, i);
            }
        }

        Scene scene = new Scene(pane, 800, 500);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Check if the current player has won
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // -
            // -
            // -
            if (buttons[i][0].getText().equals(player) && buttons[i][1].getText().equals(player) && buttons[i][2].getText().equals(player)) {
                return true;
            }
            // | | |
            if (buttons[0][i].getText().equals(player) && buttons[1][i].getText().equals(player) && buttons[2][i].getText().equals(player)) {
                return true;
            }
        } // X left to right
        if (buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][2].getText().equals(player)) {
            return true;
        }
        // X right to left
        if (buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][0].getText().equals(player)) {
            return true;
        }
        return false;
    }

    // Check if the game is a draw
    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Display a message when the game ends
    private void showEndMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setOnHidden(e -> {
            System.exit(0);
        });
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

