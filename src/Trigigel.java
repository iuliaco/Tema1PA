import java.io.*;

public class Trigigel {
    public static void multiply_matrix(long[][] A, int kmax, long[][] B, long[][] C, long mod) {
        long[][] tmp = new long[kmax][kmax];
        for (int i = 0; i < kmax; i++) {
            for (int j = 0; j < kmax; j++) {
                Long sum = Long.valueOf(0);
                for (int k = 0; k < kmax; k++) {
                    sum += 1L * A[i][k] * B[k][j];
                }
                tmp[i][j] = (long) (sum % mod);
            }
        }
        for (int i = 0; i < kmax; i++) {
            for (int j = 0; j < kmax; j++) {
                C[i][j] = tmp[i][j];
            }

        }
    }

    public static void power_matrix(long[][] C, Long p, long[][] R, int kmax, long mod) {
        long[][] tmp = new long[kmax][kmax];
        for (int i = 0; i < kmax; i++) {
            for (int j = 0; j < kmax; j++) {
                tmp[i][j] = (i == j) ? 1 : 0;
            }
        }
        while (p != 1 && p != 0) {
            System.out.println(p);
            if (p % 2 == 0) {
                multiply_matrix(C, kmax, C, C, mod);     // C = C*C
                p /= 2;                       // rămâne de calculat C^(p/2)
            } else {
                // reduc la cazul anterior:
                multiply_matrix(tmp, kmax, C, tmp, mod); // tmp = tmp*C
                --p;                          // rămâne de calculat C^(p-1)
            }
        }

        // avem o parte din rezultat în C și o parte în tmp
        multiply_matrix(C, kmax, tmp, R, mod);
    }

    public static long trig(Long n, long mod) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 3;
        }
        if (n == 3) {
            return 5;
        }
        if (n == 4) {
            return 10;
        }
        int kmax = 4;
        long[][] C = {{1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 1, 0, 0},
                {0, 0, 1, 1}};
        power_matrix(C, n, C, kmax, mod);

        // sol = S_4 * C = dp[n] (se află pe ultima poziție din S_n,
        // deci voi folosi ultima coloană din C)
        Long sol = (1 * C[0][1] + 1 * C[0][2] + 1 * C[0][3]) % mod;
        System.out.println("sol " + sol + " C1 " + C[0][1] + " C2 " + C[0][2] + " C3 " + C[0][3] + " C[3][3] " + C[3][3]);
        return sol;

    }

    public static void main(String[] args) throws IOException {
        File file = new File("trigigel.in");
        BufferedReader reader = null;
        Long nr = Long.valueOf(0);
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            text = reader.readLine();
            System.out.println(text);
            nr = Long.parseLong(text);
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
        long mod = 1000000007;
        System.out.println(trig(nr, mod));
        FileWriter myWriter = new FileWriter("trigigel.out");
        myWriter.write(String.valueOf(trig(nr, mod)));
        myWriter.close();

    }
}
