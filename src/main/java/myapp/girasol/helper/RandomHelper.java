/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package myapp.girasol.helper;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Sara

public class RandomHelper {
    
    protected static SecureRandom random = new SecureRandom();

    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
}

*/