package com.issuefinder.crawling.service;

import com.issuefinder.crawling.model.NaverArticle;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ArticleSummartCollector implements Collector<NaverArticle, Summary, Summary > {

    @Override
    public Supplier<Summary> supplier() {
        return Summary::new;
    }

    @Override
    public BiConsumer<Summary, NaverArticle> accumulator() {
        return (summary, naverArticle) -> {
            summary.setCount(naverArticle.getTotalCount());
            summary.setViews(naverArticle.getViews());
            summary.setSympathy(naverArticle.getSympathy());
            summary.setUnsympathy(naverArticle.getUnsympathy());
        };
    }

    @Override
    public BinaryOperator<Summary> combiner() {
        return (summary, summary1) -> {
            summary.setCount(summary1.getCount());
            summary.setViews(summary1.getViews());
            summary.setSympathy(summary1.getSympathy());
            summary.setUnsympathy(summary1.getUnsympathy());
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
