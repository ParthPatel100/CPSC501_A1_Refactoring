public class Shop {
    private double profit;

    /**
     * Update profit after selling an item
     * Profit could be a negaative value to indicate a loss
     * @param item: item sold
     * @param quantityToSell: quantity of items sold
     */
    public void updateProfit(Item item, int quantityToSell){
        profit += (quantityToSell * item.getSellingPrice()) - (item.getQuantity() * item.getBuyingPrice());
    }

    public double getProfit() {
        return profit;
    }

    /**
     * sell items if they are available and valid
     * If items were last bought 30 days ago, applyDiscount to item
     * @param item: item to sell
     * @param quantityToSell: quantity of items to sell
     * @throws Exception: If item is not valid or available
     */
    public void sellItems(Item item, int quantityToSell) throws Exception {
        if(item.isAvailableAndValid(quantityToSell)){
            if(item.getDaysLastBought() >= 30){
                item.applyDiscount();
            }
            updateProfit(item, quantityToSell);
            item.updateInventory(quantityToSell);
        }
    }
}
