package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopScore;

public class ShopScore extends BaseShopScore {
    private static final long serialVersionUID = 1L;

    public ShopScore() {
    }

    public ShopScore(Long paramLong) {
        super(paramLong);
    }

    public ShopScore(Long paramLong, Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2) {
        super(paramLong, paramInteger, paramBoolean1, paramBoolean2);
    }

    public static enum ScoreTypes {
        EMAIL_VALIDATE(10), ORDER_SCORE(11), BUYER_RETURN_PAY(22);

        private int value;

        private ScoreTypes(int paramInt) {
            this.value = paramInt;
        }

        public static ScoreTypes valueOf(int paramInt) {
            for (ScoreTypes localScoreTypes : values())
                if (localScoreTypes.getValue() == paramInt)
                    return localScoreTypes;
            throw new IllegalArgumentException("Connot find value " + paramInt + " in eunu OrderStatus.");
        }

        public int getValue() {
            return this.value;
        }
    }
}

