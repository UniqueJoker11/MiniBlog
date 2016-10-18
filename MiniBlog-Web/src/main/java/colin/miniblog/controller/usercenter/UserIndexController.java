package colin.miniblog.controller.usercenter;

import colin.miniblog.controller.common.CommonController;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IUserIndexService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/5/27.
 */
@Controller
@RequestMapping("mini/user")
public class UserIndexController extends CommonController{

    @Autowired
    private IUserIndexService userIndexService;

    /**
     * 首页显示
     *
     * @return
     */
    @RequestMapping(value = "center", method = RequestMethod.GET)
    public String showUserCenterHtml() {
        if(super.getHttpSession().getAttribute("userinfo")!=null){
            return "user_center";
        }else{
            return "redirect:/mini/user/login";
        }
    }

    /**
     * 用户登录页面显示
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showUserLoginHtml() {
        return "user_login";
    }

    /**
     * 显示公共主页
     * @return
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String showIndexHtml(){
        return "user_index";
    }
    /**
     * 用户注册页面显示
     *
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String showUserRegisterHtml() {
        return "user_register";
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "user/login")
    public String userLoginRequest(@RequestParam String username,@RequestParam String password){
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return "redirect:mini/user/login";
        }
       UserInfo userInfo=userIndexService.userLoginService(username, password);
       if(ObjectUtils.NULL.equals(userInfo)){
           return "redirect:/mini/user/login";
       }else{
           super.getHttpSession().setAttribute("userinfo",userInfo);
           return "user_center";
       }
    }

}
