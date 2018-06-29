package cz.jcu.uai.knihovna;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class FXMLController implements Initializable {
    
  @FXML
  private ChoiceBox<String> toggle;
  @FXML
  private TextField vyhledavaciPole;
  @FXML
  private TableView<Predmet> tabulka;
  @FXML
  private TableColumn<Predmet, String> slpcAutor;
  @FXML
  private TableColumn<Predmet, String> slpcNazev;
  @FXML
  private TableColumn<Predmet, String> slpcPocet;
  @FXML
  private Button pridatBttn;
    
  Knihovna knihovna;
  FilteredList<Predmet> predmetyFL;
  ObservableList<Predmet> predmetyOL;
  Predicate<Predmet> predikat;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      knihovna = new Knihovna(new File("Katalog.ser"));
        toggle.getItems().addAll("Knihy", "Časopisy", "Vše");
        toggle.getSelectionModel().selectLast();
        predmetyOL = FXCollections.observableList(knihovna.getPredmety());
        predmetyFL = new FilteredList<>(predmetyOL, new Predicate<Predmet>() {
        @Override
        public boolean test(Predmet t) {
          if( toggle.getSelectionModel().getSelectedItem() != null
                  && (( toggle.getSelectionModel().getSelectedItem().equals("Knihy") 
                    && !t.getClass().getName().contains("Kniha") )
                  || ( toggle.getSelectionModel().getSelectedItem().equals("Časopisy")
                    && !t.getClass().getName().contains("Casopis")))) {
            return false;
          }
          if(vyhledavaciPole.getText().isEmpty()) {
            return true;
          }
          String lowerCaseFiltr = vyhledavaciPole.getText().toLowerCase();
          return t.asString().toLowerCase().contains(lowerCaseFiltr);
        }
      });
        nastavTabulku();
        vyhledavaciPole.textProperty().addListener(new ChangeListener<String>() {
          @Override
          public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
            predmetyFL.setPredicate(new Predicate<Predmet>() {
              @Override
              public boolean test(Predmet t) {
                if( toggle.getSelectionModel().getSelectedItem() != null
                        && (( toggle.getSelectionModel().getSelectedItem().equals("Knihy") 
                          && !t.getClass().getName().contains("Kniha") )
                        || ( toggle.getSelectionModel().getSelectedItem().equals("Časopisy")
                          && !t.getClass().getName().contains("Casopis")))) {
                  return false;
                }
                if(vyhledavaciPole.getText().isEmpty()) {
                  return true;
                }
                String lowerCaseFiltr = vyhledavaciPole.getText().toLowerCase();
                return t.asString().toLowerCase().contains(lowerCaseFiltr);
              }
            });
          }
        } );
        toggle.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            predmetyFL.setPredicate(new Predicate<Predmet>() {
              @Override
              public boolean test(Predmet t) {
                if( toggle.getSelectionModel().getSelectedItem() != null
                        && (( toggle.getSelectionModel().getSelectedItem().equals("Knihy") 
                          && !t.getClass().getName().contains("Kniha") )
                        || ( toggle.getSelectionModel().getSelectedItem().equals("Časopisy")
                          && !t.getClass().getName().contains("Casopis")))) {
                  return false;
                }
                if(vyhledavaciPole.getText().isEmpty()) {
                  return true;
                }
                String lowerCaseFiltr = vyhledavaciPole.getText().toLowerCase();
                return t.asString().toLowerCase().contains(lowerCaseFiltr);
              }
            });
          }
        });
            
        SortedList<Predmet> predmetySL = new SortedList<>(predmetyFL);
        predmetySL.comparatorProperty().bind(tabulka.comparatorProperty());
        
        tabulka.setItems(predmetySL);
        
        tabulka.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          Predmet vybrany = tabulka.getSelectionModel().getSelectedItem();
          if(vybrany != null){
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20,20,10,10));
            dialog.setTitle("Správa předmětu");
            dialog.setHeaderText(vybrany.getVice() + " - " + vybrany.getNazev());
            Button odebratBttn = new Button("Odebrat");
            Button vypujcitBttn = new Button("Vypůjčit");
            Button vratitBttn = new Button("Vrátit");
            odebratBttn.setOnAction(new OdebratHandler());
            vypujcitBttn.setOnAction(new PujcitHandler());
            vratitBttn.setOnAction(new VratitHandler());
            
            grid.add(odebratBttn, 0, 1);
            grid.add(vypujcitBttn, 0, 2);
            grid.add(vratitBttn, 0, 3);
            dialog.getDialogPane().setContent(grid);
            dialog.showAndWait();
          }
        }
      });
    }

  private class VratitHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      Predmet vybrany = tabulka.getSelectionModel().getSelectedItem();
      if(vybrany != null && vybrany.getVypujckySize() != 0) {
        ChoiceDialog<Zakaznik> dialog = new ChoiceDialog<>(vybrany.getVypujcky().get(0), vybrany.getVypujcky());
        dialog.setTitle("Vrátit knihu");
        dialog.setHeaderText(vybrany.getVice() + " - " + vybrany.getNazev());
        dialog.setContentText("Kdo vrací?");
        Optional<Zakaznik> odpoved = dialog.showAndWait();
        if(odpoved.isPresent()){
          vybrany.vrat(odpoved.get());
          predmetyOL = FXCollections.observableList(knihovna.getPredmety());
        }
      }
    }

  }
  
  private class OdebratHandler implements EventHandler<ActionEvent>{
      @Override
      public void handle(ActionEvent event) {
        Predmet vybrany = tabulka.getSelectionModel().getSelectedItem();
        if(vybrany != null){
          TextInputDialog tid = new TextInputDialog();
          tid.setTitle("Odebrat předmět");
          tid.setHeaderText(vybrany.getVice() + " - " + vybrany.getNazev() + " || " + (vybrany.getPocet() - vybrany.getVypujckySize()) + "/" + vybrany.getPocet());
          tid.setContentText("Kolik odebrat?");
          Optional<String> odpoved = tid.showAndWait();
          if(odpoved.isPresent()){
            if(vybrany.getPocet() - vybrany.getVypujckySize() < Integer.valueOf(odpoved.get())){
              Alert test = new Alert(Alert.AlertType.CONFIRMATION);
              test.setContentText("Nelze odebrat, jsou ještě vypůjčeny");
              test.showAndWait();
            } else {
              vybrany.setPocet(vybrany.getPocet()-(Integer.valueOf(odpoved.get())));
              if(vybrany.getPocet() == 0){
                predmetyOL.remove(vybrany);
              }
            }
          }
        }
      }
    }
    
    private class PujcitHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
        final Predmet vybrany = tabulka.getSelectionModel().getSelectedItem();
        if(vybrany != null) {
          final Dialog<ButtonType> dialog = new Dialog<>();
          dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
          GridPane grid = new GridPane();
          grid.setHgap(10);
          grid.setVgap(10);
          grid.setPadding(new Insets(20,20,10,10));
          dialog.setTitle("Vypůjčit");
          final TextField jmenoField = new TextField();
          jmenoField.setPromptText("Jméno");
          final DatePicker vypujckaOdField = new DatePicker(LocalDate.now());
          grid.add(new Label("Jméno:"), 0, 0);
          grid.add(         jmenoField, 1, 0);
          grid.add(   new Label("Od:"), 0, 1);
          grid.add(    vypujckaOdField, 1, 1);
          dialog.setOnHidden(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
              if(dialog.getResult().equals(ButtonType.OK)){
                if(!jmenoField.getText().isEmpty()){
                  Zakaznik zak = new Zakaznik(jmenoField.getText(), vypujckaOdField.getValue());
                  knihovna.vypujcit(vybrany, zak);
                }
              }
            }
          });
          dialog.getDialogPane().setContent(grid);
          dialog.showAndWait();
        }
      }
      
    }

        //ChoiceDialog dialog = new ChoiceDialog(vybrany.getVypujcky().get(0), vybrany.getVypujcky());
            
  @FXML
  private void pridejPredmet(final ActionEvent event) {
    final Dialog<ButtonType> dialog = new Dialog<>();
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20,20,10,10));
    if(toggle.getSelectionModel().getSelectedItem().equals("Knihy") || toggle.getSelectionModel().getSelectedItem().equals("Vše")){
      dialog.setTitle("Přidat knihu");
      final TextField autor = new TextField();
      autor.setPromptText("autor");
      final TextField nazev = new TextField();
      nazev.setPromptText("název");
      final TextField pocet = new TextField();
      pocet.setPromptText("počet");
      grid.add(new Label("Autor"), 0, 0);
      grid.add(             autor, 1, 0);
      grid.add(new Label("Název"), 0, 1);
      grid.add(             nazev, 1, 1);
      grid.add(new Label("Počet"), 0, 2);
      grid.add(             pocet, 1, 2);
      // Při vyplnění formuláře a stisknutí tlačítka
      dialog.setOnHidden(new EventHandler<DialogEvent>() {
        @Override
        public void handle(DialogEvent dEvent) {
          // kontrola jestli bylo stisknuto OK
          if(dialog.getResult().equals(ButtonType.OK)){
            // kontrola zda byly vyplněny všechny pole formuláře
            if(autor.getText().isEmpty() || 
                nazev.getText().isEmpty() ||
                pocet.getText().isEmpty()) {
              // nastala chyba: objeví se vyskakovací okno
              Alert test = new Alert(Alert.AlertType.CONFIRMATION);
              test.setContentText("Špatně vyplněné položky");
              test.showAndWait();
              // a formulář se spustí znova
              pridejPredmet(event);
            } else {
              // pokud je vše v pořádku, kniha se uloží
              predmetyOL.add(new Kniha(autor.getText(), nazev.getText(), Integer.valueOf(pocet.getText())));
            }
          } // else {} -> stisknuto Cancel/křížek, není třeba nic dělat
        }
      });
    // pokud je vybrán časopis
    } else {
      dialog.setTitle("Přidat časopis");
      // vyplnění vyskak. okna
      final TextField nazev = new TextField();
      nazev.setPromptText("název");
      final TextField vydaniMesic = new TextField();
      vydaniMesic.setPromptText("měsíc");
      final TextField vydaniRok = new TextField();
      vydaniRok.setPromptText("rok");
      final TextField pocet = new TextField();
      pocet.setPromptText("počet");
      grid.add(       new Label("Název"), 0, 0);
      grid.add(                    nazev, 1, 0);
      grid.add(  new Label("Rok vydání"), 0, 1);
      grid.add(                vydaniRok, 1, 1);
      grid.add(new Label("Měsíc vydání"), 0, 2);
      grid.add(              vydaniMesic, 1, 2);
      grid.add(       new Label("Počet"), 0, 3);
      grid.add(                    pocet, 1, 3);
      //Při vyplnění formuláře a stisknutí tlačítka
      dialog.setOnHidden( new EventHandler<DialogEvent>() {
        @Override
        public void handle(DialogEvent dEvent) {
          // kontrola zda bylo stisknuto OK
          if(dialog.getResult().equals(ButtonType.OK)){
            // zda byla vyplněna všechna pole
            if(nazev.getText().isEmpty() || 
                  vydaniRok.getText().isEmpty() ||
                  vydaniMesic.getText().isEmpty() ||
                  pocet.getText().isEmpty()) {
              // nastala chyba... buďto nevyplněno nebo špatně
              // -> oznamovací okno, spustit znovu
              Alert test = new Alert(Alert.AlertType.CONFIRMATION);
              test.setContentText("Špatně vyplněné položky");
              test.showAndWait();
              pridejPredmet(event);
            } else {
              // pokud je vše v pořádku, uložit časopis
              predmetyOL.add(new Casopis(nazev.getText(),
                      Integer.valueOf(vydaniRok.getText()),
                      Integer.valueOf(vydaniMesic.getText()),
                      Integer.valueOf(pocet.getText())));
            }
          } // else {} // stisknuto Cancel/křížek -> nic
        }
      });
    }
    dialog.getDialogPane().setContent(grid);
    dialog.showAndWait();
    
  }

  private void nastavTabulku() {
    slpcAutor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> param) {
        return new ReadOnlyStringWrapper(param.getValue().getVice());
      }
    });
    slpcNazev.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> param) {
        return new ReadOnlyStringWrapper(param.getValue().getNazev());
      }
    });
    slpcPocet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
      @Override
      public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> param) {
        return new ReadOnlyStringWrapper(""
                + (param.getValue().getPocet() - param.getValue().getVypujckySize())
                + " / "
                + param.getValue().getPocet());
      }
    });
  }

  public void priZavreni() {
    knihovna.ulozPredmety();
  }
    
    
}
