package colin.miniblog.inteceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/25
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.inteceptor
 * 创建握手（handshake）接口
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
  @Override
  public boolean beforeHandshake(ServerHttpRequest request,
                                 ServerHttpResponse response, WebSocketHandler wsHandler,
                                 Map<String, Object> attributes) throws Exception {
    System.out.println("Before Handshake");
    return super.beforeHandshake(request, response, wsHandler, attributes);
  }

  @Override
  public void afterHandshake(ServerHttpRequest request,
                             ServerHttpResponse response, WebSocketHandler wsHandler,
                             Exception ex) {
    System.out.println("After Handshake");
    super.afterHandshake(request, response, wsHandler, ex);
  }
}
