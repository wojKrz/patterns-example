public class SingleInstanceExample {

    private SingleInstanceExample() {

    }

    private CarFactory carFactory;

    public CarFactory getCarFactory() {
        return carFactory;
    }

    private int i = 0;

    private static SingleInstanceExample instance;

    static SingleInstanceExample getInstance() {
        if(instance == null) {
            instance = new SingleInstanceExample();
            instance.carFactory = new OpelCarFactory();
        }

        return instance;
    }

    void increaseI() {
        i++;
    }

    int getI() {
        return i;
    }
}
