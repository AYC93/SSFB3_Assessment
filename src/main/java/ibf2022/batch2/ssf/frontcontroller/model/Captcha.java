package ibf2022.batch2.ssf.frontcontroller.model;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Captcha {
    private int a;
    private int b;
    private int ans;
    private int input;
    private boolean checkAns = false;
    private String op;

    boolean showCaptcha = false;

    Random rand = new Random();

    int randOp = rand.nextInt(5);

    public int getA() {
        a = rand.nextInt(50);
        return a;
    }

    public int getB() {
        b = rand.nextInt(50);
        return b;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public boolean isShowCaptcha() {
        return showCaptcha;
    }

    @Override
    public String toString() {
        return "What is " + a + " " + b + ", ans=" + ans + "]";
    }

    public void setShowCaptcha(boolean showCaptcha) {
        this.showCaptcha = showCaptcha;
    }

    public String getsOp() {
        switch (randOp) {
            case 1:
                return op = "+";
            case 2:
                return op = "-";
            case 3:
                return op = "*";
            case 4:
                return op = "/";
            default:
                return op = "null";
        }
    }

    public int getAns() {
        switch (randOp) {
            case 1:
                ans = a + b;
                return this.ans;
            case 2:
                ans = a - b;
                return this.ans;
            case 3:
                ans = a * b;
                return this.ans;
            case 4:
                ans = a / b;
                return this.ans;
            default:
                return 0;
        }
    }
}
