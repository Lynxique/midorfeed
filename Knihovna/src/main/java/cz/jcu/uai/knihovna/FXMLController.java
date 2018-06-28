package cz.jcu.uai.knihovna;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private ChoiceBox<?> toggle;
  @FXML
  private TextField vyhledavaciPole;
  @FXML
  private TableView<?> tabulka;
  @FXML
  private TableColumn<?, ?> slpcAutor;
  @FXML
  private TableColumn<?, ?> slpcNazev;
  @FXML
  private TableColumn<?, ?> slpcPocet;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
