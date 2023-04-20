package lr10.oop_lr10;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class IntegralController {


    public TextField nThreads;
    public TextField time;
    public TextField n;
    public TextField result;

    public synchronized void send(double v) {
        totalResult += v;
        finished++;
        notify();
    }

    private double totalResult;
    private int finished;

    public void calculateButton(ActionEvent actionEvent) {
        int threads = Integer.parseInt(nThreads.getText());
        int N = Integer.parseInt(n.getText());
        double delta = (double) (1) / threads;
        totalResult = 0;
        finished = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            IntegralCalculator calculator = new IntegralCalculator(  i * delta, i * delta + delta, N / threads, t -> Math.log(t + 1), this);
            new Thread(calculator).start();
        }
        try {
            synchronized (this) {
                while (finished < threads) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted");
        }
        long finishTime = System.currentTimeMillis();
        result.setText(String.valueOf(totalResult));
        long timeRes = finishTime - startTime;
        time.setText(String.valueOf(timeRes));
    }
}