package t3;

import static java.lang.Thread.sleep;

public class RGBLight implements Runnable {
    Signal signal;
    Thread thread = new Thread(this);

    public void startRGB(Signal signal) {
        this.signal = signal;
        thread.start();
    }//

    @Override
    public void run() {
        int x = 0;
        while (true) {
            if (x < 12000) x = x + 1;
            else x = 0;

            int n = x % 4;

            switch (n) {
                case 0:
                    signal.setName("红灯");
                    break;
                case 1:
                    signal.setName("黄灯");
                    break;
                case 2:
                    signal.setName("绿灯");
                    break;
                case 3:
                    signal.setName("黄灯");
                    break;
            }//

            System.out.println(signal.getName());

            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
