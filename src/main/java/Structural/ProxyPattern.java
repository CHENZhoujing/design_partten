package Structural;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 代理模式
 */

public class ProxyPattern {
    public static void main(String[] args) {
        TankI tankI = new TankI();
        Movable movable = (Movable) Proxy.newProxyInstance(TankI.class.getClassLoader(), new Class[] {Movable.class}, new TankIProxy2(tankI));
        movable.move();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TankE.class);
        enhancer.setCallback(new TankProxy2());
        TankE tankE = (TankE) enhancer.create();
        tankE.move();
    }
}

//JDK动态代理
interface Movable {
    void move();
}

class TankI implements Movable {

    @Override
    public void move() {
        System.out.println("Moving");
    }
}

class TankIProxy implements Movable {
    private Movable movable;

    public TankIProxy(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void move() {
        System.out.println("执行前");
        movable.move();
        System.out.println("执行后");
    }
}

class TankIProxy2 implements InvocationHandler {
    private Movable movable;

    public TankIProxy2(Movable movable) {
        this.movable = movable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "执行前");
        Object invoke = method.invoke(movable, args);
        System.out.println(method.getName() + "执行后");
        return invoke;
    }
}

//CGLIB动态代理

class TankE {
    public void move() {
        System.out.println("Moving");
    }
}

class TankEProxy extends TankE {

    @Override
    public void move() {
        System.out.println("执行前");
        super.move();
        System.out.println("执行后");
    }
}

class TankProxy2 implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + "执行前");
        Object object = null;
        object = methodProxy.invokeSuper(o, objects);
        System.out.println(method.getName() + "执行后");
        return object;
    }
}