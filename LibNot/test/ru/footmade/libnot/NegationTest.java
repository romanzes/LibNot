package ru.footmade.libnot;

import java.util.Random;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class NegationTest {
    private static Random rand;

    @BeforeClass
    public static void testSetup() {
        rand = new Random();
    }

    @AfterClass
    public static void testCleanup() {
        rand = null;
    }
    
    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            testIteration();
        }
    }
    
    public void testIteration() {
        switch (rand.nextInt(2)) {
            case 0:
                assertEquals("Not true must be false", false, NegationUtils.not(true));
                break;
            case 1:
            default:
                assertEquals("Not false must be true", true, NegationUtils.not(false));
                break;
        }
    }
}