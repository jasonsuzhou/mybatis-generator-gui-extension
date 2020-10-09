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

    /**
     * 是否需要显示在列表页面的配置值
     * @param key
     * @return
     */
    public static Boolean needShowInList(String key) {
        TableColumnMetaData data = get(key);
        if (data == null) {
            return Boolean.FALSE;
        }
        return data.getShowInList();
    }

    /**
     * 是否需要显示在添加页面的配置值
     * @param key
     * @return
     */
    public static Boolean needShowInAdd(String key) {
        TableColumnMetaData data = get(key);
        if (data == null) {
            return Boolean.FALSE;
        }
        return data.getShowInAdd();
    }

    /**
     * 是否是可编辑的字段
     * @param key
     * @return
     */
    public static Boolean isEditableColumn(String key) {
        TableColumnMetaData data = get(key);
        if (data == null) {
            return Boolean.FALSE;
        }
        return data.getEditable();
    }

    /**
     * 页面元素类型,默认text类型
     * "text","radio","checkbox","select","select-search","select-dict","select-xm","number","email","password","date","datetime","color"
     * @param key
     * @return
     */
    public static String getPageType(String key) {
        TableColumnMetaData data = get(key);
        if (data == null) {
            return "text";
        }
        return data.getPageType();
    }

}
