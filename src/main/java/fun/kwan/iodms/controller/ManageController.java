package fun.kwan.iodms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 简小
 * @create 2020-04-08 15:22
 */
@Controller
@RequestMapping(value = "manage",method = RequestMethod.GET)
public class ManageController {
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search() {
        return "pages/manage/search";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category() {
        return "pages/manage/category";
    }

    @RequestMapping(value = "warn", method = RequestMethod.GET)
    public String warn() {
        return "pages/manage/warn";
    }

    @RequestMapping(value = "range", method = RequestMethod.GET)
    public String range() {
        return "pages/manage/range";
    }

    @RequestMapping(value = "param", method = RequestMethod.GET)
    public String param() {
        return "pages/manage/param";
    }
}
