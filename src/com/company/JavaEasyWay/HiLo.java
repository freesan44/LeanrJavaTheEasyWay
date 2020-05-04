package com.company.JavaEasyWay;

import java.util.Scanner;

public class HiLo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int theNumber = (int)(Math.random()*200+1-100);
        System.out.println("答案:"+theNumber);
        int gress = 0;
        String playAgain;
        do {
            System.out.println("输入猜测的数");
            gress = scan.nextInt();
            if (gress> theNumber)
            {
                System.out.println("高了");
            }
            else if (gress<theNumber)
            {
                System.out.println("低了");
            }
            else {
                System.out.println("猜对了！");
            }
            System.out.println("继续玩吗(y/n)?");
            playAgain = scan.next();
        }while (playAgain.equalsIgnoreCase("y"));
        //关闭资源
        scan.close();
        System.out.println("谢谢play");



    }
}
