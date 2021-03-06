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
        NegationStrategyFactory factory = new NegationStrategyFactory();
        defaultStrategy = factory.createNegationStrategy();
        cachedStrategy = factory.createNegationStrategy(NegationStrategyFactory.FLAG_CACHED);
    }

    @AfterClass
    public static void testCleanup() {
        rand = null;
        defaultStrategy = null;
        cachedStrategy = null;
    }
    
    @Test
    public void strictTest() {
        assertTrue(defaultStrategy instanceof DefaultNegationStrategy);
        assertTrue(cachedStrategy instanceof CachedNegationStrategy);
        testCase(defaultStrategy, true, false, true);
        testCase(defaultStrategy, true, false, false);
        testCase(defaultStrategy, false, true, true);
        testCase(defaultStrategy, false, true, false);
        testCase(cachedStrategy, true, false, true);
        testCase(cachedStrategy, true, false, false);
        testCase(cachedStrategy, false, true, true);
        ((CachedNegationStrategy) cachedStrategy).setCache(null);
        testCase(cachedStrategy, false, true, false);
    }
    
    /**
     * In case we have overlooked something
     */
    @Test
    public void randomTest() {
        for (int i = 0; i < 1000; i++) {
            testIteration();
        }
    }
    
    /**
     * Two strategies are run in parallel and share the same cache
     */
    @Test
    public void parallelTest() {
        final CachedNegationStrategy strategy1 = new CachedNegationStrategy();
        final CachedNegationStrategy strategy2 = new CachedNegationStrategy(strategy1.getCache());
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    assertEquals("Not true must be false", false, strategy1.not(true));
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    assertEquals("Not false must be true", true, strategy2.not(false));
                }
            }
        };
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {}
    }
    
    private class FlagHolder {
        private boolean _flag;
        
        public void mark() {
            _flag = true;
        }
        
        public boolean marked() {
            return _flag;
        }
    }
    
    private void testCase(AbstractNegationStrategy strategy, boolean value,
            final boolean expect, boolean async) {
        final String failMessage = String.format("Not %s must be %s", value, expect);
        if (async) {
            final FlagHolder asyncFlag = new FlagHolder();
            strategy.notAsync(value, new AbstractNegationStrategy.AsyncCallback() {
                @Override
                public void processResult(boolean result) {
                    assertEquals(failMessage, expect, result);
                    asyncFlag.mark();
                }
            });
            while (!asyncFlag.marked()) {
                try { Thread.sleep(0); } catch (InterruptedException ex) {}
            }
        } else {
            assertEquals(failMessage, expect, strategy.not(value));
        }
    }
    
    public void testIteration() {
        AbstractNegationStrategy strategy;
        boolean value;
        boolean expect;
        boolean async;
        
        switch (rand.nextInt(2)) {
            case 0:
                strategy = defaultStrategy;
                value = true;
                expect = false;
                break;
            case 1:
            default:
                strategy = cachedStrategy;
                value = false;
                expect = true;
                break;
        }
        
        switch (rand.nextInt(2)) {
            case 0:
                async = false;
                break;
            case 1:
            default:
                async = true;
                break;
        }
        
        testCase(strategy, value, expect, async);
    }
}