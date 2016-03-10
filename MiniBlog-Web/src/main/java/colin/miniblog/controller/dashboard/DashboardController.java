package colin.miniblog.controller.dashboard;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by joker on 16-3-10.
 */
@Controller
@Scope("request")
@RequestMapping("miniblog")
public class DashboardController {

    @RequestMapping("test")
    @ResponseBody
    public Object testData(String name){
        if(name.equals("")){
            return " you pass name is empty";
        }else{
            return "you pass name is "+name;
        }
    }
}
