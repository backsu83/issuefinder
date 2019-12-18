/*
 * This file is generated by jOOQ.
 */
package com.issuefinder.crawling.generated;


import com.issuefinder.crawling.generated.tables.Stock;
import com.issuefinder.crawling.generated.tables.StockRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = -592239840;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>STOCK</code>.
     */
    public final Stock STOCK = com.issuefinder.crawling.generated.tables.Stock.STOCK;

    /**
     * The table <code>STOCK_RANK</code>.
     */
    public final StockRank STOCK_RANK = com.issuefinder.crawling.generated.tables.StockRank.STOCK_RANK;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Stock.STOCK,
            StockRank.STOCK_RANK);
    }
}
