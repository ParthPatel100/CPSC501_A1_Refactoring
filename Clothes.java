public class Clothes extends Item{
    private static final int DISCOUNT_PERIOD = 40;
    private static final int DISCOUNT_RATE_INCREASE = 5;

    public Clothes(String name, double buyingPrice, double sellingPrice, int quantity, int daysLastBought, double discountRate) {
        super(name, buyingPrice, sellingPrice, quantity, daysLastBought, discountRate);
    }

    @Override
    public void updateDiscountRate() {
        setDiscountRate(getDiscountRate() + (this.getDaysLastBought() / DISCOUNT_PERIOD) * DISCOUNT_RATE_INCREASE);
    }

}
