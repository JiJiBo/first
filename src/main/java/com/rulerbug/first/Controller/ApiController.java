package com.rulerbug.first.Controller;

import com.alibaba.fastjson.JSONObject;
import com.rulerbug.first.Utils.HttpUtils;
import com.rulerbug.first.Utils.LimitUtils;
import com.rulerbug.first.Utils.R;
import com.rulerbug.first.Utils.TextUtils;
import com.rulerbug.zoo.Tables;
import com.rulerbug.zoo.tables.records.AllbooksRecord;
import com.rulerbug.zoo.tables.records.PagesRecord;
import com.rulerbug.zoo.tables.records.UserRecord;
import com.rulerbug.zoo.tables.records.UserinfoRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

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

    @RequestMapping("/getYcode.do")
    public R getYCode(String imgUrl, String username, String password) throws IOException {
        //你的用户名
        //图片转换过的base64编码
        UserinfoRecord userinfoRecord = dsl.selectFrom(Tables.USERINFO).where(Tables.USERINFO.USER_NAME.eq(username).and(Tables.USERINFO.PASSWORD.eq(password))).fetchAny();
        if (userinfoRecord == null) {
            return R.error("没有此用户");
        }
        if (!LimitUtils.isHaveSearch(LimitUtils.SKILLS.ISCANUSEYCODE, userinfoRecord.getLimitStr())) {
            return R.error("此用户没有此权限");
        }
        String image = HttpUtils.httpToBase64(imgUrl);
        JSONObject obj = new JSONObject();
        obj.put("username", userinfoRecord.getUserName());
        obj.put("password", password);
        //typeid为可选参数 根据文档填写说明填写1:纯数字 2:纯英文
        //obj.put("typeid", "");
        //modelid定制识别的模型id,发布成功后的模型id。注：有modelid为定向识别，不存在modelid为通用识别：可空
        //obj.put("modelid", "");
        obj.put("image", image);
        try {
            String url = "http://api.ttshitu.com/base64";
            String ret = HttpUtils.httpRequestData(url, obj);
            Map map = JSONObject.parseObject(ret);
            if ((Boolean) map.get("success")) {
                Map map1 = JSONObject.parseObject(String.valueOf(map.get("data")));
                String result = (String) map1.get("result");
                System.out.println("识别成功结果为:" + result);
                return R.ok().put("data", result);
            } else {
                System.out.println("识别失败原因为:" + map.get("message"));
                return R.error(map.get("message").toString());
            }

        } catch (Exception e) {
            System.out.println("识别失败异常:");
        }
        return R.error();
    }

    @RequestMapping("/getYcodeByFile.do")
    public R getYcodeByFile(File f, String username, String password) throws IOException {
        //你的用户名
        //图片转换过的base64编码
        UserinfoRecord userinfoRecord = dsl.selectFrom(Tables.USERINFO).where(Tables.USERINFO.USER_NAME.eq(username).and(Tables.USERINFO.PASSWORD.eq(password))).fetchAny();
        if (userinfoRecord == null) {
            return R.error("没有此用户");
        }
        if (!LimitUtils.isHaveSearch(LimitUtils.SKILLS.ISCANUSEYCODE, userinfoRecord.getLimitStr())) {
            return R.error("此用户没有此权限");
        }
        String image = HttpUtils.getBase64FromFile(f);
        JSONObject obj = new JSONObject();
        obj.put("username", userinfoRecord.getUserName());
        obj.put("password", password);
        //typeid为可选参数 根据文档填写说明填写1:纯数字 2:纯英文
        //obj.put("typeid", "");
        //modelid定制识别的模型id,发布成功后的模型id。注：有modelid为定向识别，不存在modelid为通用识别：可空
        //obj.put("modelid", "");
        obj.put("image", image);
        try {
            String url = "http://api.ttshitu.com/base64";
            String ret = HttpUtils.httpRequestData(url, obj);
            Map map = JSONObject.parseObject(ret);
            if ((Boolean) map.get("success")) {
                Map map1 = JSONObject.parseObject(String.valueOf(map.get("data")));
                String result = (String) map1.get("result");
                System.out.println("识别成功结果为:" + result);
                return R.ok().put("data", result);
            } else {
                System.out.println("识别失败原因为:" + map.get("message"));
                return R.error(map.get("message").toString());
            }

        } catch (Exception e) {
            System.out.println("识别失败异常:");
        }
        return R.error();
    }

}
