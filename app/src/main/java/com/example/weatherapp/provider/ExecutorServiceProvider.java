package com.example.weatherapp.provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceProvider {
    private static ExecutorService service;

    public static ExecutorService get() {
        if (service == null) {
            synchronized (ExecutorServiceProvider.class) {
                if (service == null) {
                    service = Executors.newCachedThreadPool();
                }
            }
        }

        return service;
    }
}
