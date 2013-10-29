package ru.footmade.libnot;

public abstract class AbstractNegationStrategy {
    /**
     * Does logical negation
     * @param var a value which should be negated. Must be either true or false.
     * @return true, if an argument is false; false, if an argument is true.
     * @see #notAsync(boolean, ru.footmade.libnot.AbstractNegationStrategy.AsyncCallback)
     */
    public abstract boolean not(boolean var);
    
    /**
     * Does asynchronous logical negation
     * @param var a value which should be negated. Must be either true or false.
     * @param callback true, if an argument is false; false, if an argument is true.
     * @see #not(boolean)
     */
    public void notAsync(final boolean var, final AsyncCallback callback) {
        (new Thread() {
            @Override
            public void run() {
                boolean result = not(var);
                if (callback != null)
                    callback.processResult(result);
            }
        }).start();
    }
    
    /**
     * Callback interface to process the result of an asynchronous negation
     */
    public static interface AsyncCallback {
        public void processResult(boolean result);
    }
}
