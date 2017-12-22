package com.jzpz.controller;

import com.jzpz.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * * ServerEndpoint
 * <p>
 * 使用spring-boot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，但在springboot中连容器都是spring管理的。
 * <p>
 * 虽然@Component默认是单例模式的，但spring-boot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
 * <p>
 * WebSocket客户端建立连接的地址
 *
 * @author weiQiang
 */
@ServerEndpoint("/chatRoom/{userName}")
@Component
public class ChatRoomController {

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    /**
     * 存活的session集合（使用线程安全的map保存）
     */
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    /**
     * 建立连接的回调方法
     *
     * @param session  与客户端的WebSocket连接会话
     * @param userName 用户名，WebSocket支持路径参数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        logger.info("{}:加入聊天室",userName);
        livingSessions.put(session.getId(), session);
        sendMessageToAll(DateUtils.getNow2String()+" "+userName + " 加入聊天室");
    }

    /**
     * 收到客户端消息的回调方法
     *
     * @param message 客户端传过来的消息
     * @param session 对应的session
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userName") String userName) {
        logger.info("{} 说:{}", userName, message);
        sendMessageToAll(DateUtils.getNow2String()+" "+userName + " : " + message);
    }


    /**
     * 发生错误的回调方法
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误{}", error.getMessage());
    }

    /**
     * 关闭连接的回调方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userName") String userName) {
        logger.error("{}:退出聊天室{}", userName);
        livingSessions.remove(session.getId());
        sendMessageToAll(DateUtils.getNow2String()+" "+userName + " 退出聊天室");
    }


    /**
     * 单独发送消息
     *
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("单独发送消息发生错误:{}",e.getMessage());
        }
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        livingSessions.forEach((sessionId, session) -> {
            sendMessage(session, message);
        });
    }

}
