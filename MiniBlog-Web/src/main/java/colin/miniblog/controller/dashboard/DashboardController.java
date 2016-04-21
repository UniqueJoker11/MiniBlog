package colin.miniblog.controller.dashboard;

import colin.miniblog.controller.RequesResponseController;
import colin.miniblog.core.model.CommonResultMap;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IUserservice;
import colin.miniblog.utils.ColinCollectionsUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by joker on 16-3-10.
 */
@Controller
@Scope("request")
@RequestMapping("miniblog")
public class DashboardController extends RequesResponseController{
    @Autowired
    private IUserservice userservice;

    /**
     * 首页显示
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String showDashboardHtml(HttpServletRequest request) {
        //抓取最新的发布内容
        return "dashboard";
    }

    /**
     * 用户登录页面显示
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String showUserLoginHtml(){
        return "user_login";
    }

    /**
     * 用户注册页面显示
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String showUserRegisterHtml(){
        return "user_register";
    }

    /**
     * 用戶註冊
     *
     * @param username
     * @param password
     * @param confirmPasswrod
     * @return
     */
    @RequestMapping("user/register")
    public String userRegister(String username, String password, String confirmPasswrod) {
        CommonResultMap<UserInfo> result = null;
        if (username == null || username.equals("")) {
            result = new CommonResultMap<>(false, "用户名不能为空！");
        } else {
            if (password == null || password.equals("")) {
                result = new CommonResultMap<>(false, "用户密码不能为空！");
            } else {
                if (confirmPasswrod == null || confirmPasswrod.equals("")) {
                    result = new CommonResultMap<>(false, "确认密码不能为空！");
                } else if (!password.equals(confirmPasswrod)) {
                    result = new CommonResultMap<>(false, "两次密码输入不一致");
                } else {
                    result = userservice.userRegisterInfo(ColinCollectionsUtils.initParamsMap(new String[]{"username." + username, "pwd." + DigestUtils.md5(password)}));
                }
            }
        }
        //返回个人用户中心，显示用户的基本信息和他自己发布的内容
        return "user_center";
    }
    @RequestMapping(value = "user/validate",method = RequestMethod.POST)
    @ResponseBody
    public Object validateUserinfo(String username,String password){

         Boolean result= userservice.validateUserLogin(ColinCollectionsUtils.initParamsMap(new String[]{"username."+username,"pwd."+DigestUtils.md5(password)}));
         if (result){
             return new CommonResultMap<>(true,"用户名密码正确");
         }else {
             return new CommonResultMap<>(false,"用户名密码错误");
         }
    }
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public String userLogin(String username, String password) {
        CommonResultMap<UserInfo> result = null;
        if (username == null || username.equals("")) {
            result = new CommonResultMap<>(false, "用户名不能为空！");
        } else {
            if (password == null || password.equals("")) {
                result = new CommonResultMap<>(false, "用户密码不能为空！");
            } else {
                result = userservice.userLoginInfo(ColinCollectionsUtils.initParamsMap(new String[]{"username." + username, "pwd." + DigestUtils.md5(password)}));
            }
        }
        if(result.isSuccess()){
            this.request.getSession().setAttribute("userinfo",(UserInfo)result.getT());
            return "user_center";
        }else{
            this.request.setAttribute("result",result);
            return "user_login";
        }

    }


}
