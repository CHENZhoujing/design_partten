import Creational.AbstractFactory;

public class Test {
    public static void main(String[] args) {
        AbstractFactory factoryM = AbstractFactory.createFactory("M");
        AbstractFactory factoryW = AbstractFactory.createFactory("W");
        factoryM.createButton();
        factoryM.createBorder();
        factoryW.createButton();
        factoryW.createBorder();
    }
}
