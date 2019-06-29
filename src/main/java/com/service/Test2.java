package com.service;

import com.kernel.annotation.MyAPIMapping;
import com.kernel.annotation.MyMapping;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@MyMapping(value = "test_two")
public class Test2 {

    @MyAPIMapping("get")
    public Map<String, Object> get(Map<String,Object> map) {

        map.put("msg", "test_two查询成功");
        return map;
    }


    @MyAPIMapping("get2")
    public Map<String,Object> get2(Map<String,Object> map){
        map.put("msg", "get2查询成功");
        return map;
    }

}
