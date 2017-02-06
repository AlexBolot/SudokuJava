import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/*..................................................................................................
 . Copyright (c)
 .
 . Code créé par :
 . -> Alexandre BOLOT
 . -> Victor MONSCH
 . -> Christopher SABOYA
 . -> Laurent MICHELET
 .................................................................................................*/

public class GrilleController
{
    
    //region GridPane
    
    @FXML
    private GridPane GPSudoku;
    
    @FXML
    private ChoiceBox ddlChoix;
    
    //endregion
    
    @FXML
    public void initialize () throws IOException
    {
        ArrayList listeChoix = new ArrayList();
        
        for (Choix c : Choix.values())
        {
            listeChoix.add(c);
            listeChoix.add(new Separator());
        }
        
        listeChoix.remove(listeChoix.size() - 1);
        
        ddlChoix.setItems(FXCollections.observableArrayList(listeChoix));
    }
    
    @FXML
    void btnChoisir_onAction (ActionEvent event) throws IOException
    {
        GRILLE grille = new GRILLE(getDonnees());
        
        switch ((Choix) ddlChoix.getValue())
        {
            //region Enregistrer
            case Enregistrer:
                grille.SetOrigin();
                grille.Save();
                break;
            //endregion
            
            //region Vider
            case Vider:
                for (Node node : GPSudoku.getChildren())
                {
                    if(node instanceof TextField)
                    {
                        ((TextField) node).setText("");
                        node.setStyle("-fx-background-color:white");
                    }
                }
                
                grille.Vider();
                
                break;
            //endregion
            
            //region Loader
            case Loader:
                grille.Load();
                
                for (CASE c : grille.listeCases)
                {
                    Node node = getNodeFromGridPane(GPSudoku, c.colonne, c.ligne);
                    
                    if(node instanceof TextField)
                    {
                        if(c.val != 0)
                        {
                            ((TextField) node).setText(Integer.toString(c.val));
                            node.setStyle("-fx-background-color: CornflowerBlue;");
                        }
                        else
                        {
                            ((TextField) node).setText("");
                            node.setStyle("-fx-background-color: White;");
                        }
                    }
                }
                break;
            //endregion
            
            //region Resoudre
            case Resoudre:
                Long t0 = System.currentTimeMillis();
                
                grille.Solve();
                
                Long t1 = System.currentTimeMillis();
                
                System.out.println(t1 - t0 + "\n");
                
                for (CASE c : grille.listeCases)
                {
                    Node node = getNodeFromGridPane(GPSudoku, c.colonne, c.ligne);
                    
                    if(node instanceof TextField)
                    {
                        ((TextField) node).setText(Integer.toString(c.val));
                    }
                }
                break;
            //endregion
            
            //region Generer
            case Generer:
                for (Node node : GPSudoku.getChildren())
                {
                    if(node instanceof TextField)
                    {
                        ((TextField) node).setText("");
                        node.setStyle("-fx-background-color:white");
                    }
                }
                
                grille.Vider();
                
                grille = new GRILLE(getDonnees());
                
                grille.Solve();
                
                int nombreDeCaseGardees = 25;
                
                for (int f = 0; f < nombreDeCaseGardees; f++)
                {
                    while (true)
                    {
                        int lig = ThreadLocalRandom.current().nextInt(0, 8 + 1);
                        int col = ThreadLocalRandom.current().nextInt(0, 8 + 1);
                        
                        CASE c = grille.getCase(lig, col);
                        if(!c.origin)
                        {
                            c.origin = true;
                            break;
                        }
                    }
                }
                
                grille.SetOrigin();
                
                for (CASE c : grille.listeCases)
                {
                    Node node = getNodeFromGridPane(GPSudoku, c.colonne, c.ligne);
                    
                    if(node instanceof TextField)
                    {
                        if(c.val != 0)
                        {
                            ((TextField) node).setText(Integer.toString(c.val));
                            node.setStyle("-fx-background-color: CornflowerBlue;");
                        }
                        else
                        {
                            ((TextField) node).setText("");
                            node.setStyle("-fx-background-color: White;");
                        }
                    }
                }
                break;
            //endregion
        }
    }
    
    private Node getNodeFromGridPane (GridPane gridPane, int col, int row)
    {
        for (Node node : gridPane.getChildren())
        {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row)
            {
                return node;
            }
        }
        return null;
    }
    
    private ArrayList<CASE> getDonnees ()
    {
        ArrayList<CASE> listeCases = new ArrayList<>();
        
        Integer i = 0;
        Integer v;
        Integer col;
        Integer ligne;
        
        ArrayList<Integer> listeTemplate = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        
        for (Node node : GPSudoku.getChildren())
        {
            if(node instanceof TextField)
            {
                try
                {
                    v = Integer.parseInt(((TextField) node).getText());
                }
                catch (NumberFormatException nfe)
                {
                    v = 0;
                }
                
                col = (i % 9);
                ligne = (i / 9);
                Collections.shuffle(listeTemplate);
                
                CASE c = new CASE(col, ligne, v, listeTemplate);
                
                if(v != 0)
                {
                    node.setStyle("-fx-background-color: CornflowerBlue;");
                }
                else
                {
                    node.setStyle("-fx-background-color: White;");
                }
                
                listeCases.add(c);
                
                i++;
            }
        }
        
        return listeCases;
    }
}