package thread;

/**
 * @author happy
 */
public class Volitale {

    int num1 = 10;
    int num2 = 20;

    private void change() {
        num1 = 30;
        num2 = num1;
    }

    private void printLn() {
        System.out.println(num1 + "    " + num2);
    }

    public static void main(String[] args) {
        while (true) {
            Volitale volitaleTest = new Volitale();
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volitaleTest.change();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volitaleTest.printLn();
            }).start();

        }


    }
}
