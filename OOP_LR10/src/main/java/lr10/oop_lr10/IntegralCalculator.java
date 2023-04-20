package lr10.oop_lr10;

import java.util.function.DoubleUnaryOperator;

public class IntegralCalculator implements Runnable{

    private IntegralController integralController;
    private Function function;

    public IntegralCalculator(double start, double end, int nSteps, DoubleUnaryOperator f, IntegralController integralController){
        function = new Function(start,end, nSteps, f);
        this.integralController = integralController;

    }

    @Override
    public void run() {
        double v = function.calculate();
        integralController.send(v);
    }
}
