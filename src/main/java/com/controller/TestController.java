package com.controller;

import com.kernel.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lc
 */
@RestController
public class TestController {

    @Autowired
    MyHandler myHandler;

    @RequestMapping("get")
    public Map<String, Object> get(@RequestParam("tag") String tag, @RequestParam("methodTag") String methodTag) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", new HashMap<>());
        return myHandler.handle(tag, methodTag, map);
    }
}
