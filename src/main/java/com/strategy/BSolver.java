package com.strategy;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * B处理器
 *
 * @author lc
 * @version 1.0
 * @date 2019-08-24 10:44
 * @see com.strategy
 */
@Component
public class BSolver extends InspectionSolver {


    @Override
    public void solve(Map<String, Object> map) {
        System.out.println("B订单:" + map + "........");
    }

    @Override
    public String[] supports() {
        return new String[]{"B"};
    }
}
