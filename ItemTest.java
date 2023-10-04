import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ItemTest {
    @Test
    public void testItemAvailable() throws Exception {
        Item toys = new Item("RC Car", 10.0, 20.0, 5, 5, 10.0);
        Clothes clothes = new Clothes("tshirts", 15, 45, 5, 5, 10);
        assertTrue(toys.isAvailableAndValid(5));
        assertTrue(clothes.isAvailableAndValid(1));
    }

    @Test(expected = Exception.class)
    public void testItemNotAvailable() throws Exception{
        Item toys = new Item("RC Car", 10.0, 20.0, 0, 5, 10.0);

        toys.isAvailableAndValid(2);
    }

    @Test
    public void testValidFood() throws Exception{
        Food food = new Food("potatoes", 10, 15, 9, 9, 0);

        assertTrue(food.isAvailableAndValid(1));
    }

    @Test(expected = Exception.class)
    public void testExpiredFood() throws Exception{
        Food food = new Food("potatoes", 10, 15, 9, 10, 0);

        food.isAvailableAndValid(1);
    }

    @Test
    public void testUpdateItemDiscountRate(){
        Clothes clothes = new Clothes("tshirts", 10.0, 20.0, 5, 40, 30.0);
        Item toys = new Item("planes", 10.0, 20.0, 5, 40, 30.0);
        Electronics electronics = new Electronics("headphones", 100, 300, 5, 120, 30);
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
        Clothes clothes = new Clothes("tshirts", 10.0, 20.0, 5, 40, 30.0);
        Item toys = new Item("planes", 10.0, 20.0, 5, 40, 30.0);
        Electronics electronics = new Electronics("headphones", 100, 300, 5, 120, 30);
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
        Item toys = new Item("RC Car", 10.0, 20.0, 10, 5, 10.0);

        toys.updateInventory(2);
        assertEquals(8, toys.getQuantity());
    }
}
