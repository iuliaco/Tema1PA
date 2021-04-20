import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Valley {

    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<Integer>();
        File file = new File("valley.in");
        BufferedReader reader = null;
        int nr = 0;
        Integer inaltMin = 0;
        Integer ind = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            text = reader.readLine();
            System.out.println(text);
            int inaltime;
            String[] fLine;
            nr = Integer.parseInt(text);
            text = reader.readLine();
            fLine = text.split(" ");
            list.add(Integer.parseInt(fLine[0]));
            inaltMin = Integer.parseInt(fLine[0]);
            for (int i = 1; i < nr; i++) {
                list.add(Integer.parseInt(fLine[i]));
                if (inaltMin > Integer.parseInt(fLine[i])) {
                    inaltMin = Integer.parseInt(fLine[i]);
                    ind = i;
                }
//                System.out.println(Integer.parseInt(fLine[i]));
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

//        System.out.println(list);
//        System.out.println(inaltMin);
        Integer biggestL, biggestR;
        biggestL = list.get(0);
        biggestR = list.get(nr - 1);
        Long total = Long.valueOf(0);
        if (ind == 0)
            ind = 1;
        if (ind == (nr - 1))
            ind = nr - 2;
        for (int i = 0; i <= ind; i++) {
            Integer inaltCur = list.get(i);
            if (inaltCur > biggestL) {
                total += inaltCur - biggestL;
                list.set(i, biggestL);
            } else
                biggestL = inaltCur;
        }
        System.out.println(nr);
        for (int i = nr - 1; i >= ind; i--) {
            Integer inaltCur = list.get(i);
            if (inaltCur > biggestR) {
                total += inaltCur - biggestR;
                list.set(i, biggestR);
            } else
                biggestR = inaltCur;
        }
        System.out.println(total);
        FileWriter myWriter = new FileWriter("valley.out");
        myWriter.write(String.valueOf(total));
        myWriter.close();
    }

}
