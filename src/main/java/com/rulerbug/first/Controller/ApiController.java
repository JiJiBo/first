package com.rulerbug.first.Controller;

import com.rulerbug.first.Utils.R;
import com.rulerbug.first.Utils.TextUtils;
import com.rulerbug.zoo.Tables;
import com.rulerbug.zoo.tables.Pages;
import com.rulerbug.zoo.tables.records.AllbooksRecord;
import com.rulerbug.zoo.tables.records.PagesRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.InitBinderDataBinderFactory;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

@Controller
@RequestMapping("/api")
@ResponseBody
public class ApiController {

    @Autowired
    DSLContext dsl;

    @RequestMapping("/writeAPage.do")
    public R writeAPage(
            Long bookid,
            String title,
            String smallTitle,
            String context,
            String password
    ) {
        if (TextUtils.isEmpty(title, smallTitle, context, password)) {
            return R.error("hava null");
        }
        AllbooksRecord allbooksRecord = dsl.selectFrom(Tables.ALLBOOKS).where(Tables.ALLBOOKS.ID.eq(bookid)).fetchOne();
        String PASSWORD = allbooksRecord.getPassword();
        if (!PASSWORD.equals(password)) {
            return R.error();
        }
        Long index = 0L;
        Result<PagesRecord> fetch = dsl.selectFrom(Tables.PAGES).where(Tables.PAGES.BOOKID.eq(bookid)).orderBy(Tables.PAGES.INDEX.desc()).fetch();
        if (fetch != null && fetch.size() > 0) {
            PagesRecord pagesRecord = fetch.get(0);
            if (null == pagesRecord) {
                index = 0L;
            } else {
                index = pagesRecord.getIndex();
            }
        }


        PagesRecord pages = new PagesRecord();
        pages.setBookid(bookid);
        pages.setTitle(title);
        pages.setSmalltitle(smallTitle);
        pages.setIndex(++index);
        pages.setContext(context);
        pages.setChangetime(LocalDateTime.now());
        pages.setFirstcreattime(LocalDateTime.now());
        PagesRecord newRecord = dsl.newRecord(Tables.PAGES, pages);
        newRecord.insert();
        newRecord.refresh();
        return R.ok();
    }

    @RequestMapping("/creatABook.do")
    public R creatABook(
            String password,
            String bookName,
            String author
    ) {
        if (TextUtils.isEmpty(password, bookName, author)) {
            return R.error("hava null");
        }
        AllbooksRecord record = new AllbooksRecord();
        record.setPassword(password);
        record.setBookname(bookName);
        record.setAuthor(author);
        record.setCreatetime(LocalDateTime.now());
        record.setChangetime(LocalDateTime.now());
        AllbooksRecord newRecord = dsl.newRecord(Tables.ALLBOOKS, record);
        newRecord.insert();
        newRecord.refresh();
        return R.ok();
    }

    @RequestMapping("/delThebook.do")
    public R delThebook(
            Long bookid,
            String password
    ) {
        AllbooksRecord allbooksRecord = dsl.selectFrom(Tables.ALLBOOKS).where(Tables.ALLBOOKS.ID.eq(bookid)).fetchOne();
        String PASSWORD = allbooksRecord.getPassword();
        if (!PASSWORD.equals(password)) {
            return R.error();
        }
        dsl.delete(Tables.ALLBOOKS).where(Tables.ALLBOOKS.ID.eq(bookid)).execute();
        dsl.delete(Tables.PAGES).where(Tables.PAGES.BOOKID.eq(bookid)).execute();
        return R.ok();
    }

    @RequestMapping("/delThePage.do")
    public R delThePage(
            Long pageid,
            String password
    ) {
        PagesRecord pagesRecord = dsl.selectFrom(Tables.PAGES).where(Tables.PAGES.ID.eq(pageid)).fetchOne();
        Long bookId = pagesRecord.getBookid();
        AllbooksRecord allbooksRecord = dsl.selectFrom(Tables.ALLBOOKS).where(Tables.ALLBOOKS.ID.eq(bookId)).fetchOne();
        String PASSWORD = allbooksRecord.getPassword();
        if (!PASSWORD.equals(password)) {
            return R.error();
        }
        dsl.delete(Tables.PAGES).where(Tables.PAGES.ID.eq(pageid)).execute();
        return R.ok();
    }
}
