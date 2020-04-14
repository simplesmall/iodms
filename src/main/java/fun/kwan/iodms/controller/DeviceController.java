package fun.kwan.iodms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 简小
 * @create 2020-04-08 15:12
 */
@Controller
@RequestMapping("device")
public class DeviceController {
    @RequestMapping(value = "summary", method = RequestMethod.GET)
    public String summary() {
        return "pages/device/summary";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "pages/device/list";
    }

    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String log() {
        return "pages/device/log";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search() {
        return "pages/device/search";
    }

}
