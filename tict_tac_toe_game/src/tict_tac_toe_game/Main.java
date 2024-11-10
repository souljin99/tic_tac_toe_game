package tict_tac_toe_game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    private static int shiftCounter = 0;
    private static Box[][] boxes = new Box[3][3];
    private static boolean gameFinished = false;

    public static void main(String[] args) {
        iniciarJuego();
    }
    public static void iniciarJuego() {
        shiftCounter = 0;
        gameFinished = false;

        JFrame window = new JFrame("Tic Tac Toe");
        window.setSize(900, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel principalPanel = new JPanel();
        principalPanel.setLayout(new GridLayout(3, 3));
        
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                Box box = new Box();
                box.setPreferredSize(new Dimension(300, 300));
                if ((row + column) % 2 == 0) {
                    box.setBackground(new Color(230, 230, 250));
                } else {
                    box.setBackground(new Color(176, 196, 222));
                }

                box.setBorder(new LineBorder(Color.BLACK, 3));

                final int currentRow = row;
                final int currentColumn = column;
                box.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!gameFinished) {
                            checkBox(box, currentRow, currentColumn);
                        }
                    }
                });

                boxes[row][column] = box;
                principalPanel.add(box);
            }
        }

        window.add(principalPanel);
        window.setVisible(true);
    }

    public static void checkBox(Box box, int row, int column) {
        if (box.getSymbol().isEmpty()) {
            String symbol = (shiftCounter % 2 == 0) ? "X" : "O";
            box.setSymbol(symbol);
            shiftCounter++;

            if (checkWinner(symbol, row, column)) {
                gameFinished = true;
                showEndMessage("Winner: " + symbol);
            } else if (shiftCounter == 9) {
                gameFinished = true;
                showEndMessage("Draw");
            }
        }
    }
    
    public static boolean checkWinner(String symbol, int row, int column) {
    	
        return (boxes[row][0].getSymbol().equals(symbol) && boxes[row][1].getSymbol().equals(symbol) && boxes[row][2].getSymbol().equals(symbol)) ||
               (boxes[0][column].getSymbol().equals(symbol) && boxes[1][column].getSymbol().equals(symbol) && boxes[2][column].getSymbol().equals(symbol)) ||
               (boxes[0][0].getSymbol().equals(symbol) && boxes[1][1].getSymbol().equals(symbol) && boxes[2][2].getSymbol().equals(symbol)) ||
               (boxes[0][2].getSymbol().equals(symbol) && boxes[1][1].getSymbol().equals(symbol) && boxes[2][0].getSymbol().equals(symbol));
    }
    
    public static void showEndMessage(String message) {
        int response = JOptionPane.showConfirmDialog(null, message + "\nDo you want to restart the game?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        }
    }

    public static void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                boxes[row][column].setSymbol("");
            }
        }
        shiftCounter = 0;
        gameFinished = false;
    }
}
