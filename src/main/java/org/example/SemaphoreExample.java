package org.example;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        Shared shared = new Shared();
        Semaphore semaphore = new Semaphore(1); // Only 1 thread can access the counter at a time

        // Create multiple threads that increment the counter
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        semaphore.acquire(); // Acquire a permit to access the counter
                        shared.count++;
                        System.out.println("Thread " + Thread.currentThread().getName() + " incremented count: " + shared.count);
                        semaphore.release(); // Release the permit after modifying the counter
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    static class Shared {
        int count = 0;
    }
}


