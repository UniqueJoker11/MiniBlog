package colin.miniblog.controller;

import colin.miniblog.core.pojo.User;
import colin.miniblog.service.inter.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/14
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.controller
 */
@Controller
@RequestMapping("mini/admin")
public class DashboardController extends CommonController{

  @Resource
  private IUserService userService;

  /**
   * 顯示後台登錄頁
   * @return
   */
  @RequestMapping(value = "login",method = RequestMethod.GET)
  public String showAdminLoginPage(){
    return "admin_login";
  }

  /**
   * 显示用户注册页面
   * @return
   */
  @RequestMapping(value = "register",method = RequestMethod.GET)
  public String userRegisterShow(){
    return "admin_register";
  }

  /**
   * 用户登录
   * @param username
   * @param password
   * @return
   */
  @RequestMapping(value = "user/login",method = RequestMethod.POST)
  public String userLogin(String username,String password){
    User user=userService.userLoginService(username,password);
    if(user!=null){
      super.getHttpSession().setAttribute("user",user);
      return "dashboard";
    }else{
      super.getRequest().setAttribute("loginSuccess",false);
      return "admin_login";
    }
  }

  /**
   * 用户注册
   * @param username
   * @param password
   * @return
   */
  @RequestMapping(value = "user/register",method = RequestMethod.POST)
  public String userRegister(String username,String password){
      User user=userService.userRegisterService(username,password);
    if(user!=null){
      return "admin_login";
    }else{
      return "admin_register";
    }
  }

  /**
   * 用户聊天
   * @return
   */
  @RequestMapping(value = "chat",method = RequestMethod.GET)
  public String userChatShow(){
    return "userChat";
  }

}
