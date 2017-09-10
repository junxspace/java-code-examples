package net.junx.codesnippet.thread.calculate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author junX
 * @date Aug 3, 2017 5:55:52 PM <br/>
 * @version
 *
 */
public class SimplePoolSizeCaculatorImpl extends PoolSizeCalculator {

    /**
     * @see net.junx.codesnippet.thread.calculate.PoolSizeCalculator#creatTask()
     */
    @Override
    protected Runnable creatTask() {
        return new AsyncIOTask();
    }

    /**
     * @see net.junx.codesnippet.thread.calculate.PoolSizeCalculator#createWorkQueue()
     */
    @Override
    protected BlockingQueue createWorkQueue() {
        return new LinkedBlockingQueue(1000);
    }

    /**
     * @see net.junx.codesnippet.thread.calculate.PoolSizeCalculator#getCurrentThreadCPUTime()
     */
    @Override
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }


    public static void main(String[] args) {
        PoolSizeCalculator poolSizeCalculator = new SimplePoolSizeCaculatorImpl();
        poolSizeCalculator.calculateBoundaries(new BigDecimal(1.0), new BigDecimal(100000));
    }
}


/**
 * 自定义的异步IO任务
 * 
 * @author Will
 *
 */
class AsyncIOTask implements Runnable {

    @Override
    public void run() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String getURL = "http://baidu.com";
            URL getUrl = new URL(getURL);

            connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // empty loop
            }
        }

        catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {

                }
            }
            connection.disconnect();
        }

    }

}
