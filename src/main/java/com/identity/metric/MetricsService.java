package com.identity.metric;

import java.util.concurrent.TimeUnit;
import io.micrometer.core.instrument.Metrics;

public class MetricsService {
	public synchronized void incrementCounter(String counterName, String... tags){
        Metrics.counter(counterName, tags).increment();
    }

    public synchronized void incrementCounter(String counterName, double value, String... tags){
        Metrics.counter(counterName, tags).increment(value);
    }

    public synchronized void observeTime(String timerName, long duration, String... tags){
        Metrics.timer(timerName, tags).record(duration, TimeUnit.MILLISECONDS);
    }
}
