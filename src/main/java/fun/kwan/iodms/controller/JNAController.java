package fun.kwan.iodms.controller;

import fun.kwan.iodms.JNA.Dllinterface;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 简小
 * @create 2020-04-13 16:50
 */
@RequestMapping("jna")
@RestController
public class JNAController {

    Dllinterface instance = Dllinterface.INSTANCE;
    int count = instance.ZLDM_StartSearchDev();
    List<String> devList = new ArrayList<>();

    @GetMapping("search")
    public String search(){
        for (int i = 0; i < count; i++) {
            devList.add(instance.ZLDM_GetDevID(i));
            System.out.println("Device id is:" + devList.get(i));
        }

        for (int i = 0; i < count; i++) {
            String params = instance.ZLDM_GetDevParamString(devList.get(i), 2);
            int intparams = instance.ZLDM_GetDevParamInt(devList.get(i), 3);
            System.out.println(i + "号设备的String参数为： " + params);
            System.out.println(i + "号设备的int参数为： " + intparams);
        }
        return devList.toString();
    }

    @GetMapping("ip")
    public String returnIp(Model model){
        List<String> ips = new ArrayList<>();
        for (int i = 0; i < devList.size(); i++) {
            ips.add(instance.ZLDM_GetDevParamString(devList.get(i),2));
        }

        return ips.toString();
    }

    @GetMapping("port")
    public String returnPort(Model model){
        List<Integer> returnPort = new ArrayList<>();
        for (int i = 0; i < devList.size(); i++) {
            returnPort.add(instance.ZLDM_GetDevParamInt(devList.get(i),3));
        }

        return returnPort.toString();
    }

    @GetMapping("mav")
    public ModelAndView hello(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jnatest");
        modelAndView.addObject("jna","Hello World!!!");
        List<Integer> returnPort = new ArrayList<>();
        for (int i = 0; i < devList.size(); i++) {
            returnPort.add(instance.ZLDM_GetDevParamInt(devList.get(i),3));
        }
        modelAndView.addObject("ports",returnPort);
        return modelAndView;
    }

    @RequestMapping(value = "mav",method = RequestMethod.POST)
    public ModelAndView postTet(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jnatest");
        return modelAndView;
    }

    @RequestMapping(value = "method/{id}/{name}",method =RequestMethod.GET)
    public String gettest(@PathVariable("id") Integer id,@PathVariable("name") String name){
        return "id : "+id+",  name :"+name;
    }

    @RequestMapping(value = "method",method = RequestMethod.GET)
    public String paramTest(@RequestParam Integer id){
        return "Param is: "+id;
    }

    @RequestMapping(value = "param",method = RequestMethod.GET)
    public String paramTest(@RequestParam("user") String user){
        return "Param user is:"+user;
    }

}
