/*
 * This file is generated by jOOQ.
 */
package com.rulerbug.zoo.tables.interfaces;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


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
public interface IAllbooks extends Serializable {

    /**
     * Setter for <code>mypages.allBooks.id</code>. 书ID
     */
    public void setId(Long value);

    /**
     * Getter for <code>mypages.allBooks.id</code>. 书ID
     */
    public Long getId();

    /**
     * Setter for <code>mypages.allBooks.bookName</code>. 书名
     */
    public void setBookname(String value);

    /**
     * Getter for <code>mypages.allBooks.bookName</code>. 书名
     */
    public String getBookname();

    /**
     * Setter for <code>mypages.allBooks.author</code>. 作者
     */
    public void setAuthor(String value);

    /**
     * Getter for <code>mypages.allBooks.author</code>. 作者
     */
    public String getAuthor();

    /**
     * Setter for <code>mypages.allBooks.password</code>. 写书的密码
     */
    public void setPassword(String value);

    /**
     * Getter for <code>mypages.allBooks.password</code>. 写书的密码
     */
    public String getPassword();

    /**
     * Setter for <code>mypages.allBooks.createTime</code>. 开始创作时间
     */
    public void setCreatetime(LocalDateTime value);

    /**
     * Getter for <code>mypages.allBooks.createTime</code>. 开始创作时间
     */
    public LocalDateTime getCreatetime();

    /**
     * Setter for <code>mypages.allBooks.changeTime</code>. 最后一次修改时间
     */
    public void setChangetime(LocalDateTime value);

    /**
     * Getter for <code>mypages.allBooks.changeTime</code>. 最后一次修改时间
     */
    public LocalDateTime getChangetime();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IAllbooks
     */
    public void from(com.rulerbug.zoo.tables.interfaces.IAllbooks from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IAllbooks
     */
    public <E extends com.rulerbug.zoo.tables.interfaces.IAllbooks> E into(E into);
}
