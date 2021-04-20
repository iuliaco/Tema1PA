import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Tree {
    public int inaltime;
    public int pret;

    public Tree(int inaltime, int pret) {
        this.inaltime = inaltime;
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "inaltime=" + inaltime +
                ", pret=" + pret +
                '}';
    }
}

public class Ridge {

    public static void main(String[] args) throws IOException {
        List<Tree> list = new ArrayList<Tree>();
        File file = new File("ridge.in");
        BufferedReader reader = null;
        int copaci = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            text = reader.readLine();
            String[] fLine;
            copaci = Integer.parseInt(text);
            int inaltime;
            int cost;
            for (int i = 0; i < copaci; i++) {
                text = reader.readLine();
                fLine = text.split(" ");
                inaltime = Integer.parseInt(fLine[0]);
                cost = Integer.parseInt(fLine[1]);
                list.add(new Tree(inaltime, cost));
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
        long [][] dp = new long[copaci][3];
        dp[0][0] = 0;
        dp[0][1] = list.get(0).pret;
        dp[0][2] = list.get(0).pret * 2;
        for (int i = 1; i < copaci; i++) {
                long baza = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][0] = Math.min(baza, dp[i - 1][2]);
                dp[i][1] = Math.min(baza, dp[i - 1][2]) + list.get(i).pret;
                dp[i][2] = Math.min(baza, dp[i - 1][2]) + list.get(i).pret * 2;
                if (list.get(i).inaltime == list.get(i-1).inaltime) {
                    dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]);
                    dp[i][1] = Math.min(dp[i - 1] [0], dp[i - 1][2] ) + list.get(i).pret;
                    dp[i][2] = Math.min(dp[i - 1] [0], dp[i - 1][1] ) +  list.get(i).pret*2;
                }

                if (list.get(i).inaltime == list.get(i-1).inaltime + 1) {
                    long aux = Math.min(dp[i - 1][1], dp[i - 1][0]);
                    dp[i][0] = Math.min(aux, dp[i - 1][2]);
                    dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][2] ) + list.get(i).pret;
                    dp[i][2] = Math.min(dp[i - 1] [0], dp[i - 1][2] ) +  list.get(i).pret*2;
                }


                if (list.get(i).inaltime == list.get(i-1).inaltime + 2) {
                    long aux = Math.min(dp[i - 1][1], dp[i - 1][0]);
                    dp[i][0] = Math.min(aux, dp[i - 1][2]);
                    dp[i][1] = Math.min(aux, dp[i - 1][2] ) + list.get(i).pret;
                    dp[i][2] = Math.min(dp[i - 1] [1], dp[i - 1][2] ) +  list.get(i).pret*2;
                }

                if (list.get(i).inaltime == list.get(i-1).inaltime - 1) {
                    long aux = Math.min(dp[i - 1][1], dp[i - 1][0]);
                    dp[i][0] = Math.min( dp[i - 1][0], dp[i - 1][2]);
                    dp[i][1] = Math.min( dp[i - 1][0], dp[i - 1][1] ) + list.get(i).pret;
                    dp[i][2] = Math.min(aux , dp[i - 1][2] ) +  list.get(i).pret*2;
                }

                if (list.get(i).inaltime == list.get(i-1).inaltime - 2) {
                    long aux = Math.min(dp[i - 1][1], dp[i - 1][0]);
                    dp[i][0] = Math.min( dp[i - 1][0], dp[i - 1][1]);
                    dp[i][1] = Math.min(aux, dp[i - 1][2] ) + list.get(i).pret;
                    dp[i][2] = Math.min(aux , dp[i - 1][2] ) +  list.get(i).pret*2;
                }


                if(list.get(i).inaltime == 1) {
                    dp[i][2] = Long.MAX_VALUE;
                }
                if(list.get(i).inaltime == 0) {
                    dp[i][2] = Long.MAX_VALUE;
                    dp[i][1] = Long.MAX_VALUE;
                }

            }
        long aux = Math.min(dp[copaci - 1][1], dp[copaci - 1][2]);
        System.out.println(Math.min(dp[copaci - 1][0], aux));
        FileWriter myWriter = new FileWriter("ridge.out");
        myWriter.write(String.valueOf(Math.min(dp[copaci - 1][0], aux)));
        myWriter.close();

    }

}
