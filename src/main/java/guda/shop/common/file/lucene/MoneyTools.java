package guda.shop.common.file.lucene;

import org.apache.lucene.document.NumberTools;
import org.springframework.util.Assert;

public class MoneyTools {
    private static final Double dou = new Double(1000.0D);

    public static String moneyToString(Double paramDouble) {
        Assert.notNull(paramDouble);
        return String.valueOf(paramDouble.doubleValue() * dou.doubleValue());
    }

    public static Double stringToMoney(String paramString) {
        Double localDouble = Double.parseDouble(paramString);
        return Double.valueOf(localDouble.doubleValue() / dou.doubleValue());
    }

    public static void main(String[] args){
        System.out.println(MoneyTools.stringToMoney("3999000.0"));
    }
}

