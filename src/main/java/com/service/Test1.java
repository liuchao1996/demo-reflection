package com.service;

import com.kernel.annotation.MyAPIMapping;
import com.kernel.annotation.MyMapping;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@MyMapping(value = "test_one")
public class Test1 {

    @MyAPIMapping("get")
    public Map<String, Object> get(Map<String,Object> map) {

        map.put("msg", "test_one查询成功");
        return map;
    }

}
