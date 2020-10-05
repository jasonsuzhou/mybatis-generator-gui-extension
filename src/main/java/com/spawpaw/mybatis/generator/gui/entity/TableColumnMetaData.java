package com.spawpaw.mybatis.generator.gui.entity;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 表中字段的元信息
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TableColumnMetaData {

    private BooleanProperty checked = new SimpleBooleanProperty(true);

    private StringProperty columnName = new SimpleStringProperty();

    private StringProperty jdbcType = new SimpleStringProperty();

    private StringProperty javaType = new SimpleStringProperty();

    private StringProperty propertyName = new SimpleStringProperty();

    private StringProperty typeHandler = new SimpleStringProperty();

    // add by jason
    private BooleanProperty searched = new SimpleBooleanProperty(false);//是否是可查询字段
    private BooleanProperty required = new SimpleBooleanProperty(false);//是否是必填字段
    private BooleanProperty editable = new SimpleBooleanProperty(true);//是否是可编辑字段
    private BooleanProperty showInList = new SimpleBooleanProperty(true);//是否需要显示在列表页面
    private BooleanProperty showInAdd = new SimpleBooleanProperty(true);//是否需要显示在添加页面
    private StringProperty pageType = new SimpleStringProperty();

    private StringProperty remarks = new SimpleStringProperty();

    public boolean isShowInAdd() {
        return showInAdd.get();
    }

    public BooleanProperty showInAddProperty() {
        return showInAdd;
    }

    public void setShowInAdd(boolean showInAdd) {
        this.showInAdd.set(showInAdd);
    }

    public boolean isEditable() {
        return editable.get();
    }

    public BooleanProperty editableProperty() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable.set(editable);
    }

    public boolean isShowInList() {
        return showInList.get();
    }

    public BooleanProperty showInListProperty() {
        return showInList;
    }

    public void setShowInList(boolean showInList) {
        this.showInList.set(showInList);
    }

    public String getPageType() {
        return pageType.get();
    }

    public StringProperty pageTypeProperty() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType.set(pageType);
    }

    public boolean isRequired() {
        return required.get();
    }

    public BooleanProperty requiredProperty() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required.set(required);
    }

    public boolean isChecked() {
        return checked.get();
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    public boolean isSearched() {
        return searched.get();
    }

    public BooleanProperty searchedProperty() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched.set(searched);
    }

    public Boolean getShowInList() {
        return this.showInList.get();
    }

    public Boolean getShowInAdd() {
        return showInAdd.get();
    }

    public Boolean getEditable() {
        return this.editable.get();
    }

    public String getRemarks() {
        return remarks.get();
    }

    public StringProperty remarksProperty() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks.set(remarks);
    }

    public String getColumnName() {
        return columnName.get();
    }

    public void setColumnName(String columnName) {
        this.columnName.set(columnName);
    }

    public String getJdbcType() {
        return jdbcType.get();
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType.set(jdbcType);
    }

    public String getPropertyName() {
        return propertyName.get();
    }

    public void setPropertyName(String propertyName) {
        this.propertyName.set(propertyName);
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public Boolean getChecked() {
        return this.checked.get();
    }

    public Boolean getSearched() {
        return this.searched.get();
    }

    public Boolean getRequired() {
        return this.required.get();
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    public StringProperty typeHandlerProperty() {
        return typeHandler;
    }

    public String getTypeHandler() {
        return typeHandler.get();
    }

    public void setTypeHandler(String typeHandler) {
        this.typeHandler.set(typeHandler);
    }

    public StringProperty columnNameProperty() {
        return columnName;
    }

    public StringProperty jdbcTypeProperty() {
        return jdbcType;
    }

    public StringProperty propertyNameProperty() {
        return propertyName;
    }

    public String getJavaType() {
        return javaType.get();
    }

    public void setJavaType(String javaType) {
        this.javaType.set(javaType);
    }

    public StringProperty javaTypeProperty() {
        return javaType;
    }
}