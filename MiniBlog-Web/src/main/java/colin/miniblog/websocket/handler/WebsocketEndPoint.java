package colin.miniblog.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/25
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.websocket.handler
 * 创建websocket处理类
 */
@Service(value = "websocket")
public class WebsocketEndPoint extends TextWebSocketHandler {
  @Override
  protected void handleTextMessage(WebSocketSession session,
                                   TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
    TextMessage returnMessage = new TextMessage(message.getPayload());
    session.sendMessage(returnMessage);
  }
}
