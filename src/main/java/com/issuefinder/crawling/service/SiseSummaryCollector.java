package com.issuefinder.crawling.service;

import com.issuefinder.crawling.model.NaverSise;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SiseSummaryCollector implements Collector<NaverSise, Summary, Summary> {
    @Override
    public Supplier<Summary> supplier() {
        return Summary::new;
    }

    @Override
    public BiConsumer<Summary, NaverSise> accumulator() {
        return (summary, naverSise) -> {
            summary.setClosingPrice(naverSise.getClosingPrice());
            summary.setVolume(naverSise.getVolume());
        };
    }

    @Override
    public BinaryOperator<Summary> combiner() {
        return (summary, summary1) -> {
            summary.setClosingPrice(summary1.getClosingPrice());
            summary.setVolume(summary1.getVolume());
            return summary;
        };
    }

    @Override
    public Function<Summary, Summary> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.IDENTITY_FINISH);
    }
}
