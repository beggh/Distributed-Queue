package org.example.services;

import org.example.impl.InMemoryCounter;
//import org.example.impl.InMemoryNonBlockingQueue;
import org.example.interfaces.IDistributedCounter;
import org.example.interfaces.IDistributedQueue;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static org.example.services.BurgerDistribution.MAX_BURGERS;


public class Main {
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();

        IDistributedCounter counter = new InMemoryCounter();
        BurgerDistribution service = BurgerDistribution.getInstance(counter);


        // Simulate 6 million burger claims using multiple threads
        int numThreads = 100; // Adjust based on system resources
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < MAX_BURGERS / numThreads; j++) {
                    try {
                        service.claimBurger();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Burger distribution completed (simulated).");
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1e9);

    }
}