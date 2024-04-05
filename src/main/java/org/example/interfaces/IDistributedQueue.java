package org.example.interfaces;

public interface IDistributedQueue {
    void enqueue(String message);
    String dequeue();
}
