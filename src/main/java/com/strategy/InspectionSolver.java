package com.strategy;

import java.util.Map;

/**
 * 抽象业务处理器
 *
 * @author lc
 * @version 1.0
 * @date 2019-08-24 10:41
 * @see com.strategy
 */
public abstract class InspectionSolver {

    public abstract void solve(Map<String, Object> map);

    public abstract String[] supports();
}
