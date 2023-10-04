public class Food extends Item{
    private static final int BEST_BEFORE_DAYS = 10;

    public Food(String name, double buyingPrice, double sellingPrice, int quantity, int daysLastBought, double discountRate) {
        super(name, buyingPrice, sellingPrice, quantity, daysLastBought, discountRate);
    }

    @Override
    public boolean isAvailableAndValid(int quantityToSell) throws Exception {
        if(getDaysLastBought()>= BEST_BEFORE_DAYS){
            throw new Exception("Food item cannot be sold as it has expired");
        }
        return super.isAvailableAndValid(quantityToSell);
    }
}
