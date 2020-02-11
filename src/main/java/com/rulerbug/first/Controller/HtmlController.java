package com.rulerbug.first.Controller;

import com.rulerbug.first.Utils.L;
import com.rulerbug.first.Utils.M;
import com.rulerbug.zoo.Tables;
import com.rulerbug.zoo.tables.records.AllbooksRecord;
import com.rulerbug.zoo.tables.records.PagesRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HtmlController {
    @Autowired
    DSLContext dsl;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("hello");
        Result<AllbooksRecord> allbooksRecords = dsl.selectFrom(Tables.ALLBOOKS).fetch();
        L books = L.c();
        allbooksRecords.forEach(
                action -> {
                    M m = M.c();
                    Field<?>[] fields = action.fields();
                    boolean isHaveNull = false;
                    for (Field<?> field : fields) {
                        Object value = field.getValue(action);
                        if (null == value) {
                            isHaveNull = true;
                        } else {
                            m.p(field.getName(), field.getValue(action));
                        }

                    }
                    if (!isHaveNull) {
                        books.a(m);
                    }

                }
        );
        view.addObject("books", books);
        return view;
    }

    @RequestMapping("{bookid}/pages.do")
    public ModelAndView pages(@PathVariable("bookid") Long bookid) {
        ModelAndView view = new ModelAndView("pages");
        Result<PagesRecord> pagesRecords = dsl.selectFrom(Tables.PAGES).where(Tables.PAGES.BOOKID.eq(bookid)).fetch();
        AllbooksRecord book = dsl.selectFrom(Tables.ALLBOOKS).where(Tables.ALLBOOKS.ID.eq(bookid)).fetchOne();
        L pages = L.c();
        pagesRecords.forEach(
                action -> {
                    M m = M.c();
                    Field<?>[] fields = action.fields();
                    for (Field<?> field : fields) {
                        m.p(field.getName(), field.getValue(action));
                    }
                    pages.a(m);
                }
        );
        view.addObject("pages", pages);
        view.addObject("bookid", bookid);
        view.addObject("author", book.getAuthor());
        view.addObject("bookname", book.getBookname());
        return view;
    }

    @RequestMapping("{pageid}/page.do")
    public ModelAndView page(@PathVariable("pageid") Long pageid) {
        ModelAndView view = new ModelAndView("page");
        PagesRecord page = dsl.selectFrom(Tables.PAGES).where(Tables.PAGES.ID.eq(pageid)).fetchOne();

        view.addObject("title", page.getTitle());
        view.addObject("smalltitle", page.getSmalltitle());
        view.addObject("context", page.getContext());
        return view;
    }

    @RequestMapping("{bookid}/addApage.do")
    public ModelAndView addAPage(@PathVariable("bookid") Long bookid) {
        ModelAndView view = new ModelAndView("addApage");
        AllbooksRecord book = dsl.selectFrom(Tables.ALLBOOKS).where(Tables.ALLBOOKS.ID.eq(bookid)).fetchOne();
        view.addObject("bookId", bookid);
        view.addObject("bookName", book.getBookname());
        return view;
    }

    @RequestMapping("{bookid}/delTheBook.do")
    public ModelAndView delTheBook(@PathVariable("bookid") Long bookid) {
        ModelAndView view = new ModelAndView("delTheBook");
        AllbooksRecord book = dsl.selectFrom(Tables.ALLBOOKS).where(Tables.ALLBOOKS.ID.eq(bookid)).fetchOne();
        view.addObject("bookId", bookid);
        view.addObject("bookName", book.getBookname());
        return view;
    }

    @RequestMapping("{pageid}/delThePage.do")
    public ModelAndView delThePage(@PathVariable("pageid") Long pageid) {
        ModelAndView view = new ModelAndView("delThePage");
        PagesRecord pagesRecord = dsl.selectFrom(Tables.PAGES).where(Tables.PAGES.ID.eq(pageid)).fetchOne();
        view.addObject("bookId", pageid);
        view.addObject("pageName", pagesRecord.getTitle() + "----" + pagesRecord.getSmalltitle());
        return view;
    }

    @RequestMapping("creatABook.do")
    public ModelAndView creatABook() {
        ModelAndView view = new ModelAndView("creatABook");
        return view;
    }
}
