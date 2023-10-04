public class Shop {
    public double profit;


    public void sellItems(Item item, int quantityToSell) throws Exception {
        if (item.quantity ==  0){
            throw new Exception("Item is sold out");
        }
        else if(item.quantity < quantityToSell){
            throw new Exception(String.format("Not enough items available to sell, only %s in stock", item.quantity));
        }
        else if(item.daysLastBought >= 10 && item.name.startsWith("Food")){
            throw new Exception("Item cannot be sold as it has expired");
        }
        else{
            if (item.daysLastBought >= 30){
                if(item.discountRate <= 40){
                    if(item.name.startsWith("Clothes")){
                        item.discountRate += (item.daysLastBought/ 40) * 5;
                    }
                    else if(item.name.startsWith("Electronics")){
                        item.discountRate += (item.daysLastBought / 60) * 3;
                    }
                    else {
                        item.discountRate += (item.daysLastBought / 20) * 2;
                    }
                    if(item.discountRate > 40){
                        item.discountRate = 40;
                    }
                }
                item.sellingPrice *= (item.discountRate / 100);
            }
            profit += (quantityToSell * item.sellingPrice) - (item.quantity * item.buyingPrice);
            item.quantity -= quantityToSell;
        }
    }
}
