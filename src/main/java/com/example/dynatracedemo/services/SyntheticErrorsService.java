package com.example.dynatracedemo.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class SyntheticErrorsService {

    private volatile static boolean isLoopEnabled = false;
    private final AtomicInteger counter = new AtomicInteger(0);
    ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    private final CounterThread counterThread = new CounterThread();
    private final ConcurrentLinkedQueue<AtomicInteger> queue = new ConcurrentLinkedQueue<>();

    public synchronized void startInfiniteLoop() {
        if (!isLoopEnabled) {
            isLoopEnabled = true;
            threadPoolExecutor.submit(counterThread);
        }
    }

    public synchronized void stopInfiniteLoop() {
        if (isLoopEnabled) {
            LOG.info("Shutdown loop. Counter is: " + counter.get());
//            threadPoolExecutor.shutdown();
            counterThread.cancel();
            LOG.info("Reset counter!");
            counter.set(0);
        }
    }

    private class CounterThread extends Thread {
        public void run() {
            Thread.currentThread().setName("counter_thread");
            if (!Thread.currentThread().isInterrupted()) {
                LOG.info("Enable infinite loop!");
                while (!Thread.currentThread().isInterrupted() && isLoopEnabled) {
                    try {
//                        Thread.sleep(1);
                        counter.incrementAndGet();
                        queue.add(counter);
                        LOG.info("Counter: " + counter.get());
                    } catch (Exception e) {
                        throw new RuntimeException("Infinite loop was interrupted!");
                    }
                }
            }
        }

        public void cancel() {
            LOG.info("Cancellation...!");
            LOG.info("Queue size was: " + queue.size());
            isLoopEnabled = false;
            interrupt();
        }
    }
}
