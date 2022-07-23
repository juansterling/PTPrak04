package com.example.p_pt04_2072009;

import java.io.IOException;

import com.example.p_pt04_2072009.Dao.CategoryDao;
import com.example.p_pt04_2072009.Dao.MenuDao;
import com.example.p_pt04_2072009.Model.Category;
import com.example.p_pt04_2072009.Model.Menu;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
public class MenuController {
    public TextField idmenu;
    public TextField namamenu;
    public TextField harga;
    public TextArea desc;
    public ComboBox<Category> isicategory;
    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView<Menu> tabelmenu;
    public TableColumn<String, Menu> colid;
    public TableColumn<String, Menu> colnama;
    public TableColumn<Float, Menu> colharga;
    public TableColumn<Category, Menu> colcategory;
    public MenuItem showcat;
    public MenuItem close;
    private Stage stage;
    ObservableList<Menu> listmenu;
    ObservableList<Category> listcat;

    public MenuController() {
    }

    public void initialize() throws IOException {
        this.stage = new Stage();
        this.showcat.setAccelerator(KeyCombination.keyCombination("Alt+F2"));
        this.close.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        CategoryDao cDao = new CategoryDao();
        this.listcat = cDao.getData();
        this.isicategory.setItems(this.listcat);
        this.ShowData();
    }

    public void showcat(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Category-View.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 600.0, 300.0);
        CategoryController ctgController = (CategoryController)fxmlLoader.getController();
        this.stage.setTitle("Category Management");
        this.stage.setScene(scene);
        this.stage.showAndWait();
    }

    public void close(ActionEvent actionEvent) {
        this.idmenu.getScene().getWindow().hide();
    }

    public void ShowData() {
        MenuDao dao = new MenuDao();
        this.listmenu = dao.getData();
        this.tabelmenu.setItems(this.listmenu);
        this.colid.setCellValueFactory(new PropertyValueFactory("id"));
        this.colnama.setCellValueFactory(new PropertyValueFactory("name"));
        this.colharga.setCellValueFactory(new PropertyValueFactory("price"));
        this.colcategory.setCellValueFactory(new PropertyValueFactory("category"));
        this.btnUpdate.setDisable(true);
        this.btnDelete.setDisable(true);
    }

    public void savebtn(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        if (this.idmenu.getText() != null && this.namamenu.getText() != null && this.harga.getText() != null && this.desc.getText() != null && this.isicategory.getValue() != null) {
            dao.addData(new Menu(Integer.parseInt(this.idmenu.getText()), this.namamenu.getText(), this.desc.getText(),Double.parseDouble(this.harga.getText()), (Category)this.isicategory.getValue()));
            this.ShowData();
            this.resetbtn();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Please Fill all the field", new ButtonType[]{ButtonType.OK});
            alert.showAndWait();
        }

    }
    public void klikcategory() {
        CategoryDao cDao = new CategoryDao();
        this.listcat = cDao.getData();
        this.isicategory.setItems(this.listcat);
    }
    public void resetbtn() {
        this.idmenu.clear();
        this.namamenu.clear();
        this.harga.clear();
        this.desc.clear();
        this.isicategory.getSelectionModel().select(-1);
    }
    public void updatebtn(ActionEvent actionEvent) {
    }

    public void deletebtn(ActionEvent actionEvent) {
    }

}
