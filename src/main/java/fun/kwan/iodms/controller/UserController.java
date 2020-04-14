package fun.kwan.iodms.controller;

import fun.kwan.iodms.entity.User;
import fun.kwan.iodms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 简小
 * @create 2020-04-06 14:06
 */
@RestController
@RequestMapping("/test")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("getUser/{id}")
    public  String GetUser(@PathVariable int id){
        return  userService.Sel(id).toString();
    }

    @RequestMapping("delUser/{id}")
    public int DelUser(@PathVariable int id){
        if (userService.Del(id)!=-1){
            return 1;
        }
        return -1;
    }

    @RequestMapping("upd/{id}")
    public String UpdUser(HttpServletRequest request){
        String name = request.getParameter("user");
        String password = request.getParameter("password");
        String realname = request.getParameter("realname");
        User user = new User();
        user.setId(5);
        user.setUserName(name);
        user.setPassWord(password);
        user.setRealName(realname);

        return userService.Upd(user).toString();
    }



}
