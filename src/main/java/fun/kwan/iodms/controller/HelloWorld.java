package fun.kwan.iodms.controller;

import fun.kwan.iodms.entity.User;
import fun.kwan.iodms.entity.tcp.entity.AnotherNode;
import fun.kwan.iodms.entity.tcp.entity.CreateOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author 简小
 * @create 2020-04-06 13:10
 * <p>
 * 返回字符就restcontroller 返回页面则restcontroller
 * restcontroller 等价于  responsebody + controller
 */
@Controller
@RequestMapping("")
public class HelloWorld {
    @GetMapping("index")
    public String hello() {
        return "index";
    }

    @GetMapping("socketnode")
    public String socketNode() {
        return "socketNode";
    }




    // 简单的测试映射到html页面
    @GetMapping("test")
    public String test() {
        return "pages/device/test";
    }

    // 测试单例模式向首页返回请求结果
    @GetMapping("conn")
    public String GetRet(ModelMap map) {
        String another = null;

        CreateOrder createOrder = new CreateOrder(2, "0000", "06", "0101", "0000", "0008");
        AnotherNode anotherNode = new AnotherNode("192.168.1.254", 502, createOrder);
        try {
            another = anotherNode.CreateNode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.addAttribute("return", another);
        map.addAttribute("name", "<span style='color:red'>Jerry</span>");
        return "pages/device/test";
    }

    //  对象单例模式返回到前端页面
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String userShow(Model model) {
        User user = new User();
        user.setId(12);
        user.setUserName("Jianxiao");
        user.setPassWord("password Code");
        user.setRealName("cute pig");
        model.addAttribute("user", user);
        return "pages/device/test";
    }

    // 以set的方式存储对象并最后返回到页面
    @RequestMapping(value = "/user/set", method = RequestMethod.GET)
    public String set(Model model) {
        Set<String> allNames = new HashSet<String>();
        List<Integer> allIds = new ArrayList<Integer>();
        for (int x = 0; x < 5; x++) {
            allNames.add("boot-" + x);
            allIds.add(x);
        }
        model.addAttribute("names", allNames);
        model.addAttribute("ids", allIds);
        model.addAttribute("mydate", new java.util.Date());
        return "pages/device/test";
    }

    // 测试使用内嵌对象 request session servlet等
    @RequestMapping(value = "/inner", method = RequestMethod.GET)
    public String inner(HttpServletRequest request, Model model) {
        request.setAttribute("requestMessage", "springboot-request");
        request.getSession().setAttribute("sessionMessage", "springboot-session");
        request.getServletContext().setAttribute("applicationMessage",
                "springboot-application");
        model.addAttribute("url", "www.baidu.cn");
        request.setAttribute("url2",
                "<span style='color:red'>www.baidu.cn</span>");
        return "pages/device/test";
    }

    // 用map来存储多个对象
    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    public String userListByMap(Model model) {
        Map<String, User> map = new HashMap<String, User>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("No.sennion : " + i * 2);
            user.setPassWord("Password is:" + i);
            user.setRealName("Real " + new Date());
            map.put("number :" + i, user);
        }
        model.addAttribute("allusers", map);
        return "pages/device/test";
    }

    // 用List来存储多个对象
    @RequestMapping(value = "user/setlist", method = RequestMethod.GET)
    public String userListByList(Model model) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("Name : " + i * 2);
            user.setPassWord("Password :" + i);
            user.setRealName("Real :" + new Date());
            users.add(user);
        }
        model.addAttribute("listusers", users);
        return "pages/device/test";
    }


}


