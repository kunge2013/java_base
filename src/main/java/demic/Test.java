package demic;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.ZERO;
        BigDecimal setScale1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(setScale1);
    }
}
