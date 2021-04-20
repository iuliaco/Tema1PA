import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Components {
    public int monede;
    public int pret;

    public Components(int monede, int pret) {
        this.monede = monede;
        this.pret = pret;
    }

}


public class Crypto {

    public static void main(String[] args) throws IOException {
        List<Components> list = new ArrayList<Components>();
        File file = new File("crypto.in");
        BufferedReader reader = null;
        int calculatoare = 0;
        int bani = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            text = reader.readLine();
            String[] fLine = text.split(" ");
            System.out.println(fLine[0]);
            calculatoare = Integer.parseInt(fLine[0]);
            bani = Integer.parseInt(fLine[1]);
            int capacitate;
            int pret;
            for (int i = 0; i < calculatoare; i++) {
                text = reader.readLine();
                fLine = text.split(" ");
                capacitate = Integer.parseInt(fLine[0]);
                pret = Integer.parseInt(fLine[1]);
                list.add(new Components(capacitate, pret));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list.sort(new Comparator<Components>() {
            @Override
            public int compare(Components o1, Components o2) {
                if (o1.monede > o2.monede)
                    return 1;
                else if (o1.monede < o2.monede) {
                    return -1;
                } else {
                    if (o1.pret > o2.pret)
                        return 1;
                    else if (o1.pret < o2.pret) {
                        return -1;
                    } else
                        return 0;
                }
            }
        });
//        System.out.println(list);

        int baniConsumati = 0;
        Components componenta = list.get(0);
        int monedeFinal = componenta.monede;
        int i = 1;
        int updatePartial = componenta.pret;
        int monedeFinale = componenta.monede;
        int monedeViitorUpdate = list.get(i).monede;
        while(baniConsumati <= bani) {
            if(monedeFinale == monedeViitorUpdate) {
                updatePartial = updatePartial + list.get(i).pret;
                baniConsumati += updatePartial;
                i++;
                monedeFinale++;
                monedeViitorUpdate = list.get(i).monede;
            } else {
                baniConsumati += updatePartial;
                monedeFinale++;
            }
        }

        if(baniConsumati > bani)
            monedeFinale--;
        System.out.println(monedeFinale);
        FileWriter myWriter = new FileWriter("crypto.out");
        myWriter.write(String.valueOf(monedeFinale));
        myWriter.close();

    }

}
