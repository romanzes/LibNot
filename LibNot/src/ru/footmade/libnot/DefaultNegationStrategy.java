package ru.footmade.libnot;

public class DefaultNegationStrategy extends AbstractNegationStrategy {

    @Override
    public boolean not(boolean var) {
        final Boolean varObj = Boolean.valueOf(var);
        Boolean result;
        if (varObj.equals(Constants.TRUE)) {
            result = Boolean.valueOf(Constants.FALSE.booleanValue());
        } else if (varObj.equals(Constants.FALSE)) {
            result = Boolean.valueOf(Constants.TRUE.booleanValue());
        } else {
            throw new IllegalArgumentException();
        }
        return result.booleanValue();
    }
}
