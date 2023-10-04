public class Electronics extends Item{
    private static final int DISCOUNT_PERIOD = 60;
    private static final int DISCOUNT_RATE_INCREASE = 3;

    public Electronics(String name, double buyingPrice, double sellingPrice, int quantity, int daysLastBought, double discountRate) {
        super(name, buyingPrice, sellingPrice, quantity, daysLastBought, discountRate);
    }

    @Override
    public void updateDiscountRate() {
        setDiscountRate(getDiscountRate() + (this.getDaysLastBought() / DISCOUNT_PERIOD) * DISCOUNT_RATE_INCREASE);
    }
}
