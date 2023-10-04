public class Food extends Item{
    public Food(String name, double buyingPrice, double sellingPrice, int quantity, int daysLastBought, double discountRate) {
        super(name, buyingPrice, sellingPrice, quantity, daysLastBought, discountRate);
    }

    @Override
    public boolean isAvailableAndValid(int quantityToSell) throws Exception {
        if(getDaysLastBought()>= 10){
            throw new Exception("Food item cannot be sold as it has expired");
        }
        return super.isAvailableAndValid(quantityToSell);
    }
}
