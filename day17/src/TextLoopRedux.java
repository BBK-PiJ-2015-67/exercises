import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Day 18 exercise 2
 */
public class TextLoopRedux {

    public static void main(String... args) {
        ExecutorService e = Executors.newFixedThreadPool(3);
        Executor ex = new TaskScheduler(new ScheduledTask());

        if (args.length < 1 || (!args[0].equals("0") && !args[0].equals("1"))) {
            System.out.println("USAGE: java TextLoop <mode>");
            System.out.println(" mode 0: without threads");
            System.out.println(" mode 1: with threads");
        } else if (args[0].equals("0")) {
            System.out.println("Without Threads!\n");
            for (int i = 0; i < 10; i++) {
                Runnable r = new TextLooper("Thread " + i);
                r.run();
            }
        } else {
            System.out.println("With Threads!\n");
            for (int i = 0; i < 10; i++) {
                Runnable r = new TextLooper("Thread " + i);
                ex.execute(r);
            }
//            ex.shutdown();
        }
    }
}