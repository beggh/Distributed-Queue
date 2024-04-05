//package org.example.impl;
//
//import org.example.interfaces.IDistributedQueue;
//
//import java.util.concurrent.atomic.AtomicReference;
//
//public class InMemoryNonBlockingQueue implements IDistributedQueue {
//
//    private static final class Node {
//        private final String message;
//        private final Node next;
//
//        public Node(String message, Node next) {
//            this.message = message;
//            this.next = next;
//        }
//    }
//
//    private final AtomicReference<Node> head;
//    private final AtomicReference<Node> tail;
//
//    public InMemoryNonBlockingQueue() {
//        head = new AtomicReference<>(null);
//        tail = new AtomicReference<>(null);
//    }
//
//    @Override
//    public void enqueue(String message) {
//        Node newNode = new Node(message, null);
//        while (true) {
//            Node currentTail = tail.get();
//            if (currentTail != null) {
//                if (currentTail.next == null) {
//                    // Attempt to CAS the next pointer of tail
//                    if (currentTail.next.compareAndSet(null, newNode)) { // This line is updated
//                        tail.compareAndSet(currentTail, newNode);
//                        return;
//                    }
//                } else {
//                    // Tail has been updated, retry operation
//                    continue;
//                }
//            } else {
//                // Empty queue, attempt to CAS both head and tail
//                if (head.compareAndSet(null, newNode) && tail.compareAndSet(null, newNode)) {
//                    return;
//                }
//            }
//        }
//    }
//    @Override
//    public String dequeue() {
//        while (true) {
//            Node currentHead = head.get();
//            if (currentHead == null) {
//                return null; // Queue is empty
//            }
//            if (head.compareAndSet(currentHead, currentHead.next)) {
//                return currentHead.message;
//            }
//        }
//    }
//}
//
