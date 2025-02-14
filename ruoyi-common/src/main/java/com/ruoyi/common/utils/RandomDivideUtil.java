package com.ruoyi.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomDivideUtil {

    public static String divide(List<String> nameList, Integer size) {

        // 随机分组
        Collections.shuffle(nameList);

        // 随机英雄
        List<String> legendList = randomLegends(nameList, size);

        // 输出
        return output(nameList, legendList, size);

    }

    private static String output(List<String> nameList, List<String> legendList, Integer size) {
        StringBuffer result = new StringBuffer();
        Integer groupCount = nameList.size() % 2 == 0 ? nameList.size() / 2 : nameList.size() / 2 + 1;

        for (int i = 0; i < groupCount; i++) {
            result.append(StrUtil.format("第{}组：\n", groupNum));
            Integer nameIndex = i * 2;
            result.append(nameList.get(nameIndex)).append("，备选英雄：");
            yourLegendList(size, legendList, result, nameIndex);

            nameIndex = i * 2 + 1;
            result.append(nameList.get(nameIndex)).append("，备选英雄：");
            yourLegendList(size, legendList, result, nameIndex);
            result.append("\n");
        }

        // 备选英雄
//        String alternative = StrUtil.format("第四名额外备选英雄：\n{}\n{}\n{}\n{}",
//                legendList.get(legendList.size() - 4),
//                legendList.get(legendList.size() - 3),
//                legendList.get(legendList.size() - 2),
//                legendList.get(legendList.size() - 1));
//
//        result.append(alternative);
//        System.out.println(result.toString());
        return result.toString().replace("\n", "<br>");
    }

    private static void yourLegendList(Integer size, List<String> legendList, StringBuffer result, Integer nameIndex) {
        for (int j = 0; j < size; j++) {
            result.append(legendList.get(nameIndex + j));
            // 最后一个不加逗号
            if (j != size - 1) {
                result.append("，");
            }
        }
        result.append("\n");
    }

    // 选择随机英雄
    private static List<String> randomLegends(List<String> nameList, Integer size) {
        // 获取ban位英雄
        List<String> banList = getBanList();

        File file = FileUtil.file("legends.txt");
        List<String> legends = FileUtil.readLines(file, "utf-8");
        Collections.shuffle(legends);

        List<String> pickList = new ArrayList<>();

        Integer listSize = nameList.size() * size + 4;

        for (int i = 0, j = 0; i < listSize; i++, j++) {
            if (banList.contains(legends.get(i))) {
//                System.out.println("ban位英雄："+legends.get(j));
                j++;
            }
            pickList.add(legends.get(j));
        }

        // 记录本次备选英雄
        File banFile = FileUtil.file("ban.txt");
        // 清空文件重新写入
        FileUtil.writeLines(pickList, banFile, "utf-8", false);

        return pickList;
    }

    private static List<String> getBanList() {
        File banFile = FileUtil.file("ban.txt");
        List<String> banList = FileUtil.readLines(banFile, "utf-8");
        if (CollectionUtil.isEmpty(banList)) {
            return new ArrayList<>();
        }
        return banList;
    }


    // 八人随机排列
    private static List<String> randomDivideUtil(List<String> nameList) {


        return nameList;


    }


}
