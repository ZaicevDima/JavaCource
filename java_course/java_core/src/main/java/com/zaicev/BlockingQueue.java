package com.zaicev;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be more 0");
        }
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(T element) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T element = queue.remove();
        notifyAll();
        return element;
    }

    public synchronized int size() {
        return queue.size();
    }
}
