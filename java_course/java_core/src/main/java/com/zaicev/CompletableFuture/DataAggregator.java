package com.zaicev.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class DataAggregator {
    private static final double ERROR_PROBABILITY = 0.2;
    private static final double DEFAULT_PRICE = 0.0;
    private static final String DEFAULT_DESCRIPTION = "Нет данных";
    private static final double DEFAULT_RATING = 0.0;

    public ProductInfo aggregateProductInfo(String productName) {
        CompletableFuture<Double> priceFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return fetchPrice(productName);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("price fallback: " + ex.getMessage());
                    return DEFAULT_PRICE;
                });

        CompletableFuture<String> descriptionFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return fetchDescription(productName);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("description fallback: " + ex.getMessage());
                    return DEFAULT_DESCRIPTION;
                });

        CompletableFuture<Double> ratingFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return fetchRating(productName);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("rating fallback: " + ex.getMessage());
                    return DEFAULT_RATING;
                });

        return priceFuture
                .thenCombine(descriptionFuture, (price, description) -> new Object[]{price, description})
                .thenCombine(ratingFuture, (pair, rating) ->
                        new ProductInfo(productName, (Double) pair[0], (String) pair[1], rating))
                .join();
    }

    private double fetchPrice(String productName) throws InterruptedException {
        simulateDelay();
        maybeFail("price service");
        return 899.99;
    }

    private String fetchDescription(String productName) throws InterruptedException {
        simulateDelay();
        maybeFail("description service");
        return "Описание товара";
    }

    private double fetchRating(String productName) throws InterruptedException {
        simulateDelay();
        maybeFail("rating service");
        return 4.7;
    }

    private void simulateDelay() throws InterruptedException {
        long delay = ThreadLocalRandom.current().nextLong(1000, 3000);
        Thread.sleep(delay);
    }

    private void maybeFail(String service) {
        if (ThreadLocalRandom.current().nextDouble() < ERROR_PROBABILITY) {
            throw new RuntimeException(service + " failed");
        }
    }
}
