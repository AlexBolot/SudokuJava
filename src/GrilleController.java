import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class GrilleController
{
    
    //region List TextFields + GridPane
    
    @FXML
    private TextField c31;
    
    @FXML
    private TextField c75;
    
    @FXML
    private TextField c30;
    
    @FXML
    private TextField c74;
    
    @FXML
    private TextField c33;
    
    @FXML
    private TextField c77;
    
    @FXML
    private TextField c32;
    
    @FXML
    private TextField c76;
    
    @FXML
    private TextField c35;
    
    @FXML
    private TextField c34;
    
    @FXML
    private TextField c78;
    
    @FXML
    private TextField c37;
    
    @FXML
    private TextField c36;
    
    @FXML
    private TextField c38;
    
    @FXML
    private TextField c80;
    
    @FXML
    private TextField c82;
    
    @FXML
    private TextField c81;
    
    @FXML
    private TextField c40;
    
    @FXML
    private TextField c84;
    
    @FXML
    private TextField c83;
    
    @FXML
    private TextField c42;
    
    @FXML
    private TextField c86;
    
    @FXML
    private TextField c41;
    
    @FXML
    private TextField c85;
    
    @FXML
    private TextField c00;
    
    @FXML
    private TextField c44;
    
    @FXML
    private TextField c88;
    
    @FXML
    private TextField c43;
    
    @FXML
    private TextField c87;
    
    @FXML
    private TextField c02;
    
    @FXML
    private TextField c46;
    
    @FXML
    private TextField c01;
    
    @FXML
    private TextField c45;
    
    @FXML
    private TextField c04;
    
    @FXML
    private TextField c48;
    
    @FXML
    private TextField c03;
    
    @FXML
    private TextField c47;
    
    @FXML
    private TextField c06;
    
    @FXML
    private TextField c05;
    
    @FXML
    private TextField c08;
    
    @FXML
    private TextField c07;
    
    @FXML
    private TextField c51;
    
    @FXML
    private TextField c50;
    
    @FXML
    private TextField c53;
    
    @FXML
    private TextField c52;
    
    @FXML
    private TextField c11;
    
    @FXML
    private TextField c55;
    
    @FXML
    private TextField c10;
    
    @FXML
    private TextField c54;
    
    @FXML
    private TextField c13;
    
    @FXML
    private TextField c57;
    
    @FXML
    private TextField c12;
    
    @FXML
    private TextField c56;
    
    @FXML
    private TextField c15;
    
    @FXML
    private TextField c14;
    
    @FXML
    private TextField c58;
    
    @FXML
    private GridPane GPSudoku;
    
    @FXML
    private TextField c17;
    
    @FXML
    private TextField c16;
    
    @FXML
    private TextField c18;
    
    @FXML
    private TextField c60;
    
    @FXML
    private TextField c62;
    
    @FXML
    private TextField c61;
    
    @FXML
    private TextField c20;
    
    @FXML
    private TextField c64;
    
    @FXML
    private TextField c63;
    
    @FXML
    private TextField c22;
    
    @FXML
    private TextField c66;
    
    @FXML
    private TextField c21;
    
    @FXML
    private TextField c65;
    
    @FXML
    private TextField c24;
    
    @FXML
    private TextField c68;
    
    @FXML
    private TextField c23;
    
    @FXML
    private TextField c67;
    
    @FXML
    private TextField c26;
    
    @FXML
    private TextField c25;
    
    @FXML
    private TextField c28;
    
    @FXML
    private TextField c27;
    
    @FXML
    private Button btnChoisir;
    
    @FXML
    private ChoiceBox ddlChoix;
    
    @FXML
    private TextField c71;
    
    @FXML
    private TextField c70;
    
    @FXML
    private TextField c73;
    
    @FXML
    private TextField c72;
    
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
                
                //String csvTotal = "";
                
                for (int i = 0; i < 50; i++)
                {
                    //String csv = "";
    
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
                                
                                //csv += "," + c.val;
                                
                            }
                            else
                            {
                                ((TextField) node).setText("");
                                node.setStyle("-fx-background-color: White;");
                                
                                //csv += ",0";
                                
                            }
                        }
                    }
                    
                    //csvTotal += csv.substring(1) + "\n";
                    
                }
                
                //SerializationClass.save(csvTotal);
                
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
    
    private void PrintConsole (GRILLE grille)
    {
        for (CASE c : grille.listeCases)
        {
            String s = "";
            
            if(c.colonne == 2 || c.colonne == 5)
            {
                s += (c.val + " ");
            }
            else if(c.colonne == 8 && (c.ligne == 2 || c.ligne == 5))
            {
                s += (c.val + "\n\n");
            }
            else if(c.colonne == 8)
            {
                s += (c.val + "\n");
            }
            else
            {
                s += c.val;
            }
            
            System.out.print(s);
        }
    }
}