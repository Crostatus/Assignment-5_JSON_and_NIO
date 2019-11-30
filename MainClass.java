import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainClass {


    public static void main(String args[]) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 10000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        ByteBuffer buffer = ByteBuffer.allocate(256);
        FileChannel inputChannel = null;
        Counter contatoreOccorrenze = new Counter();
        try {
            inputChannel = FileChannel.open(Paths.get("conti_correnti2.json"), StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String conto = "";
        String bufferReadOutput;
        int graffeA = 0;
        int graffeC = 0;
        JSONParser parser = new JSONParser();
        try {
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                bufferReadOutput = StandardCharsets.UTF_8.decode(buffer).toString();
                char[] arrayToTest = bufferReadOutput.toCharArray();
                int j = 0;
                while (j < arrayToTest.length) {
                    if (arrayToTest[j] == '{')
                        graffeA++;
                    if (arrayToTest[j] == '}')
                        graffeC++;
                    if (graffeA > 0)
                        conto += arrayToTest[j];
                    if ((graffeA == graffeC) && graffeA != 0) {
                        JSONObject o = (JSONObject) parser.parse(conto);
                        CountingTask nextTask = new CountingTask(o, contatoreOccorrenze);
                        pool.submit(nextTask);
                        conto = "";
                        graffeA = 0;
                        graffeC = 0;
                    }
                    j++;
                }
                buffer.flip();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                pool.awaitTermination(300, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        contatoreOccorrenze.printResults();

    }
}
