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
public interface IPages extends Serializable {

    /**
     * Setter for <code>mypages.pages.id</code>. 文章ID
     */
    public void setId(Long value);

    /**
     * Getter for <code>mypages.pages.id</code>. 文章ID
     */
    public Long getId();

    /**
     * Setter for <code>mypages.pages.bookId</code>. 书Id
     */
    public void setBookid(Long value);

    /**
     * Getter for <code>mypages.pages.bookId</code>. 书Id
     */
    public Long getBookid();

    /**
     * Setter for <code>mypages.pages.index</code>. 章节索引
     */
    public void setIndex(Long value);

    /**
     * Getter for <code>mypages.pages.index</code>. 章节索引
     */
    public Long getIndex();

    /**
     * Setter for <code>mypages.pages.title</code>. 文章标题
     */
    public void setTitle(String value);

    /**
     * Getter for <code>mypages.pages.title</code>. 文章标题
     */
    public String getTitle();

    /**
     * Setter for <code>mypages.pages.smallTitle</code>. 文章小标题
     */
    public void setSmalltitle(String value);

    /**
     * Getter for <code>mypages.pages.smallTitle</code>. 文章小标题
     */
    public String getSmalltitle();

    /**
     * Setter for <code>mypages.pages.context</code>. 文章内容
     */
    public void setContext(String value);

    /**
     * Getter for <code>mypages.pages.context</code>. 文章内容
     */
    public String getContext();

    /**
     * Setter for <code>mypages.pages.firstCreatTime</code>. 创作时间
     */
    public void setFirstcreattime(LocalDateTime value);

    /**
     * Getter for <code>mypages.pages.firstCreatTime</code>. 创作时间
     */
    public LocalDateTime getFirstcreattime();

    /**
     * Setter for <code>mypages.pages.changeTime</code>. 修改时间
     */
    public void setChangetime(LocalDateTime value);

    /**
     * Getter for <code>mypages.pages.changeTime</code>. 修改时间
     */
    public LocalDateTime getChangetime();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IPages
     */
    public void from(com.rulerbug.zoo.tables.interfaces.IPages from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IPages
     */
    public <E extends com.rulerbug.zoo.tables.interfaces.IPages> E into(E into);
}
