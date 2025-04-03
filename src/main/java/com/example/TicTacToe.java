package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe {
    private static final int SIZE = 5;
    private Button[][] board;
    private boolean xTurn = true;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe");

        GridPane grid = new GridPane();
        board = new Button[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Button button = new Button("");
                button.setMinSize(100, 100);
                button.setOnAction(e -> handleButtonClick(button));
                board[i][j] = button;
                grid.add(button, j, i);
            }
        }

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(xTurn ? "X" : "O");
            if (checkWin()) {
                showAlert((xTurn ? "X" : "O") + " wins!");
                resetBoard();
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
                resetBoard();
            }
            xTurn = !xTurn;
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkDirection(i, j, 1, 0) || checkDirection(i, j, 0, 1) || checkDirection(i, j, 1, 1) || checkDirection(i, j, 1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int row, int col, int rowDir, int colDir) {
        String symbol = board[row][col].getText();
        if (symbol.isEmpty()) return false;

        for (int k = 1; k < 5; k++) {
            int newRow = row + k * rowDir;
            int newCol = col + k * colDir;

            if (newRow < 0 || newRow >= SIZE || newCol < 0 || newCol >= SIZE || !board[newRow][newCol].getText().equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j].setText("");
            }
        }
        xTurn = true;
    }
}