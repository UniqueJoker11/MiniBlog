package colin.miniblog.controller.dashboard;

import colin.miniblog.core.model.CommonResultMap;
import colin.miniblog.core.pojo.UserInfo;
import colin.miniblog.service.inter.IUserservice;
import colin.miniblog.utils.ColinCollectionsUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joker on 16-3-10.
 */
@Controller
@Scope("request")
@RequestMapping("miniblog")
public class DashboardController {
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
        return "dashboard";
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
    @ResponseBody
    public Object userRegister(String username, String password, String confirmPasswrod, boolean isRemote) {
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
        if (isRemote) {
            return new JSONPObject("register", result);
        } else {
            return result;
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param isRemote
     * @return
     */
    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    public Object userLogin(String username, String password, boolean isRemote) {
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
        if (isRemote) {
            return new JSONPObject("login", result);
        } else {
            return result;
        }
    }


}
