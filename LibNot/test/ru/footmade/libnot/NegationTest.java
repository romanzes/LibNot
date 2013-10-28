package ru.footmade.libnot;

import java.util.Random;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class NegationTest {
    private static Random rand;
    private static AbstractNegationStrategy defaultStrategy, cachedStrategy;

    @BeforeClass
    public static void testSetup() {
        rand = new Random();
        defaultStrategy = new DefaultNegationStrategy();
        cachedStrategy = new CachedNegationStrategy();
    }

    @AfterClass
    public static void testCleanup() {
        rand = null;
        defaultStrategy = null;
        cachedStrategy = null;
    }
    
    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            testIteration();
        }
    }
    
    public void testIteration() {
        AbstractNegationStrategy strategy;
        
        switch (rand.nextInt(2)) {
            case 0:
                strategy = defaultStrategy;
                break;
            case 1:
            default:
                strategy = cachedStrategy;
                break;
        }
        
        switch (rand.nextInt(2)) {
            case 0:
                assertEquals("Not true must be false", false, strategy.not(true));
                break;
            case 1:
            default:
                assertEquals("Not false must be true", true, strategy.not(false));
                break;
        }
    }
}