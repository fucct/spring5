package spring5.domain.chap07;

public class RecCalculator implements Calculator {
    @Override
    public long factorial(final long num) {
        if (num < 2) {
            return 1;
        }
        return num * factorial(num - 1);
    }
}
