package be.ehb.herhaling.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DiceLogic {

    public int rollD6(){
        Random generator = new Random();
        return generator.nextInt(6)+1;
    }

    public int[] rollYahtzee(){
        int[] roll = new int[5];
        for (int i = 0; i < roll.length; i++) {
            roll[i] = rollD6();
        }
        return roll;
    }
}
