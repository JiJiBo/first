/*
 * This file is generated by jOOQ.
 */
package com.rulerbug.zoo;


import com.rulerbug.zoo.tables.Allbooks;
import com.rulerbug.zoo.tables.Pages;
import com.rulerbug.zoo.tables.User;
import com.rulerbug.zoo.tables._Warning;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>mypages</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ALLBOOKS_PRIMARY = Indexes0.ALLBOOKS_PRIMARY;
    public static final Index PAGES_PRIMARY = Indexes0.PAGES_PRIMARY;
    public static final Index USER_PRIMARY = Indexes0.USER_PRIMARY;
    public static final Index _WARNING_PRIMARY = Indexes0._WARNING_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index ALLBOOKS_PRIMARY = Internal.createIndex("PRIMARY", Allbooks.ALLBOOKS, new OrderField[] { Allbooks.ALLBOOKS.ID }, true);
        public static Index PAGES_PRIMARY = Internal.createIndex("PRIMARY", Pages.PAGES, new OrderField[] { Pages.PAGES.ID }, true);
        public static Index USER_PRIMARY = Internal.createIndex("PRIMARY", User.USER, new OrderField[] { User.USER.ID }, true);
        public static Index _WARNING_PRIMARY = Internal.createIndex("PRIMARY", _Warning._WARNING, new OrderField[] { _Warning._WARNING.ID }, true);
    }
}
