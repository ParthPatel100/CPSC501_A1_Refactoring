public class Shop {
    public double profit;

    /**
     * Check if item is available and valid (not expired),
     * @param item: item to sell
     * @param quantityToSell: quantity of items to sell
     * @return true if item is available and valid (not expired)
     * @throws Exception: if item is not available to sell or if a food item is expired
     */
    public boolean itemAvailableAndValid(Item item, int quantityToSell) throws Exception{
        if(item.quantity == 0){
            throw new Exception("Item is currently sold out");
        }

        else if(item.quantity < quantityToSell){
            throw new Exception(String.format("Not enough items available to sell, only %s in stock", item.quantity));
        }

        else if(item.daysLastBought >= 10 && item.name.startsWith("Food")){
            throw new Exception("Food item cannot be sold as it has expired");
        }

        else {
            return true;
        }
    }

    /**
     * Update item discount rate of an item based on the type of item.
     * Maximum discount rate for any item is 40%
     * @param item:
     *            Clothes  - up by 5% every 40 days
     *            Electronics - up by 3% every 60 days
     *            Others - up by 2% every 20 days
     */
    public void updateItemDiscountRate(Item item) {
        if (item.name.startsWith("Clothes")) {
            item.discountRate += (item.daysLastBought / 40) * 5;
        } else if (item.name.startsWith("Electronics")) {
            item.discountRate += (item.daysLastBought / 60) * 3;
        } else {
            item.discountRate += (item.daysLastBought / 20) * 2;
        }

        if (item.discountRate >= 40) {
            item.discountRate = 40;
        }
    }

    /**
     * Update items selling price based on its discountRate
     * @param item: valid Item
     */
    public void applyDiscount(Item item){
        updateItemDiscountRate(item);
        item.sellingPrice *= item.discountRate / 100;
    }

    /**
     * Update profit after selling an item
     * Profit could be a negative value to indicate a loss
     * @param item: item sold
     * @param quantityToSell: quantity of items sold
     */
    public void updateProfit(Item item, int quantityToSell){
        profit += (quantityToSell * item.sellingPrice) - (item.quantity * item.buyingPrice);
    }

    /**
     * Update item inventory after selling the item
     * @param item: item sold
     * @param quantityToSell: quantity of items sold
     */
    public void updateInventory(Item item, int quantityToSell){
        item.quantity -= quantityToSell;
    }

    /**
     * sell items if they are available and valid
     * If items were last bought 30 days ago, applyDiscount to item
     * @param item: item to sell
     * @param quantityToSell: quantity of items to sell
     * @throws Exception: If item is not valid or available
     */
    public void sellItems(Item item, int quantityToSell) throws Exception {
        if(itemAvailableAndValid(item, quantityToSell)){
            if(item.daysLastBought >= 30){
                applyDiscount(item);
            }
            updateProfit(item, quantityToSell);
            updateInventory(item, quantityToSell);
        }
    }
}
