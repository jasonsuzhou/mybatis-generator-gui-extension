package com.spawpaw.mybatis.generator.gui.controller;

import com.spawpaw.mybatis.generator.gui.entity.TableColumnMetaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By spawpaw@hotmail.com  2018-01-31
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class TableColumnEditorController extends BaseController implements Initializable {

    transient org.slf4j.Logger log = LoggerFactory.getLogger(BaseController.class);
    //    public TextField tf_filter;
    public TableView<TableColumnMetaData> table_view;
    public TableColumn<TableColumnMetaData, Boolean> c_checked;
    public TableColumn<TableColumnMetaData, Boolean> c_searched;
    public TableColumn<TableColumnMetaData, Boolean> c_required;
    public TableColumn<TableColumnMetaData, Boolean> c_editable;
    public TableColumn<TableColumnMetaData, String> c_page_type;
    public TableColumn<TableColumnMetaData, String> c_column_name;
    public TableColumn<TableColumnMetaData, String> c_jdbc_type;
    public TableColumn<TableColumnMetaData, String> c_java_type;
    public TableColumn<TableColumnMetaData, String> c_property_name;
    public TableColumn<TableColumnMetaData, String> c_type_handler;
    public TableColumn<TableColumnMetaData, String> c_remarks;
    public TableColumn<TableColumnMetaData, Boolean> c_show_in_list;
    public TableColumn<TableColumnMetaData, Boolean> c_show_in_add;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        c_checked.setCellValueFactory(new PropertyValueFactory<>("checked"));
        c_column_name.setCellValueFactory(new PropertyValueFactory<>("columnName"));
        c_jdbc_type.setCellValueFactory(new PropertyValueFactory<>("jdbcType"));
        c_java_type.setCellValueFactory(new PropertyValueFactory<>("javaType"));
        c_property_name.setCellValueFactory(new PropertyValueFactory<>("propertyName"));
        c_type_handler.setCellValueFactory(new PropertyValueFactory<>("typeHandler"));
        // add by jason
        c_searched.setCellValueFactory(new PropertyValueFactory<>("searched"));
        c_required.setCellValueFactory(new PropertyValueFactory<>("required"));
        c_editable.setCellValueFactory(new PropertyValueFactory<>("editable"));
        c_show_in_list.setCellValueFactory(new PropertyValueFactory("showInList"));
        c_show_in_add.setCellValueFactory(new PropertyValueFactory("showInAdd"));
        c_remarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        c_page_type.setCellValueFactory(new PropertyValueFactory<>("pageType"));

        c_checked.setCellFactory(CheckBoxTableCell.forTableColumn(c_checked));
        c_searched.setCellFactory(CheckBoxTableCell.forTableColumn(c_searched));
        c_required.setCellFactory(CheckBoxTableCell.forTableColumn(c_required));
        c_editable.setCellFactory(CheckBoxTableCell.forTableColumn(c_editable));
        c_show_in_list.setCellFactory(CheckBoxTableCell.forTableColumn(c_show_in_list));
        c_show_in_add.setCellFactory(CheckBoxTableCell.forTableColumn(c_show_in_add));
        ObservableList<String> pageTypeList =
                FXCollections.observableArrayList("text","radio","radio-dict","checkbox","select","select-search","select-dict","select-xm","img","textarea","textarea-wangEdit","number","email","password","date","datetime","color");
        c_page_type.setCellFactory(ChoiceBoxTableCell.forTableColumn(pageTypeList));
        c_java_type.setCellFactory(TextFieldTableCell.forTableColumn());
        c_property_name.setCellFactory(TextFieldTableCell.forTableColumn());
        c_type_handler.setCellFactory(TextFieldTableCell.forTableColumn());
        c_remarks.setCellFactory(TextFieldTableCell.forTableColumn());

        c_java_type.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setJavaType(event.getNewValue()));
        c_property_name.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setPropertyName(event.getNewValue()));
        c_type_handler.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setTypeHandler(event.getNewValue()));
    }

    public void on_btn_confirm_clicked(ActionEvent actionEvent) {
        tableColumnEditorStage.close();
    }

    public void setColumns(List<TableColumnMetaData> tableColumnMetaDataList) {
        table_view.setItems(FXCollections.observableList(tableColumnMetaDataList));
    }
}
