import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Action {
    public int currValue;
    public int minValue;
    public int maxValue;

    public Action(int currValue, int minValue, int maxValue) {
        this.currValue = currValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public String toString() {
        return "Action{" +
                "currValue=" + currValue +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}

public class Stocks {
    public static void main(String[] args) throws IOException {
        List<Action> list = new ArrayList<Action>();
        File file = new File("stocks.in");
        BufferedReader reader = null;
        int actiuni = 0;
        int bani = 0;
        int loss = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            text = reader.readLine();
            String[] fLine = text.split(" ");
//            System.out.println(fLine[0]);
            actiuni = Integer.parseInt(fLine[0]);
            bani = Integer.parseInt(fLine[1]);
            loss = Integer.parseInt(fLine[2]);
            int currValue;
            int minValue;
            int maxValue;
            for (int i = 0; i < actiuni; i++) {
                text = reader.readLine();
                fLine = text.split(" ");
                currValue = Integer.parseInt(fLine[0]);
                minValue = Integer.parseInt(fLine[1]);
                maxValue = Integer.parseInt(fLine[2]);
                list.add(new Action(currValue, minValue, maxValue));
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
        int[][][] dp = new int[actiuni + 1][bani + 1][loss + 1];
        for (int j = 0; j <= bani; j++) {
            for (int k = 0; k <= loss; k++) {
                dp[0][j][k] = 0; // cand nu avem actiuni date nu cumparam, caz de baza
            }
        }
        for (int i = 1; i <= actiuni; i++) {
            for (int j = 0; j <= bani; j++) {
                for (int k = 0; k <= loss; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];

                    Action actiune = list.get(i - 1);
                    if ((j - actiune.currValue >= 0) && (k - (actiune.currValue - actiune.minValue) >= 0)) {
                        int aux_dp = dp[i - 1][j - actiune.currValue][k - (actiune.currValue - actiune.minValue)] +
                                (actiune.maxValue - actiune.currValue);

                        dp[i][j][k] = Math.max(dp[i][j][k], aux_dp);
                    }
                }
            }
        }
        System.out.println(dp[actiuni][bani][loss]);
        FileWriter myWriter = new FileWriter("stocks.out");
        myWriter.write(String.valueOf(dp[actiuni][bani][loss]));
        myWriter.close();
    }
}
