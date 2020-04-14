package fun.kwan.iodms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 简小
 * @create 2020-04-08 15:24
 */
@Controller
@RequestMapping("operation")
public class OperationController {
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String order() {
        return "pages/operation/order";
    }

    @RequestMapping(value = "send", method = RequestMethod.GET)
    public String send() {
        return "pages/operation/send";
    }

    @RequestMapping(value = "heartbeat", method = RequestMethod.GET)
    public String heartbeat() {
        return "pages/operation/heartbeat";
    }
}
