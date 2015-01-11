package guda.shop.common.file.lucene;

import org.apache.lucene.document.NumberTools;
import org.springframework.util.Assert;

public class MoneyTools {
    private static final Double _$1 = new Double(1000.0D);

    public static String moneyToString(Double paramDouble) {
        Assert.notNull(paramDouble);
        return String.valueOf(paramDouble.doubleValue() * _$1.doubleValue());
    }

    public static Double stringToMoney(String paramString) {
        Double localDouble = new Double(NumberTools.stringToLong(paramString));
        return Double.valueOf(localDouble.doubleValue() / _$1.doubleValue());
    }
}

