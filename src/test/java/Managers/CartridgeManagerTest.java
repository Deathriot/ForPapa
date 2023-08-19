package Managers;

import exceptions.NotEnoughMoneyException;
import model.Cartridge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CartridgeManagerTest {

    private CartridgeManager manager;

    @BeforeEach
    public void create() {
        manager = new CartridgeManager();
    }

    //Должен выдать 3 по 5000 для суммы 1500
    @Test
    public void shouldGive3of5000for15000() {
        manager.cartridges.add(new Cartridge(5000, 4));
        manager.cartridges.add(new Cartridge(1000, 4));

        manager.cashOut(15000);

        assertEquals(Optional.of(3), Optional.of(manager.banknotesOut.get(5000)));
    }

    //Должен выдать 4 по 5000 и 7 по 1000 для 27000
    // Ведь 5-тысячных купюр не хватает
    @Test
    public void shouldGive4of5000And7of1000For27000() {
        manager.cartridges.add(new Cartridge(5000, 4));
        manager.cartridges.add(new Cartridge(1000, 20));
        manager.cartridges.add(new Cartridge(500, 100));

        manager.cashOut(27000);

        assertEquals(Optional.of(4), Optional.of(manager.banknotesOut.get(5000)));
        assertEquals(Optional.of(7), Optional.of(manager.banknotesOut.get(1000)));
    }

    //Ну тут то же самое примерно
    @Test
    public void shouldGive1of1000And1Of500And20Of100For8000() {
        manager.cartridges.add(new Cartridge(5000, 0));
        manager.cartridges.add(new Cartridge(1000, 1));
        manager.cartridges.add(new Cartridge(500, 10));
        manager.cartridges.add(new Cartridge(100, 50));

        manager.cashOut(8000);

        assertEquals(Optional.of(1), Optional.of(manager.banknotesOut.get(1000)));
        assertEquals(Optional.of(10), Optional.of(manager.banknotesOut.get(500)));
        assertEquals(Optional.of(20), Optional.of(manager.banknotesOut.get(100)));
    }

    //Должен выкидывать исключение если денег не хватает
    @Test
    public void shouldBeExceptionWhenNoMoneyFor55000() {
        manager.cartridges.add(new Cartridge(5000, 3));
        manager.cartridges.add(new Cartridge(1000, 1));
        manager.cartridges.add(new Cartridge(500, 10));
        manager.cartridges.add(new Cartridge(100, 50));

        assertThrows(NotEnoughMoneyException.class,
                () -> manager.cashOut(55000));
    }

    //Тестов хватит, тут можно бесконечно
}
