package org.example;

import Managers.CartridgeManager;
import model.Cartridge;

public class Main {
    public static void main(String[] args) {
        Cartridge c1 = new Cartridge(1000, 5);
        Cartridge c2 = new Cartridge(5000, 3);
        //Cartridge c3 = new Cartridge(100, 20);
        //Cartridge c4 = new Cartridge(500, 1);

        CartridgeManager manager = new CartridgeManager();

        manager.cartridges.add(c1);
        manager.cartridges.add(c2);
        //manager.cartridges.add(c3);
        //manager.cartridges.add(c4);

        manager.cashOut(15000);

    }
}