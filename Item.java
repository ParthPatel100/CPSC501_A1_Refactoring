public class Item {
    private final String name;
    private final double buyingPrice;
    private final int daysLastBought;
    private double sellingPrice;
    private int quantity;
    private double discountRate;

    private static final int DISCOUNT_PERIOD = 20;
    private static final int DISCOUNT_RATE_INCREASE = 2;

    public Item(String name, double buyingPrice,double sellingPrice,int quantity, int daysLastBought,double discountRate){
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.daysLastBought = daysLastBought;
        this.discountRate = discountRate;
    }

    /**
     * Check if item is available and valid (not expired),
     * @param quantityToSell: quantity of items to sell
     * @return true if item is available and valid (not expired)
     * @throws Exception: if item is not available to sell or if a food item is expired
     */
    public boolean isAvailableAndValid(int quantityToSell) throws Exception{
        if(this.getQuantity() == 0){
            throw new Exception("Item is currently sold out");
        }

        else if(this.getQuantity() < quantityToSell){
            throw new Exception(String.format("Not enough items available to sell, only %s in stock", this.quantity));
        }

        return true;

    }

    /**
     * Update items selling price based on its discountRate
     */
    public void applyDiscount(){
        updateDiscountRate();
        setSellingPrice(getSellingPrice() * getDiscountRate()/100);
    }

    /**
     * Update item discount rate of an item based on the type of item.
     * Maximum discount rate for any item is 40%.
     * Clothes  - up by 5% every 40 days.
     * Electronics - up by 3% every 60 days.
     * Others - up by 2% every 20 days.
     */
    public void updateDiscountRate() {
        setDiscountRate(getDiscountRate() + (this.getDaysLastBought() / DISCOUNT_PERIOD) * DISCOUNT_RATE_INCREASE);
    }

    /**
     * Update item inventory after selling the item
     * @param quantityToSell: quantity of items sold
     */
    public void updateInventory(int quantityToSell){
        setQuantity(getQuantity() - quantityToSell);
    }

    public String getName(){
        return this.name;
    }

    public double getBuyingPrice(){
        return this.buyingPrice;
    }

    public int getDaysLastBought(){
        return this.daysLastBought;
    }

    public double getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(double sellingPrice){
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getDiscountRate(){
        return this.discountRate;
    }

    public void setDiscountRate(double discountRate){
        this.discountRate = Math.min(discountRate, 40);
    }
}
