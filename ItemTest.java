import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ItemTest {
    @Test
    public void testItemAvailable() throws Exception {
        Item toys = new Item("Toys - RC Car", 10.0, 20.0, 5, 5, 10.0);
        Item clothes = new Item("Clothes - tshirts", 15, 45, 5, 5, 10);

        assertTrue(toys.isAvailableAndValid(5));
        assertTrue(clothes.isAvailableAndValid(1));
    }

    @Test(expected = Exception.class)
    public void testItemNotAvailable() throws Exception{
        Item toys = new Item("Toys - RC Car", 10.0, 20.0, 0, 5, 10.0);

        toys.isAvailableAndValid(2);
    }

    @Test
    public void testValidFood() throws Exception{
        Item food = new Item("Food - potatoes", 10, 15, 9, 9, 0);

        assertTrue(food.isAvailableAndValid(1));
    }

    @Test(expected = Exception.class)
    public void testExpiredFood() throws Exception{
        Item food = new Item("Food - potatoes", 10, 15, 9, 10, 0);

        food.isAvailableAndValid(1);
    }

    @Test
    public void testUpdateItemDiscountRate(){
        Item clothes = new Item("Clothes - tshirts", 10.0, 20.0, 5, 40, 30.0);
        Item toys = new Item("Toys - planes", 10.0, 20.0, 5, 40, 30.0);
        Item electronics = new Item("Electronics - headphones", 100, 300, 5, 120, 30);
        Item decor = new Item("Decor", 10, 50, 5, 40, 20);
        Item[] items = new Item[]{clothes, toys, electronics, decor};

        for(int i=0; i < items.length; i++){
            items[i].updateDiscountRate();
        }

        assertEquals(35, clothes.getDiscountRate(), 0.01);
        assertEquals(34, toys.getDiscountRate(), 0.01);
        assertEquals(36, electronics.getDiscountRate(), 0.01);
        assertEquals(24, decor.getDiscountRate(), 0.01);
    }

    @Test
    public void testApplyDiscount(){
        Item clothes = new Item("Clothes - tshirts", 10.0, 20.0, 5, 40, 30.0);
        Item toys = new Item("Toys - planes", 10.0, 20.0, 5, 40, 30.0);
        Item electronics = new Item("Electronics - headphones", 100, 300, 5, 120, 30);
        Item decor = new Item("Decor", 10, 50, 5, 40, 20);
        Item[] items = new Item[]{clothes, toys, electronics, decor};

        for(int i=0; i < items.length; i++){
            items[i].applyDiscount();
        }

        assertEquals(7, clothes.getSellingPrice(),0.01);
        assertEquals(6.8, toys.getSellingPrice(), 0.01);
        assertEquals(108, electronics.getSellingPrice(), 0.01);
    }

    @Test
    public void testUpdateInventory(){
        Item toys = new Item("Toys - RC Car", 10.0, 20.0, 10, 5, 10.0);

        toys.updateInventory(2);
        assertEquals(8, toys.getQuantity());
    }
}