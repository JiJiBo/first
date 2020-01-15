/*
 * This file is generated by jOOQ.
 */
package com.rulerbug.zoo.tables;


import com.rulerbug.zoo.Indexes;
import com.rulerbug.zoo.Keys;
import com.rulerbug.zoo.Mypages;
import com.rulerbug.zoo.tables.records.AllbooksRecord;

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
import org.jooq.Row5;
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
public class Allbooks extends TableImpl<AllbooksRecord> {

    private static final long serialVersionUID = 280703245;

    /**
     * The reference instance of <code>mypages.allBooks</code>
     */
    public static final Allbooks ALLBOOKS = new Allbooks();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AllbooksRecord> getRecordType() {
        return AllbooksRecord.class;
    }

    /**
     * The column <code>mypages.allBooks.id</code>. 书ID
     */
    public final TableField<AllbooksRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "书ID");

    /**
     * The column <code>mypages.allBooks.bookName</code>. 书名
     */
    public final TableField<AllbooksRecord, String> BOOKNAME = createField(DSL.name("bookName"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "书名");

    /**
     * The column <code>mypages.allBooks.author</code>. 作者
     */
    public final TableField<AllbooksRecord, String> AUTHOR = createField(DSL.name("author"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "作者");

    /**
     * The column <code>mypages.allBooks.createTime</code>. 开始创作时间
     */
    public final TableField<AllbooksRecord, LocalDateTime> CREATETIME = createField(DSL.name("createTime"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "开始创作时间");

    /**
     * The column <code>mypages.allBooks.changeTime</code>. 最后一次修改时间
     */
    public final TableField<AllbooksRecord, LocalDateTime> CHANGETIME = createField(DSL.name("changeTime"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "最后一次修改时间");

    /**
     * Create a <code>mypages.allBooks</code> table reference
     */
    public Allbooks() {
        this(DSL.name("allBooks"), null);
    }

    /**
     * Create an aliased <code>mypages.allBooks</code> table reference
     */
    public Allbooks(String alias) {
        this(DSL.name(alias), ALLBOOKS);
    }

    /**
     * Create an aliased <code>mypages.allBooks</code> table reference
     */
    public Allbooks(Name alias) {
        this(alias, ALLBOOKS);
    }

    private Allbooks(Name alias, Table<AllbooksRecord> aliased) {
        this(alias, aliased, null);
    }

    private Allbooks(Name alias, Table<AllbooksRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Allbooks(Table<O> child, ForeignKey<O, AllbooksRecord> key) {
        super(child, key, ALLBOOKS);
    }

    @Override
    public Schema getSchema() {
        return Mypages.MYPAGES;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ALLBOOKS_PRIMARY);
    }

    @Override
    public Identity<AllbooksRecord, Long> getIdentity() {
        return Keys.IDENTITY_ALLBOOKS;
    }

    @Override
    public UniqueKey<AllbooksRecord> getPrimaryKey() {
        return Keys.KEY_ALLBOOKS_PRIMARY;
    }

    @Override
    public List<UniqueKey<AllbooksRecord>> getKeys() {
        return Arrays.<UniqueKey<AllbooksRecord>>asList(Keys.KEY_ALLBOOKS_PRIMARY);
    }

    @Override
    public Allbooks as(String alias) {
        return new Allbooks(DSL.name(alias), this);
    }

    @Override
    public Allbooks as(Name alias) {
        return new Allbooks(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Allbooks rename(String name) {
        return new Allbooks(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Allbooks rename(Name name) {
        return new Allbooks(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
