package colin.miniblog.controller.dashboard;

import colin.miniblog.controller.common.CommonController;
import colin.miniblog.core.model.CommonResultMap;
import colin.miniblog.core.pojo.AdminInfo;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IAdminDashboardService;
import colin.miniblog.service.inter.IUserService;
import com.github.cage.Cage;
import com.github.cage.GCage;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by joker on 16-3-10.
 */
@Controller
@Scope("request")
@RequestMapping("mini/admin")
public class AdminDashboardController extends CommonController {

    private Logger logger = LoggerFactory.getLogger(AdminDashboardController.class);

    private final AtomicInteger MAX_LOGIN_FAILTURE_TIMES = new AtomicInteger(6);
    @Autowired
    private IAdminDashboardService adminDashboardService;

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
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showUserLoginHtml() {
        return "user_login";
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
     * 用戶註冊
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "user/login/isValid", method = RequestMethod.POST)
    @ResponseBody
    public Object userLoginValidate(String username, String password) throws IOException {
        CommonResultMap<UserInfo> result = null;
        if (StringUtils.isBlank(username)) {
            result = new CommonResultMap<>(false, "用户名不能为空！");
        } else {
            if (StringUtils.isBlank(password)) {
                result = new CommonResultMap<>(false, "用户密码不能为空！");
            } else {
                boolean isValid = adminDashboardService.validateAdminIngoLogin(username, password);
                result = new CommonResultMap<UserInfo>(isValid, isValid ? "验证成功" : "验证失败");
            }
        }
        return result;
    }

    /**
     * 用户登录
     *
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
                AdminInfo adminInfo = adminDashboardService.adminInfoLoginService(username, password);
                if (ObjectUtils.NULL.equals(adminInfo)) {
                    if (getLoginFailtureTimes("user" + super.getAccessIp()) == this.MAX_LOGIN_FAILTURE_TIMES.get()) {
                        //生成验证码
                        Cage cage = new GCage();
                        OutputStream os = null;
                        try {
                            File codeImage = new ServletContextResource(super.getHttpSession().getServletContext(), "/resources/validate/captcha.jpg").getFile();
                            if (!codeImage.exists()) {
                                codeImage.createNewFile();
                            }
                            os = new FileOutputStream(codeImage, false);
                            String tokenKey = cage.getTokenGenerator().next();
                            super.getHttpSession().setAttribute("user" + super.getAccessIp() + "code", tokenKey);
                            cage.draw(tokenKey, os);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            os.close();
                        }
                        result = new CommonResultMap<UserInfo>(false, "登录错误次数达最大次数");
                    } else {
                        this.setLoginFailtureTimes("user" + super.getAccessIp(), getLoginFailtureTimes("user" + super.getAccessIp()) + 1);
                        result = new CommonResultMap<UserInfo>(false, "用户名或密码错误");
                    }

                } else {
                    this.request.getSession().setAttribute("userinfo", adminInfo);
                    return "user_center";
                }
            }
        }
        if (result.isSuccess()) {
            this.request.getSession().setAttribute("userinfo", (UserInfo) result.getT());
            return "user_center";
        } else {
            this.request.setAttribute("result", result);
            return "user_login";
        }

    }

    /**
     * 返回当前用户的错误登录次数
     *
     * @param keyName
     * @return
     */
    private int getLoginFailtureTimes(String keyName) {
        Object failtureTimes = this.getHttpSession().getAttribute(keyName);
        if (failtureTimes == null) {
            return 0;
        } else {
            return Integer.parseInt(failtureTimes.toString());
        }
    }

    /**
     * 设定当前用户的错误登录次数
     *
     * @param keyName
     * @param times
     */
    private void setLoginFailtureTimes(String keyName, int times) {
        this.getHttpSession().setAttribute(keyName, times);
    }


}
