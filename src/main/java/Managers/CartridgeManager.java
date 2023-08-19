package Managers;

import exceptions.NotEnoughMoneyException;
import model.Cartridge;

import java.util.*;

public class CartridgeManager {
    //Все картриджи здесь, отсортированы в порядке уменьшения
    public Set<Cartridge> cartridges = new TreeSet<>(
            (c1, c2) -> c2.denomination - c1.denomination
    );

    //Выданные купюры
    public Map<Integer, Integer> banknotesOut = new TreeMap<>();

    //Обналичиваем!!!
    public void cashOut(int rubles) {

        int initialSum = rubles;

        //Перебираем все картриджи, в порядке уменьшения (начинаем с самого большого номинала)
        for (Cartridge currentCartridge : cartridges) {

            //Пока сумма, которую нужно выдать больше номинала в текущем картридже, и при это деньги в картридже еще есть
            //Выдаем по купюре текущего номинала
            while (rubles >= currentCartridge.denomination && !currentCartridge.isEmpty()) {
                rubles -= currentCartridge.denomination;
                currentCartridge.giveBanknote();

                //Записываем какую купюру и сколько выдали
                banknotesOut.put(currentCartridge.denomination,
                        banknotesOut.getOrDefault(currentCartridge.denomination, 0) + 1);
            }
        }

        System.out.println();
        System.out.println("Для суммы " + initialSum + " рублей было выдано:");

        for (int denomination : banknotesOut.keySet()) {
            int count = banknotesOut.get(denomination);

            System.out.println(count + " штук по " + denomination + " рублей");
        }
    }

    //Проверяем, можно ли вообще обналичить сумму, хватает ли денег
    //!!! Да, может быть так, что может остаться невыданная "мелочь", но давай это опустим :)

    private void checkAvailableSum(int rubles) {
        int totalSum = 0;

        for (Cartridge currentCartridge : cartridges) {
            totalSum += currentCartridge.count * currentCartridge.denomination;
        }

        if (rubles > totalSum) {
            throw new NotEnoughMoneyException();
        }
    }
}
