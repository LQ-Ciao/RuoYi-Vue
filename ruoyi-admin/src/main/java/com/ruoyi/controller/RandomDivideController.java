package com.ruoyi.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.RandomDivideUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/random")
public class RandomDivideController {

    @GetMapping
    public String randomDivide(String names, Integer size) {
        if (ObjectUtil.isEmpty(size)) {
            size = 2;
        }
        List<String> nameList = StrUtil.split(names, ",");
        if (nameList.size() % 2 != 0) {
            return "有人落单了，在霸凌谁？";
        }
        return RandomDivideUtil.divide(nameList, size);
    }

    @GetMapping("/test")
    public JSONObject test() {
        JSONObject json = new JSONObject();
        json.put("code",200);
        json.put("data","test");

        return json;
    }



}
