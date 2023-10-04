public class Clothes extends Item{
    public Clothes(String name, double buyingPrice, double sellingPrice, int quantity, int daysLastBought, double discountRate) {
        super(name, buyingPrice, sellingPrice, quantity, daysLastBought, discountRate);
    }

    @Override
    public void updateDiscountRate() {
        setDiscountRate(getDiscountRate() + (this.getDaysLastBought() / 40) * 5);
    }

}
