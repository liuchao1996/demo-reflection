package com.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 控制器
 *
 * @author lc
 * @version 1.0
 * @date 2019-08-24 10:46
 * @see com.strategy
 */
@RestController
public class Controller {

    @Autowired
    private InspectionSolverChooser chooser;


    @GetMapping("execute")
    public void execute(@RequestParam Map<String, Object> map) {
        InspectionSolver choose = chooser.choose(map.get("tag").toString());
        if (choose == null) {
            System.out.println("没有查询到可用类型");
            return;
        }
        choose.solve(map);
    }

}
