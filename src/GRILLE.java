import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class GRILLE implements Serializable
{
    ArrayList<CASE> listeCases;
    
    GRILLE ()
    {
        listeCases = new ArrayList<>();
    }
    
    GRILLE (ArrayList<CASE> liste)
    {
        listeCases = liste;
    }
    
    void Save () throws IOException
    {
        System.out.println("Saving...");
        
        String s = "SavedGrid.txt";
        
        FileOutputStream fos = new FileOutputStream(s);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        
        try
        {
            oos.writeObject(this);
            System.out.println("...Saved");
        }
        catch (IOException ioe)
        {
            System.out.println("...not Saved : " + ioe.getMessage());
        }
        
        oos.close();
    }
    
    void Load () throws IOException
    {
        System.out.println("Loading...");
        
        String s = "SavedGrid.txt";
        
        FileInputStream fis = new FileInputStream(s);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        
        try
        {
            GRILLE g = (GRILLE) ois.readObject();
            
            this.listeCases = g.listeCases;
            System.out.println("...Loaded");
        }
        catch (Exception e)
        {
            IOException ex = (IOException) e;
            System.out.println("...not Loaded : " + ex.getMessage());
        }
        
        ois.close();
    }
    
    void Solve ()
    {
        System.out.println("Solving...");
        
        int i = 0;
        
        while (i < 81)
        {
            if(listeCases.get(i).val == 0 && !listeCases.get(i).origin)
            {
                listeCases.get(i).listePossibilitees = (ArrayList<Integer>) listeCases.get(i).listeTemplate.clone();
                
                for (CASE c : getLigne(listeCases, listeCases.get(i)))
                {
                    Collections.replaceAll(listeCases.get(i).listePossibilitees, c.val, 0);
                }
                
                for (CASE c : getColonne(listeCases, listeCases.get(i)))
                {
                    Collections.replaceAll(listeCases.get(i).listePossibilitees, c.val, 0);
                }
                
                for (CASE c : getBlock(listeCases, listeCases.get(i)))
                {
                    Collections.replaceAll(listeCases.get(i).listePossibilitees, c.val, 0);
                }
                
                listeCases.get(i).refreshlistePossibilitees();
                
                listeCases.get(i).selectedValue = 0;
                
                if(listeCases.get(i).listePossibilitees.size() > 0)
                {
                    listeCases.get(i).val = listeCases.get(i).listePossibilitees.get(listeCases.get(i).selectedValue);
                }
                else
                {
                    i--;
                    while (listeCases.get(i).listePossibilitees.size() == 1 || listeCases.get(i).listePossibilitees.size() - 1 == listeCases.get(
                            i).selectedValue)
                    {
                        if(!listeCases.get(i).origin)
                        {
                            listeCases.get(i).val = 0;
                        }
                        i--;
                    }
                    listeCases.get(i).val = listeCases.get(i).listePossibilitees.get(++listeCases.get(i).selectedValue);
                }
            }
            i++;
        }
        System.out.println("...Solved");
    }
    
    void SetOrigin ()
    {
        for (CASE c : listeCases)
        {
            if(!c.origin)
            {
                c.val = 0;
            }
        }
    }
    
    void Vider ()
    {
        for (CASE c : listeCases)
        {
            c.val = 0;
        }
    }
    
    private ArrayList<CASE> getLigne (ArrayList<CASE> Grille, CASE Case)
    {
        ArrayList<CASE> Ligne = new ArrayList<>();
        
        for (CASE c : Grille)
        {
            if(c.ligne.equals(Case.ligne))
            {
                Ligne.add(c);
            }
        }
        
        return Ligne;
    }
    
    private ArrayList<CASE> getColonne (ArrayList<CASE> Grille, CASE Case)
    {
        ArrayList<CASE> Colonne = new ArrayList<>();
        
        for (CASE c : Grille)
        {
            if(c.colonne.equals(Case.colonne))
            {
                Colonne.add(c);
            }
        }
        
        return Colonne;
    }
    
    private ArrayList<CASE> getBlock (ArrayList<CASE> Grille, CASE Case)
    {
        ArrayList<CASE> Block = new ArrayList<>();
        
        for (CASE c : Grille)
        {
            if(c.block.equals(Case.block))
            {
                Block.add(c);
            }
        }
        
        return Block;
    }
    
    CASE getCase (int lig, int col)
    {
        for (CASE c : listeCases)
        {
            if(c.ligne.equals(lig) && c.colonne.equals(col))
            {
                return c;
            }
        }
        return null;
    }
    
}