import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class CarPanel extends JComponent {
    private Car car1;
    private int x, y, delay;
    private CarQueue carQueue;
    private int direction;

    CarPanel(int x1, int y1, int d, CarQueue queue) {
        delay = d;
        x = x1;
        y = y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
    }

    public void startAnimation() {
        Runnable animationRunnable = new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 1000; i++) {
                        direction = carQueue.deleteQueue();
                        updatePosition(direction);
                        repaint();
                        Thread.sleep(delay * 100);
                    }
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                } finally {
                    System.out.println("Animation Done!");
                }
            }

            private void updatePosition(int direction) {
                switch (direction) {
                    case 0:
                        y = Math.max(y - 10, 0);
                        break;
                    case 1:
                        y = Math.min(y + 10, 400);
                        break;
                    case 2:
                        x = Math.min(x + 10, 300);
                        break;
                    case 3:
                        x = Math.max(x - 10, 0);
                        break;
                    default:
                        break;
                }
            }
        };

        Thread animationThread = new Thread(animationRunnable);
        animationThread.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        car1.draw(g2, x, y);
    }
}
