package com.rulerbug.first.Controller;

import com.rulerbug.first.Utils.L;
import com.rulerbug.first.Utils.M;
import com.rulerbug.zoo.Tables;
import com.rulerbug.zoo.tables.records.AllbooksRecord;
import com.rulerbug.zoo.tables.records.BooksRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HelloController {
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
                    for (Field<?> field : fields) {
                        m.p(field.getName(), field.getValue(action));
                    }
                    books.a(m);
                }
        );
        view.addObject("books", books);
        return view;
    }
}
