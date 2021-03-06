package com.spawpaw.mybatis.generator.gui.entity;

import com.spawpaw.mybatis.generator.gui.cache.TableColumnMetaDataCache;
import com.spawpaw.mybatis.generator.gui.util.ExampleUtil;
import com.spawpaw.mybatis.generator.gui.util.JavaBeansUtil;
import com.spawpaw.mybatis.generator.gui.util.RegexpUtil;
import com.spawpaw.mybatis.generator.gui.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.config.Context;

import java.util.*;

/**
 * Created By spawpaw@hotmail.com  2018-03-22
 * this class holds the basic info for a column.
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class Column extends ConfigMatcher {

    private static List<String> whoColumnList = new ArrayList<>();
    static {
        whoColumnList.add("created_by");
        whoColumnList.add("create_by");
        whoColumnList.add("updated_by");
        whoColumnList.add("update_by");
        whoColumnList.add("createdBy");
        whoColumnList.add("createBy");
        whoColumnList.add("updatedBy");
        whoColumnList.add("updateBy");
        whoColumnList.add("created_time");
        whoColumnList.add("create_time");
        whoColumnList.add("updated_time");
        whoColumnList.add("update_time");
        whoColumnList.add("createdTime");
        whoColumnList.add("createTime");
        whoColumnList.add("updatedTime");
        whoColumnList.add("updateTime");
        whoColumnList.add("created_date");
        whoColumnList.add("create_date");
        whoColumnList.add("updated_date");
        whoColumnList.add("update_date");
        whoColumnList.add("createdDate");
        whoColumnList.add("createDate");
        whoColumnList.add("updatedDate");
        whoColumnList.add("updateDate");
    }


    public final String fullTableName;// add by jason 所属的数据库和表名（db.table）
    public final String globalColumnCacheKey;// add by jason 全局唯一定位某个数据库的某个表的某个字段信息
    public final String actualName;//真实列名称db.table
    public final String fieldName;//该字段entity中的变量名称
    public final String fieldNameUpperCamel;
    public final String fieldType;//该字段的类型
    // add by jason start
    public String remarks; // 字段备注内容
    public final String searched; // 是否是可查询字段
    public final String required;  // 是否是必填字段
    public final String showInList; // 是否显示在列表页面
    public final String showInAdd; //是否显示在添加页面
    public final String editable; //是否是可编辑的字段
    public final String pageType;
    public final String whoColumn;// 是否是who column
    public Set<String> valueSet = new LinkedHashSet<>();
    public String valueSetString;
    // add by jason end
    public final String getterName;//该字段在entity中的getter名称
    public final String setterName;//该字段在entity中的setter名称

    //以下为该字段在Example类中的方法名
    public final String betweenMethod;
    public final String notBetweenMethod;
    public final String equalMethod;
    public final String greaterThanMethod;
    public final String greaterThenOrEqualMethod;
    public final String inMethod;
    public final String notInMethod;
    public final String lessThanMethod;
    public final String lessThanOrEqualMethod;
    public final String likeMethod;
    public final String notEqualMethod;
    public final String notLikeMethod;
    public final String notNullMethod;
    public final String nullMethod;
    //基本属性
    int index;//该列的显示顺序
    boolean disable = true;//当没有配置该字段时，默认为true，当配置该字段时，默认为false


    public Column(Context context, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Map<String, String> parent) {
        super(introspectedColumn.getRemarks(), parent);
        fullTableName =
                introspectedTable.getFullyQualifiedTable().getIntrospectedCatalog()
                        + "."
                        + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();// add by jason
        Field field = JavaBeansUtil.getJavaBeansField(introspectedColumn, context, introspectedTable);

        fieldName = field.getName();
        whoColumn = String.valueOf(whoColumnList.contains(fieldName));
        fieldNameUpperCamel = Utils.getUpperCamelCase(fieldName);
        fieldType = field.getType().getFullyQualifiedName();
        actualName = introspectedColumn.getActualColumnName();
        globalColumnCacheKey = fullTableName + ":" + actualName;
        searched = TableColumnMetaDataCache.isSearchedColumn(globalColumnCacheKey).toString();
        required = TableColumnMetaDataCache.isRequiredColumn(globalColumnCacheKey).toString();
        editable = TableColumnMetaDataCache.isEditableColumn(globalColumnCacheKey).toString();
        showInList = TableColumnMetaDataCache.needShowInList(globalColumnCacheKey).toString();
        showInAdd = TableColumnMetaDataCache.needShowInAdd(globalColumnCacheKey).toString();
        pageType = TableColumnMetaDataCache.getPageType(globalColumnCacheKey);
        remarks = introspectedColumn.getRemarks();
        if ("radio".equals(pageType) || "select".equals(pageType)
                || "select-dict".equals(pageType) || "checkbox".equals(pageType)
                || "radio-dict".equals(pageType) || "checkbox-dict".equals(pageType)) {
            remarks = RegexpUtil.parseValueSet(getRemarks(), valueSet);
        }
        getterName = JavaBeansUtil.getGetterMethodName(field.getName(), field.getType());
        setterName = JavaBeansUtil.getSetterMethodName(field.getName());

        this.betweenMethod = ExampleUtil.getSetBetweenOrNotBetweenMethod(introspectedColumn, true).getName();
        this.notBetweenMethod = ExampleUtil.getSetBetweenOrNotBetweenMethod(introspectedColumn, false).getName();
        this.equalMethod = ExampleUtil.getSetEqualMethod(introspectedColumn).getName();
        this.greaterThanMethod = ExampleUtil.getSetGreaterThanMethod(introspectedColumn).getName();
        this.greaterThenOrEqualMethod = ExampleUtil.getSetGreaterThenOrEqualMethod(introspectedColumn).getName();
        this.inMethod = ExampleUtil.getSetInOrNotInMethod(introspectedColumn, true).getName();
        this.notInMethod = ExampleUtil.getSetInOrNotInMethod(introspectedColumn, false).getName();
        this.lessThanMethod = ExampleUtil.getSetLessThanMethod(introspectedColumn).getName();
        this.lessThanOrEqualMethod = ExampleUtil.getSetLessThanOrEqualMethod(introspectedColumn).getName();
        this.likeMethod = ExampleUtil.getSetLikeMethod(introspectedColumn).getName();
        this.notEqualMethod = ExampleUtil.getSetNotEqualMethod(introspectedColumn).getName();
        this.notLikeMethod = ExampleUtil.getSetNotLikeMethod(introspectedColumn).getName();
        this.notNullMethod = ExampleUtil.getSetNotNullMethod(introspectedColumn).getName();
        this.nullMethod = ExampleUtil.getSetNullMethod(introspectedColumn).getName();
        //获取ddl
        String ddl = RegexpUtil.findMatches(
                "(?:`" + actualName + "` *)"
                , "(.*)"
                , "(?:\n)"
                , parent.get("ddl")).replaceAll(",$", "");//去除末尾逗号
        this.put("ddl", ddl);
        if (contains("index"))
            index = Integer.valueOf(get("index"));
        if (contains("disable"))
            disable = Boolean.valueOf(get("disable"));
    }

    @Override
    public boolean contains(String key) {
        System.err.println("key:" + key + "          " + (super.contains(key) || super.contains(actualName + "." + key)));
        return super.contains(key) || super.contains(actualName + "." + key);
    }

    @Override
    public String get(String key) {
        System.err.println("getkey:" + key + "          " + (super.contains(key) || super.contains(actualName + "." + key)));
        return containsKey(key) ? super.get(key) : super.get(actualName + "." + key);
    }

    public int getIndex() {
        return index;
    }

    public boolean isDisable() {
        return disable;
    }

    public String getActualName() {
        return actualName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldNameUpperCamel() {
        return fieldNameUpperCamel;
    }

    public String getRemarks() {
        if (StringUtils.isBlank(remarks)) {
            return fieldName;
        }
        return remarks;
    }

    public String getPageType() {
        if (StringUtils.isBlank(pageType)) {
            return "text";
        }
        return pageType;
    }

    public String getValueSetString() {
        StringBuilder sb = new StringBuilder();
        if (valueSet != null) {
            valueSet.stream().forEach(val -> {
                sb.append(val).append(",");
            });
        }
        String result = sb.substring(0, sb.length() - 1);
        return result;
    }

    public String getShowInList() {
        return showInList;
    }

    public String getShowInAdd() {
        return showInAdd;
    }

    public String getEditable() {
        return editable;
    }

    public String getSearched(){
        return searched;
    }

    public String getRequired() {
        return required;
    }

    public String getWhoColumn() {
        return whoColumn;
    }

    public Set<String> getValueSet() {
        return valueSet;
    }

    public String getGetterName() {
        return getterName;
    }

    public String getSetterName() {
        return setterName;
    }

    public String getBetweenMethod() {
        return betweenMethod;
    }

    public String getNotBetweenMethod() {
        return notBetweenMethod;
    }

    public String getEqualMethod() {
        return equalMethod;
    }

    public String getGreaterThanMethod() {
        return greaterThanMethod;
    }

    public String getGreaterThenOrEqualMethod() {
        return greaterThenOrEqualMethod;
    }

    public String getInMethod() {
        return inMethod;
    }

    public String getNotInMethod() {
        return notInMethod;
    }

    public String getLessThanMethod() {
        return lessThanMethod;
    }

    public String getLessThanOrEqualMethod() {
        return lessThanOrEqualMethod;
    }

    public String getLikeMethod() {
        return likeMethod;
    }

    public String getNotEqualMethod() {
        return notEqualMethod;
    }

    public String getNotLikeMethod() {
        return notLikeMethod;
    }

    public String getNotNullMethod() {
        return notNullMethod;
    }

    public String getNullMethod() {
        return nullMethod;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
