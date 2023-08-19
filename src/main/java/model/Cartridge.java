package model;

public class Cartridge {
    //номинал
    public final int denomination;
    //Количество
    public int count;

    public Cartridge(int denomination, int count) {
        this.denomination = denomination;
        this.count = count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    //Выдает одну банкноту
    public void giveBanknote() {
        count--;
        System.out.println("Выдано " + denomination + " рублей.");
    }
}
