public class Item {
    public String name;
    public double buyingPrice;
    public int daysLastBought;
    public double sellingPrice;
    public int quantity;
    public double discountRate;

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
    public boolean itemAvailableAndValid(int quantityToSell) throws Exception{
        if(this.quantity == 0){
            throw new Exception("Item is currently sold out");
        }

        else if(this.quantity < quantityToSell){
            throw new Exception(String.format("Not enough items available to sell, only %s in stock", this.quantity));
        }

        else if(this.daysLastBought>= 10 && this.name.startsWith("Food")){
            throw new Exception("Food item cannot be sold as it has expired");
        }

        else {
            return true;
        }
    }

    /**
     * Update items selling price based on its discountRate
     */
    public void applyDiscount(){
        updateItemDiscountRate();
        this.sellingPrice = (this.sellingPrice * discountRate/100);
    }

    /**
     * Update item discount rate of an item based on the type of item.
     * Maximum discount rate for any item is 40%.
     * Toys - up by 2% every 20 days.
     * Clothes  - up by 5% every 40 days.
     * Electronics - up by 3% every 60 days.
     * Others - up by 2% every 20 days.
     */
    public void updateItemDiscountRate() {
        if (this.name.startsWith("Clothes")) {
            this.discountRate = (discountRate + (this.daysLastBought / 40) * 5);
        }
        else if (this.name.startsWith("Electronics")) {
            this.discountRate = (discountRate + (this.daysLastBought / 60) * 3);
        }
        else {
            this.discountRate = (discountRate + (this.daysLastBought / 20) * 2);
        }

        if(discountRate >= 40){
            discountRate = 40;
        }
    }

    /**
     * Update item inventory after selling the item
     * @param quantityToSell: quantity of items sold
     */
    public void updateInventory(int quantityToSell){
        this.quantity = this.quantity - quantityToSell;
    }

}
