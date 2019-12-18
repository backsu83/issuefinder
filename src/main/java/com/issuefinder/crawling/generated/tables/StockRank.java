/*
 * This file is generated by jOOQ.
 */
package com.issuefinder.crawling.generated.tables;


import com.issuefinder.crawling.generated.DefaultSchema;
import com.issuefinder.crawling.generated.Indexes;
import com.issuefinder.crawling.generated.Keys;
import com.issuefinder.crawling.generated.tables.records.StockRankRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class StockRank extends TableImpl<StockRankRecord> {

    private static final long serialVersionUID = -1678469734;

    /**
     * The reference instance of <code>STOCK_RANK</code>
     */
    public static final StockRank STOCK_RANK = new StockRank();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StockRankRecord> getRecordType() {
        return StockRankRecord.class;
    }

    /**
     * The column <code>STOCK_RANK.ID</code>.
     */
    public final TableField<StockRankRecord, Integer> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>STOCK_RANK.COLLECT_DAY</code>.
     */
    public final TableField<StockRankRecord, LocalDate> COLLECT_DAY = createField(DSL.name("COLLECT_DAY"), org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>STOCK_RANK.COMPANY_CODE</code>.
     */
    public final TableField<StockRankRecord, String> COMPANY_CODE = createField(DSL.name("COMPANY_CODE"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>STOCK_RANK.CREATE_AT</code>.
     */
    public final TableField<StockRankRecord, LocalDateTime> CREATE_AT = createField(DSL.name("CREATE_AT"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>STOCK_RANK.REFER</code>.
     */
    public final TableField<StockRankRecord, String> REFER = createField(DSL.name("REFER"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>STOCK_RANK.RESOURCE_TYPE</code>.
     */
    public final TableField<StockRankRecord, Integer> RESOURCE_TYPE = createField(DSL.name("RESOURCE_TYPE"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>STOCK_RANK.SCORE</code>.
     */
    public final TableField<StockRankRecord, Integer> SCORE = createField(DSL.name("SCORE"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>STOCK_RANK.UPDATE_AT</code>.
     */
    public final TableField<StockRankRecord, LocalDateTime> UPDATE_AT = createField(DSL.name("UPDATE_AT"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>STOCK_RANK</code> table reference
     */
    public StockRank() {
        this(DSL.name("STOCK_RANK"), null);
    }

    /**
     * Create an aliased <code>STOCK_RANK</code> table reference
     */
    public StockRank(String alias) {
        this(DSL.name(alias), STOCK_RANK);
    }

    /**
     * Create an aliased <code>STOCK_RANK</code> table reference
     */
    public StockRank(Name alias) {
        this(alias, STOCK_RANK);
    }

    private StockRank(Name alias, Table<StockRankRecord> aliased) {
        this(alias, aliased, null);
    }

    private StockRank(Name alias, Table<StockRankRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> StockRank(Table<O> child, ForeignKey<O, StockRankRecord> key) {
        super(child, key, STOCK_RANK);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_46, Indexes.UNIQUE_COLLECT_INDEX_4);
    }

    @Override
    public Identity<StockRankRecord, Integer> getIdentity() {
        return Keys.IDENTITY_STOCK_RANK;
    }

    @Override
    public UniqueKey<StockRankRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_46;
    }

    @Override
    public List<UniqueKey<StockRankRecord>> getKeys() {
        return Arrays.<UniqueKey<StockRankRecord>>asList(Keys.CONSTRAINT_46, Keys.UNIQUE_COLLECT);
    }

    @Override
    public StockRank as(String alias) {
        return new StockRank(DSL.name(alias), this);
    }

    @Override
    public StockRank as(Name alias) {
        return new StockRank(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public StockRank rename(String name) {
        return new StockRank(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public StockRank rename(Name name) {
        return new StockRank(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, LocalDate, String, LocalDateTime, String, Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
