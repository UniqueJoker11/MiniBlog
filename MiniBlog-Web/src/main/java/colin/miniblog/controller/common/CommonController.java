package colin.miniblog.controller.common;

import colin.miniblog.core.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 创建人 LinQiang
 * 创建日期 2016/4/5
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.controller
 */
public class CommonController {
  @Autowired
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected HttpSession httpSession;

  public HttpSession getHttpSession() {
    return httpSession==null?request.getSession():httpSession;
  }

  public void setHttpSession(HttpSession httpSession) {
    if (httpSession == null) {
      this.httpSession = request.getSession();
    } else {
      this.httpSession = httpSession;
    }
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  @ModelAttribute
  public void setResponse(HttpServletResponse response) {
    this.response = response;
  }

  /**
   * 返回用户访问IP
   * @return
     */
  public  String getAccessIp(){
    return this.request.getRemoteAddr();
  }


  /**
   * 设定用户信息
   *
   * @param user
   */
  public void setUserInfo(UserInfo user) {
    request.getSession().setAttribute("user", user);
  }

  /**
   * 获取用户信息
   *
   * @return
   */
  public UserInfo getUserInfo() {
    if (request.getSession().getAttribute("user")!=null){
      return (UserInfo)request.getSession().getAttribute("user");
    }else{
      return null;
    }
  }
}
