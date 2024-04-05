package org.example.impl;

import org.example.interfaces.IDistributedCounter;

public class InMemoryCounter implements IDistributedCounter {
    public long counter;
    public InMemoryCounter() {
        this.counter=0;
    }
    @Override
    public synchronized long get() {
        return counter;
    }

    @Override
    public synchronized void increment() {
        counter++;
    }
}
