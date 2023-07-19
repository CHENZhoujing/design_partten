package Creational;

/*
 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 深度复制要把内部对象也复制，可以通过序列化和逐层clone来实现
 */

import java.io.Serializable;
import java.util.Date;

public class Prototype {
    public static final void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date(923231231031l);
        User user = new User();
        user.setBirthDate(date);
        // 克隆对象
        User user1 = (User) user.clone();
        // 修改原型对象中的属性
        date.setTime(123291231232l);
        System.out.println("----输出原型对象的属性------");
        System.out.println(user.getBirthDate());
        System.out.println("----输出clone对象的属性------");
        System.out.println(user1.getBirthDate());
    }
}

class User implements Cloneable, Serializable {

    private String name;
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Object clone() throws CloneNotSupportedException {
        Object object = super.clone();
        User user = (User) object;
        user.birthDate = (Date) this.birthDate.clone();
        return object;
    }
}