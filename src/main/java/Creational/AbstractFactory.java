package Creational;

/*
 抽象工厂模式是为了让创建工厂和一组产品与使用相分离，并可以随时切换到另一个工厂以及另一组产品；
 抽象工厂模式实现的关键点是定义工厂接口和产品接口，但如何实现工厂与产品本身需要留给具体的子类实现，客户端只和抽象工厂与抽象产品打交道。
 */

public abstract class AbstractFactory {
    public abstract Button createButton();

    public abstract Border createBorder();

    public static AbstractFactory createFactory(String s) {
        switch (s) {
            case "M":
                return new MacFactory();
            case "W":
                return new WinFactory();
        }
        return null;
    }
}

interface Button {
}

interface Border {
}

class MacButton implements Button {
}

class MacBorder implements Border {
}

class WinButton implements Button {
}

class WinBorder implements Border {
}

class MacFactory extends AbstractFactory {

    @Override
    public Button createButton() {
        System.out.println("factoryMethod.MacButton created");
        return new MacButton();
    }

    @Override
    public Border createBorder() {
        System.out.println("factoryMethod.MacBorder created");
        return new MacBorder();
    }
}

class WinFactory extends AbstractFactory {

    @Override
    public Button createButton() {
        System.out.println("factoryMethod.WinButton created");
        return new WinButton();
    }

    @Override
    public Border createBorder() {
        System.out.println("factoryMethod.WinBorder created");
        return new WinBorder();
    }
}
