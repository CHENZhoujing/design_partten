package Behavioral;

/*
 迭代器模式
 */
public class Iterator {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository(new String[]{"Robert", "John", "Julie", "Lora"});
        Iterator_ iterator = nameRepository.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

interface Iterator_ {
    boolean hasNext();

    Object next();
}

interface Container {
    Iterator_ getIterator();
}

class NameRepository implements Container {
    String[] names;

    public NameRepository(String[] names) {
        this.names = names;
    }

    @Override
    public Iterator_ getIterator() {
        return new NameRepositoryIterator();
    }

    class NameRepositoryIterator implements Iterator_ {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            return names[index++];
        }
    }
}