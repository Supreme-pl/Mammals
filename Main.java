import java.io.*;
import java.util.ArrayList;

class Mammal {
    private String name;
    private short birthYear;
    private boolean young;
    String className = this.getClass().getSimpleName();
    public Mammal(String name, short birthYear, boolean young) {
        this.name = name;
        this.birthYear = birthYear;
        this.young = young;
    }
    @Override
    public String toString() {
        return className+","+name + ","+birthYear+","+young+",";
    }

    void save(FileOutputStream fos) {

    }
}
class Wolf extends Mammal {
    private String herdName; //ok
    private int position; //ok
    public Wolf(String name, short birthYear, boolean young, String herdName, int position){
        super(name, birthYear, young);
        this.herdName = herdName;
        this.position = position;
    }

    String introduceUrself() {
        return super.toString() + herdName + "," + position;
    }

    void save(FileOutputStream fos) {
        String daneObiektu = this.introduceUrself();
        try {
            byte [] daneObiektuTab = daneObiektu.getBytes();
            fos.write(daneObiektuTab);
            fos.write("\n".getBytes());
            fos.flush();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
class Wadera extends Mammal {
    private int whelpsQuantity; //ok
    private Mammal[] whelps; //ok
    public Wadera(String name, short birthYear, boolean young, int whelpsQuantity) {
        super(name, birthYear, young);
        this.whelpsQuantity=whelpsQuantity;
        whelps = new Mammal[this.whelpsQuantity];
    }
    String introduceUrself(){
        if (whelpsQuantity==1)
            return super.toString() + whelpsQuantity + " whelp";
        return super.toString() + whelpsQuantity + " whelps";
    }
    public void addWhelp(int numer, Mammal whelp){
        this.whelps[numer] = whelp;
    }
    void save(FileOutputStream fos) {

        String daneObiektu = this.introduceUrself();

        try {
            byte [] daneObiektuTab = daneObiektu.getBytes();
            fos.write(daneObiektuTab);
            fos.write("\n".getBytes());
            fos.flush();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("wilki.txt");
        if(f.exists() && !f.isDirectory())
        {
            Mammal[] Mammal = load("wilki.txt");
        } else { createHerds();}
    }

    public static Mammal[] load (String sciezka) {
        Mammal[] herds = new Mammal[20];
        int n = 0;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("wilki.txt"));
            String line = reader.readLine();
            while(line != null) {
                String[] tokens = line.split(",");
                if(tokens[0].equals("Wilk")) herds[n] = new Wolf(tokens[1], (short)Integer.parseInt(tokens[2]), Boolean.parseBoolean(tokens[3]), tokens[4], Integer.parseInt(tokens[5]));
                else if(tokens[0].equals("Wadera")) herds[n] = new Wadera(tokens[1], (short)Integer.parseInt(tokens[2]), Boolean.parseBoolean(tokens[3]), Character.getNumericValue(tokens[4].charAt(0)));
                n++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return herds;
    }

    public static void createHerds() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("wilki.txt");

        ArrayList<Mammal> herd1 = new ArrayList<>();
        herd1.add(new Wolf("Szefu", (short) 1999, false, "Bomba", 1));
        herd1.add(new Wolf("Dyjek", (short) 2002, false, "Bomba", 2));
        herd1.add(new Wolf("Alberto", (short) 2000,false, "Bomba", 3));
        herd1.add(new Wolf("Pikachu", (short)2020, true, "Bomba", 4));
        herd1.add(new Wolf("Panda", (short) 2021, true, "Bomba", 5));
        herd1.add(new Wadera("Mikasa", (short) 2004, false, 1));
        herd1.add(new Wadera("Sasha", (short)2005, false, 2));
        herd1.add(new Wadera("Historia", (short)2009, false, 1));
        herd1.add(new Wadera("Minnie Mouse", (short) 2019, true, 0));
        herd1.add(new Wadera("Duffy", (short)2020, true, 0));
        ArrayList<Mammal> herd2 = new ArrayList<>();
        herd2.add(new Wolf("Michael", (short) 1999, false, "Zubrowka", 1));
        herd2.add(new Wolf("Jim", (short) 2002, false, "Zubrowka", 2));
        herd2.add(new Wolf("Dwight", (short) 2000,false, "Zubrowka", 3));
        herd2.add(new Wolf("Kevin", (short)2020, true, "Zubrowka", 4));
        herd2.add(new Wolf("Creed", (short) 2021, true, "Zubrowka", 5));
        herd2.add(new Wadera("Angela", (short) 2004, false, 1));
        herd2.add(new Wadera("Pam", (short)2005, false, 2));
        herd2.add(new Wadera("Karen", (short)2009, false, 1));
        herd2.add(new Wadera("Dwike", (short) 2019, true, 0));
        herd2.add(new Wadera("Philip", (short)2020, true, 0));

        for(Mammal herdMember : herd1)
        {
            herdMember.save(fos);
        }
        for(Mammal herdMember : herd2)
        {
            herdMember.save(fos);
        }
    }
}
