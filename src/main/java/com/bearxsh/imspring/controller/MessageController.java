package com.bearxsh.imspring.controller;

import com.alibaba.fastjson.JSON;
import com.bearxsh.imspring.client.handler.ClientHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bearx
 */
@RestController
@RequestMapping("/msg")
public class MessageController {
    @GetMapping("/send")
    public void sendMessage(@RequestParam("dest") String dest, @RequestParam("msg") String msg) {
        System.out.println("send: " + msg);
        System.out.println("send: " + dest);
        Map<String, Object> map = new HashMap(3);
        map.put("type", 2);
        map.put("dest", dest);
        map.put("msg", msg);
        String data = JSON.toJSONString(map);
        ByteBuf buffer = Unpooled.buffer(data.length());
        byte[] bytes = data.getBytes();
        for (byte aByte : bytes) {
            buffer.writeByte(aByte);
        }
        ClientHandler.channel.writeAndFlush(buffer);
    }
}
