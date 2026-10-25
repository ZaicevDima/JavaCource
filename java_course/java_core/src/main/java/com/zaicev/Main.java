package com.zaicev;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(3);
        Thread t = new Thread(() -> {
            try {
                queue.dequeue();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t.start();

        Thread.sleep(200);
        System.out.println(t.getState());
    }
}