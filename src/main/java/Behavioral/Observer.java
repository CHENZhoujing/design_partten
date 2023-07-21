package Behavioral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 观察者模式
 定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新
 */
public class Observer {
    public static void main(String[] args) {
        ProductObservable store = new Store();
        ProductObserver admin = new Admin();
        ProductObserver customer = new Customer();
        store.addObserver(admin);
        store.addObserver(customer);
        store.addNewProduct("test1", 11L);
        store.addNewProduct("test2", 10L);
    }
}

class Product {
    String name;
    Double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

interface ProductObserver {
    void onEvent(String event);
}

class Admin implements ProductObserver {

    @Override
    public void onEvent(String event) {
        System.out.println("[Customer] on product published: " + event);
    }
}

class Customer implements ProductObserver {

    @Override
    public void onEvent(String event) {
        System.out.println("[Admin] on product published: " + event);
    }
}

interface ProductObservable {
    void addObserver(ProductObserver observer);

    void removeObserver(ProductObserver observer);

    void addNewProduct(String name, double price);

    void setProductPrice(String name, double price);
}

class Store implements ProductObservable {
    private List<ProductObserver> observers = new ArrayList<>();
    private Map<String, Product> products = new HashMap<>();

    @Override
    public void addObserver(ProductObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void addNewProduct(String name, double price) {
        Product p = new Product(name, price);
        products.put(p.getName(), p);
        observers.forEach(observer -> observer.onEvent(p.toString()));
    }

    @Override
    public void setProductPrice(String name, double price) {
        Product p = products.get(name);
        p.setPrice(price);
        observers.forEach(observer -> observer.onEvent(p.toString()));
    }
}