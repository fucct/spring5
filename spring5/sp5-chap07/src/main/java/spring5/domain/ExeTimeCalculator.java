package spring5.domain;

import spring5.domain.chap07.Calculator;

public class ExeTimeCalculator implements Calculator {

    private final Calculator delegate;

    public ExeTimeCalculator(final Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(final long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();
        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", delegate.getClass().getSimpleName(), num, (end - start) / 1000);
        return result;
    }
}
