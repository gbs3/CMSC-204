import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
    private Queue<Integer> directionQueue;
    private Random random;

    public CarQueue() {
        directionQueue = new LinkedList<>();
        random = new Random();
        directionQueue.add(2);
        directionQueue.add(0);
        directionQueue.add(1);
        directionQueue.add(0);
        directionQueue.add(3);
    }

    public void addToQueue() {
        Runnable addToQueueTask = new RandomDirection();
        Thread addToQueueThread = new Thread(addToQueueTask);
        addToQueueThread.start();
    }

    public int deleteQueue() {
        return directionQueue.poll();
    }

    private class RandomDirection implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 1000; i++) {
                    int r = random.nextInt(4);
                    directionQueue.add(r);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
