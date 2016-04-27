package colin.miniblog.controller.dashboard;

import colin.miniblog.controller.common.CommonController;
import colin.miniblog.core.model.CommonResultMap;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IUserService;
import com.github.cage.Cage;
import com.github.cage.GCage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by joker on 16-3-10.
 */
@Controller
@Scope("request")
@RequestMapping("mini/admin")
public class AdminDashboardController extends CommonController {

    private Logger logger= LoggerFactory.getLogger(AdminDashboardController.class);

    private static final int MAX_LOGIN_FAILTURE_TIMES=1;
    @Autowired
    private IUserService userservice;

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
    public Object userRegister(String username, String password, String confirmPasswrod) throws IOException {
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
                    UserInfo userInfo = userservice.userRegisterService(username, password);
                    result=new CommonResultMap<UserInfo>(userInfo==null?false:true,userInfo==null?"用户注册失败":"");
                }
            }
        }
        //返回个人用户中心，显示用户的基本信息和他自己发布的内容
        return "user_center";
    }
    @RequestMapping(value = "user/validate",method = RequestMethod.POST)
    @ResponseBody
    public Object validateUserinfo(String username,String password){
         Boolean result= userservice.validateUserLogin(username,password);
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
    public String userLogin(String username, String password) throws IOException {
        CommonResultMap<UserInfo> result = null;
        if (username == null || username.equals("")) {
            result = new CommonResultMap<>(false, "用户名不能为空！");
        } else {
            if (password == null || password.equals("")) {
                result = new CommonResultMap<>(false, "用户密码不能为空！");
            } else {
                UserInfo userInfo = userservice.userLoginService(username, password);
                if(userInfo==null){
                    if(getLoginFailtureTimes("user"+super.getAccessIp())==this.MAX_LOGIN_FAILTURE_TIMES){
                        //生成验证码
                        Cage cage = new GCage();
                        OutputStream os=null;
                        try {
                            File codeImage=new ServletContextResource(super.getHttpSession().getServletContext(), "/resources/validate/captcha.jpg").getFile();
                            if(!codeImage.exists()){
                                codeImage.createNewFile();
                            }
                            os= new FileOutputStream(codeImage, false);
                            String tokenKey=cage.getTokenGenerator().next();
                            System.out.println("当前的key是"+tokenKey+"---"+codeImage.getAbsolutePath());
                            super.getHttpSession().setAttribute("user"+super.getAccessIp()+"code",tokenKey);
                            cage.draw(tokenKey, os);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            os.close();
                        }
                        result=new CommonResultMap<UserInfo>(false,"登录错误次数达最大次数");
                    }else{
                        this.setLoginFailtureTimes("user"+super.getAccessIp(),getLoginFailtureTimes("user"+super.getAccessIp())+1);
                        result=new CommonResultMap<UserInfo>(false,"用户名或密码错误");
                    }

                }else{
                    result=new CommonResultMap<UserInfo>(true,"");
                }
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
    /**
     * 返回当前用户的错误登录次数
     * @param keyName
     * @return
     */
    private int getLoginFailtureTimes(String keyName){
        Object failtureTimes=this.getHttpSession().getAttribute(keyName);
        if(failtureTimes==null){
            return 0;
        }else{
            return Integer.parseInt(failtureTimes.toString());
        }
    }

    /**
     * 设定当前用户的错误登录次数
     * @param keyName
     * @param times
     */
    private void setLoginFailtureTimes(String keyName,int times){
        this.getHttpSession().setAttribute(keyName,times);
    }


}
