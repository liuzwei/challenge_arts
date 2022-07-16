package com.challenge.arts.week29.spi.file;

import java.util.List;

import com.challenge.arts.week29.spi.SpiSearch;

/**
 * @author liuzhaowei
 * @date 2022/7/15 10:24
 */
public class FileSearchService implements SpiSearch {
    @Override
    public List<String> searchDocs(String keyWords) {
        System.out.println("文件搜索:keyWords="+keyWords);
        return null;
    }
}
