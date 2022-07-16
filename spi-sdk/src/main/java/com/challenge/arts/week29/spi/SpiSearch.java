package com.challenge.arts.week29.spi;

import java.util.List;

/**
 * 搜索接口
 * @author liuzwei
 */
public interface SpiSearch {

    /**
     * 根据关键字搜索文档
     * @param keyWords
     * @return
     */
    List<String> searchDocs(String keyWords);
}
