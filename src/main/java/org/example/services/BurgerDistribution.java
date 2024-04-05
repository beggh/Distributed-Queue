package org.example.services;

import org.example.interfaces.IDistributedQueue;
import org.example.interfaces.IDistributedCounter;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class BurgerDistribution {
    public static final int MAX_BURGERS = 6_000_000;
    private static BurgerDistribution instance = null;
    private final IDistributedCounter counter;
    //private final IDistributedQueue queue;
    private final LinkedBlockingQueue<String> queue;

    private BurgerDistribution(IDistributedCounter counter) {
        this.counter = counter;
        this.queue = new LinkedBlockingQueue<>(MAX_BURGERS);
    }

    public static BurgerDistribution getInstance(IDistributedCounter counter) {
        if (instance == null) {

                    instance = new BurgerDistribution(counter);

        }
        return instance;
    }

    public void claimBurger() throws InterruptedException {
        if (counter.get() < MAX_BURGERS) {
            counter.increment();
            queue.add("ClaimBurger");
            System.out.println("claiming burger " + counter.get() );
        } else {
            throw new RuntimeException("No more burgers available!");
        }
    }
}