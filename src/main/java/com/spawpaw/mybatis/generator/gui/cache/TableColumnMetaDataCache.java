package com.spawpaw.mybatis.generator.gui.cache;

import com.spawpaw.mybatis.generator.gui.entity.TableColumnMetaData;

import java.util.HashMap;
import java.util.Map;

/**
 * key should be combined with database name and table name and column name<br/>
 * like xxdb.xxtable:xxcolumn
 */
public class TableColumnMetaDataCache {

    private static Map<String, TableColumnMetaData> cache = new HashMap<>();

    public static void put(String key, TableColumnMetaData data) {
        cache.put(key, data);
    }

    public static TableColumnMetaData get(String key) {
        return cache.get(key);
    }

    /**
     * 是否是可查询的字段，生成页面的时候可以按需生成查询的表单
     * @param key
     * @return
     */
    public static Boolean isSearchedColumn(String key) {
        TableColumnMetaData data = get(key);
        if (data == null) {
            return Boolean.FALSE;
        }
        return data.getSearched();
    }

    /**
     * 是否是必填字段，生成页面的时候可以按需生成必填的校验逻辑
     * @param key
     * @return
     */
    public static Boolean isRequiredColumn(String key) {
        TableColumnMetaData data = get(key);
        if (data == null) {
            return Boolean.FALSE;
        }
        return data.getRequired();
    }

}
