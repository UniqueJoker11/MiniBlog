package colin.miniblog.controller.dashboard;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.codec.digest.DigestUtils;
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

    /**
     * 首页显示
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
    public JSONPObject testData(String username, String password, String confirmPasswrod) {
        Map<String, Object> map = new HashMap<>();
        if (username == null || username.equals("")) {
            map.put("isSuccess", false);
            map.put("msg", "用户名不能为空！");
        } else {
            if (password == null || password.equals("")) {
                map.put("isSuccess", false);
                map.put("msg", "用户密码不能为空！");
            } else {
                if (confirmPasswrod == null || confirmPasswrod.equals("")) {
                    map.put("isSuccess", false);
                    map.put("msg", "确认密码不能为空！");
                } else if (!password.equals(confirmPasswrod)) {
                    map.put("isSuccess", false);
                    map.put("msg", "两次密码输入不一致");
                } else {
                    map.put("isSuccess", true);
                    map.put("msg", "恭喜你注册成功。你的用户名是" + username);
                }
            }

        }
        return new JSONPObject("register", map);
    }


}
