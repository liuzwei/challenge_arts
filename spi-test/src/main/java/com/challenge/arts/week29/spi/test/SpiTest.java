package com.challenge.arts.week29.spi.test;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.challenge.arts.week29.spi.SpiSearch;

/**
 * @author liuzhaowei
 * @date 2022/7/15 10:36
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<SpiSearch> searches = ServiceLoader.load(SpiSearch.class);

        Iterator<SpiSearch> iterator = searches.iterator();
        while (iterator.hasNext()){
            SpiSearch search = iterator.next();
            search.searchDocs("Java");
        }
    }
}
