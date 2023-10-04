import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ShopTest {
    private static Shop shop;

    private static Item goodToys;
    private static Item goodClothes;
    private static Item goodToys2;
    private static Item goodClothes2;
    private static Item goodElectronics;
    private static Item goodFood;
    private static Item goodDecor;
    private static Item soldOutClothes;
    private static Item lowStockToys;
    private static Item expiredFood;
    private static Item maxDiscountItem;

    @Before
    public void initShop(){
        shop = new Shop();
        goodClothes = new Item("Clothes - tshirts", 10.0, 20.0, 5, 40, 30.0);
        goodToys = new Item("Toys - planes", 10.0, 20.0, 5, 40, 30.0);
        goodElectronics = new Item("Electronics - headphones", 100, 300, 5, 120, 30);
        goodDecor = new Item("Decor", 10, 50, 5, 40, 20);
        soldOutClothes = new Item("Clothes - tshirts", 15, 45, 0, 5, 10);
        lowStockToys = new Item("Toy - dolls", 10.0, 20.0, 2, 5, 10.0);
        expiredFood = new Item("Food - potatoes", 10.0, 20.0, 5, 15, 10.0);
        goodFood = new Item("Food - potatoes", 10.0, 20.0, 5, 5, 10.0);
        maxDiscountItem = new Item("Electronics - headphones", 100, 450, 15, 1000, 40.0);

        goodClothes2 = new Item("Clothes - tshirts", 10.0, 20.0, 5, 1, 30.0);
        goodToys2 = new Item("Toys - planes", 10.0, 20.0, 5, 1, 30.0);
    }

    @Test
    public void testSellItemsSuccess() throws Exception {
        shop.sellItems(goodToys2,3);
        shop.sellItems(goodClothes2, 2);

        assertEquals(2, goodToys2.getQuantity());
        assertEquals(0, shop.getProfit(), 0.01);
    }

    @Test(expected = Exception.class)
    public void testSellItemsSoldOut() throws Exception {
        shop.sellItems(soldOutClothes, 3);
    }

    @Test(expected = Exception.class)
    public void testSellItemsInsufficientQuantity() throws Exception {
        shop.sellItems(lowStockToys, 3);
    }

    @Test(expected = Exception.class)
    public void testSellItemsExpiredFood() throws Exception {
        shop.sellItems(expiredFood, 3);
    }

    @Test
    public void testSellItemsWithDiscount() throws Exception {
        Item[] items = new Item[]{goodClothes, goodToys, goodElectronics, goodDecor};

        for(int i=0; i < items.length; i++){
            shop.sellItems(items[i], 5);
        }

        assertEquals(35, goodClothes.getDiscountRate(), 0.01);
        assertEquals(34, goodToys.getDiscountRate(), 0.01);
        assertEquals(36, goodElectronics.getDiscountRate(), 0.01);
        assertEquals(24, goodDecor.getDiscountRate(), 0.01);

        assertEquals(7, goodClothes.getSellingPrice(),0.01);
        assertEquals(6.8, goodToys.getSellingPrice(), 0.01);
        assertEquals(108, goodElectronics.getSellingPrice(), 0.01);
        assertEquals(12, goodDecor.getSellingPrice(), 0.01);

        assertEquals(19, shop.getProfit(), 0.01);
    }

    @Test
    public void testSellItemsWithMaximumDiscount() throws Exception {
        shop.sellItems(maxDiscountItem, 15);

        assertEquals(40, maxDiscountItem.getDiscountRate(), 0.01);

        assertEquals(1200, shop.getProfit(), 0.01);
    }

}
