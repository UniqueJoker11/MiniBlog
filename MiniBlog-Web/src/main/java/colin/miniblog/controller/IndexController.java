package colin.miniblog.controller;

import colin.miniblog.controller.common.CommonController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/14
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.controller
 */
@Controller
@RequestMapping("mini/blog")
@Scope("request")
public class IndexController extends CommonController {

  /**
   * 顯示博客首頁
   * @return
   */
  @RequestMapping(value = "index", method = RequestMethod.GET)
  public String showBlogIndex() {
    return "/pages/user_index.html";
  }
}
