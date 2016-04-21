package colin.miniblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joker on 16-4-6.
 */
public class RequesResponseController {
    @Autowired(required = true)
    protected HttpServletRequest request;
    protected HttpServletResponse response;

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
}
