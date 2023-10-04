import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void testItemAvailable() throws Exception {
        assertTrue(shop.itemAvailableAndValid(goodToys, 5));
        assertTrue(shop.itemAvailableAndValid(goodClothes, 1));
    }

    @Test(expected = Exception.class)
    public void testItemNotAvailable() throws Exception{
        shop.itemAvailableAndValid(lowStockToys, 3);
    }

    @Test
    public void testValidFood() throws Exception{
        assertTrue(shop.itemAvailableAndValid(goodFood, 1));
    }

    @Test(expected = Exception.class)
    public void testExpiredFood() throws Exception{
        shop.itemAvailableAndValid(expiredFood, 1);
    }

    @Test
    public void testUpdateItemDiscountRate(){
        Item[] items = new Item[]{goodClothes, goodToys, goodElectronics, goodDecor};


        for(int i=0; i < items.length; i++){
            shop.updateItemDiscountRate(items[i]);
        }

        assertEquals(35, goodClothes.discountRate, 0.01);
        assertEquals(34, goodToys.discountRate, 0.01);
        assertEquals(36, goodElectronics.discountRate, 0.01);
        assertEquals(24, goodDecor.discountRate, 0.01);
    }

    @Test
    public void testApplyDiscount(){
        Item[] items = new Item[]{goodClothes, goodToys, goodElectronics, goodDecor};
        for(int i=0; i < items.length; i++){
            shop.applyDiscount(items[i]);
        }

        assertEquals(7, goodClothes.sellingPrice,0.01);
        assertEquals(6.8, goodToys.sellingPrice, 0.01);
        assertEquals(108, goodElectronics.sellingPrice, 0.01);
    }

    @Test
    public void testSellItemsSuccess() throws Exception {
        shop.sellItems(goodToys2,3);
        shop.sellItems(goodClothes2, 2);

        assertEquals(2, goodToys2.quantity);
        assertEquals(0, shop.profit, 0.01);
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

        assertEquals(35, goodClothes.discountRate, 0.01);
        assertEquals(34, goodToys.discountRate, 0.01);
        assertEquals(36, goodElectronics.discountRate, 0.01);
        assertEquals(24, goodDecor.discountRate, 0.01);

        assertEquals(7, goodClothes.sellingPrice,0.01);
        assertEquals(6.8, goodToys.sellingPrice, 0.01);
        assertEquals(108, goodElectronics.sellingPrice, 0.01);
        assertEquals(12, goodDecor.sellingPrice, 0.01);

        assertEquals(19, shop.profit, 0.01);
    }

    @Test
    public void testSellItemsWithMaximumDiscount() throws Exception {
        shop.sellItems(maxDiscountItem, 15);

        assertEquals(40, maxDiscountItem.discountRate, 0.01);

        assertEquals(1200, shop.profit, 0.01);
    }

}
