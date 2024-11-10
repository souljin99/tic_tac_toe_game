package tict_tac_toe_game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

class Box extends JPanel {
    private String symbol = "";

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(8));

        if ("X".equals(symbol)) {
            g2d.setColor(Color.RED);
            g2d.drawLine(30, 30, getWidth() - 30, getHeight() - 30);
            g2d.drawLine(getWidth() - 30, 30, 30, getHeight() - 30);
        } else if ("O".equals(symbol)) {
            g2d.setColor(Color.BLUE);
            g2d.drawOval(30, 30, getWidth() - 60, getHeight() - 60);
        }
    }
}