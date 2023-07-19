package Structural;

/*
 将两个不兼容的类纠合在一起使用，属于结构型模式,需要有Adaptee(被适配者)和 Adaptor(适配器)两个身份
 处理旧系统代码时，希望能重用代码或希望能弥合旧系统与新系统的接口
 */

public class Adapter {
    public static final void main(String[] args) {
        EuropeSocketImpl europeSocket = new EuropeSocketImpl();
        ChineseSocketImpl chineseSocket = new ChineseSocketImpl();
        ChineseAdapterEurope chineseAdapterEurope = new ChineseAdapterEurope(europeSocket);
        System.out.println(europeSocket.useEuropeSocket());
        System.out.println(chineseSocket.useChineseSocket());
        System.out.println(chineseAdapterEurope.useChineseSocket());
    }
}

interface EuropeSocket {
    String useEuropeSocket();
}

interface ChineseSocket {
    String useChineseSocket();
}

class EuropeSocketImpl implements EuropeSocket {

    @Override
    public String useEuropeSocket() {
        return "Europe Socket";
    }
}

class ChineseSocketImpl implements ChineseSocket {

    @Override
    public String useChineseSocket() {
        return "Chinese Socket";
    }
}

class ChineseAdapterEurope implements ChineseSocket {

    private EuropeSocket europeSocket;

    public ChineseAdapterEurope(EuropeSocket europeSocket) {
        this.europeSocket = europeSocket;
    }

    @Override
    public String useChineseSocket() {
        return europeSocket.useEuropeSocket();
    }
}

