public class Item {
    public String name;
    public double buyingPrice;
    public double sellingPrice;
    public int quantity;
    public int daysLastBought;
    public double discountRate;

    public Item(String name, double buyingPrice,double sellingPrice,int quantity, int daysLastBought,double discountRate){
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.daysLastBought = daysLastBought;
        this.discountRate = discountRate;
    }
}
