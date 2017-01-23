import java.io.Serializable;
import java.util.ArrayList;

class CASE implements Serializable
{
    Integer colonne;
    Integer ligne;
    Integer val;
    Integer block;
    Boolean origin;
    Integer selectedValue;
    ArrayList<Integer> listeTemplate = new ArrayList();
    ArrayList<Integer> listePossibilitees = new ArrayList();
    
    String listTempl()
    {
        String s = "";
    
        for (Integer i : listeTemplate)
        {
            s += (i + ",");
        }
        
        return s;
    }
    String listPoss()
    {
        String s = "";
        
        for (Integer i : listePossibilitees)
        {
            s += (i + ",");
        }
        
        return s;
    }
    
    void refreshlistePossibilitees ()
    {
        ArrayList<Integer> newListPossibilitess = new ArrayList<>();
    
        for (Integer i : listePossibilitees)
        {
            if(i != 0)
            {
                newListPossibilitess.add(i);
            }
        }
        
        listePossibilitees = newListPossibilitess;
    }
    
    CASE ()
    {
        
    }
    
    CASE (int c, int l, int v, ArrayList newListeTemplate)
    {
        colonne = c;
        ligne = l;
        val = v;
        //region block = ...;
        if(ligne / 3 == 0)
        {
            if(colonne / 3 == 0)
            {
                block = 0;
            }
            else
            {
                if(colonne / 3 == 1)
                {
                    block = 1;
                }
                else
                {
                    block = 2;
                }
            }
        }
        else
        {
            if(ligne / 3 == 1)
            {
                if(colonne / 3 == 0)
                {
                    block = 3;
                }
                else
                {
                    if(colonne / 3 == 1)
                    {
                        block = 4;
                    }
                    else
                    {
                        block = 5;
                    }
                }
            }
            else
            {
                if(colonne / 3 == 0)
                {
                    block = 6;
                }
                else
                {
                    if(colonne / 3 == 1)
                    {
                        block = 7;
                    }
                    else
                    {
                        block = 8;
                    }
                }
            }
        }
        //endregion
        origin = (v != 0);
        if(v != 0)
        {
            listeTemplate.add(v);
            listePossibilitees = listeTemplate;
        }
        else
        {
            listeTemplate = newListeTemplate;
            listePossibilitees = listeTemplate;
        }
    }
}
