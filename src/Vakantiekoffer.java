import java.util.ArrayList;

public class Vakantiekoffer {
    //De grootte mag niet groter zijn dan 5
    public enum category
    {
       bottoms, tops, jurken, schoeisel,accessoires;

    }
    private ArrayList<category>items =new ArrayList<>();
    private ArrayList<category>meegenomenCategoriën =new ArrayList<>();

    private int totaalGewicht;
    public Vakantiekoffer()
    {

    }
    public void toevoegenItem(category categorie, int gewicht)
    {
        //checken van de lijstItems
        if(gewicht<0 || gewicht >10 || totaalGewicht + gewicht>8  )
            throw new IllegalArgumentException("Het gewicht klopt niet");
        if(categorie == null)
        {
            throw new NullPointerException("De categorie is leeg");
        }
        if(items.size()+ 1 >=5)
            throw new IllegalArgumentException("De koffer zit al vol");
        if(meegenomenCategoriën.stream().filter(c->c  == categorie).count()>=3)
            throw new IllegalArgumentException("Je kan geen artikel van deze categorie meer meenemen");
        switch(categorie){
            case jurken -> {
                if(meegenomenCategoriën.stream().filter(c -> c == category.bottoms).count()+1<=3 || meegenomenCategoriën.stream().filter(c -> c == category.tops).count()+1<=3) {
                   throw new IllegalArgumentException("Kan geen jurk meer toevoegen");
                }
                meegenomenCategoriën.add(category.tops);
                meegenomenCategoriën.add(category.bottoms);
                items.add(categorie);
                break;


            }
            case bottoms, tops, schoeisel, accessoires -> {
                meegenomenCategoriën.add(categorie);
                items.add(categorie);
                break;
            }

        }
        totaalGewicht+=gewicht;

    }
    public void vertrek()
    {
        controle();
    }
    //todo controle aantal topjes, broekjes, schoeisel
    public void controle()
    {if(meegenomenCategoriën.stream().filter(c -> c == category.bottoms).count()<1 || meegenomenCategoriën.stream().filter(c-> c == category.tops).count()<1
    || meegenomenCategoriën.stream().filter(c-> c == category.schoeisel).count()<1
    )
        throw new IllegalArgumentException("Er moet minstens één top, schoeisel en bottoms zijn");

    }
}
