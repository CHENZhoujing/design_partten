package Creational;

/*
 只有private构造方法，确保外部无法实例化；
 通过private static变量持有唯一实例，保证全局唯一性；
 通过public static方法返回此唯一实例，使外部调用方能获取到实例。
 ex: Runtime runtime = Runtime.getRuntime();
 http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html

 */

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}

// 多线程双重检查 懒加载
class Singleton2 {
    private static Singleton2 INSTANCE = null;

    private Singleton2(){}

    public static Singleton2 getInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton2.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }
}
