package com.company.JavaEasyWay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class GuessingGame {
    private JTextField textGuess;
    private JButton guessBtn;
    private JLabel resultLab;
    private JPanel guessingGamePanel;
    private int theNumber = 0;

    public GuessingGame() {
        theNumber = (int)(Math.random() * 100+1);
        System.out.printf("目标是："+theNumber);
        //按钮事件
        guessBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
        //textField事件
        textGuess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    public void checkGuess() {
        String tfText = textGuess.getText();
        try {
            Integer guessNum = Integer.parseInt(textGuess.getText());
            if (guessNum > theNumber){
                resultLab.setText("高了");
            }
            else if (guessNum < theNumber)
            {
                resultLab.setText("低了");
            }
            else
            {
                resultLab.setText("中了");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resultLab.setText("输入错误的数值！");
        } finally {
            //鼠标重新聚焦在输入框上
            textGuess.requestFocus();
            textGuess.selectAll();
        }


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("GuessingGame");
        frame.setContentPane(new GuessingGame().guessingGamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(450,300));



    }
}
