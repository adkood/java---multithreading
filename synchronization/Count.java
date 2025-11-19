package synchronization;

public class Count {
    int count = 0;

    public void increment() {

        synchronized (this) {
            this.count += 1;
        }

    } 

    public int getCount() {
        return count;
    }
}
