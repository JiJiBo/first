package com.rulerbug.first.Controller;

import com.rulerbug.first.Utils.R;
import com.rulerbug.zoo.Tables;
import com.rulerbug.zoo.tables.Pages;
import com.rulerbug.zoo.tables.records.AllbooksRecord;
import com.rulerbug.zoo.tables.records.PagesRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
@ResponseBody
public class ApiController {

    @Value("${password}")
    private String PASSWORD;

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

        if (!PASSWORD.equals(password)) {
            return R.error();
        }
        Long index = dsl.selectFrom(Tables.PAGES).orderBy(Tables.PAGES.INDEX.desc()).fetchOne().getIndex();
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
}
