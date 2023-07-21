package Structural;

/*
 享元模式
 运用共享技术有效地支持大量细粒度的对象
 负责创建和管理享元角色。当客户对象请求一个享元 对象时，享元工厂检査系统中是否存在符合要求的享元对象，如果存在则提供给客户；如果不存在的话，则创建一个新的享元对象
 */

import java.util.HashMap;

public class Flyweight {
    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        System.out.println(webSiteFactory.getWebSite("Web1"));
        System.out.println(webSiteFactory.getWebSite("Web2"));
        System.out.println(webSiteFactory.getWebSite("Web1"));
    }
}

interface WebSite {
    void use();
}

class ConcreteWebSite implements WebSite {

    private String name;

    public ConcreteWebSite(String name) {
        this.name = name;
    }

    @Override
    public void use() {
        System.out.println(name);
    }
}

class WebSiteFactory {

    HashMap<String, ConcreteWebSite> map = new HashMap<>();

    public WebSite getWebSite(String s) {
        if (!map.containsKey(s)) {
            map.put(s, new ConcreteWebSite(s));
        }
        return map.get(s);
    }
}