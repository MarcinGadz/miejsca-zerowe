import java.util.Arrays;

public class demo {
    public static void main(String[] args) {
        double[][] macierz = new double[][]{
                {1,2,3},
                {5,1,4},
                {8,1,2.5}
        };
        double[] wektor = new double[] {6,2,5};


        /*
           NIEOZNACZONOSC/SPRZECZNOSC
         */
        //Pętla po wszystkich wierszach
        int liczbaWierszy = 3;
        for (int i = 0; i < liczbaWierszy; i++) {
            boolean isZeroRow = true;
            for (int j = 0; j < 3; j++) {
                if(macierz[i][j] != 0) {
                    isZeroRow = false;
                }
            }
            if(isZeroRow) {
                if(wektor[i] == 0) {
                    System.out.println("NIEOZNACZONY");
                    return;
                }
                System.out.println("SPRZECZNY");
                return;
            }

            /*
            PIVOTING
             */
            double maxValue = Math.abs(macierz[i][i]);
            int p = i;
            double tmp;
            for (int j = i; j < liczbaWierszy; j++) {
                tmp = Math.abs(macierz[j][i]);
                if(tmp > maxValue) {
                    maxValue = tmp;
                    p = j;
                }
            }
            if(p != i) {
                double tmpArr[] = Arrays.copyOf(macierz[i], liczbaWierszy);
                macierz[i] = Arrays.copyOf(macierz[p], liczbaWierszy);
                macierz[p] = Arrays.copyOf(tmpArr, liczbaWierszy);
                double tmpRes = wektor[i];
                wektor[i] = wektor[p];
                wektor[p] = wektor[i];
            }
        }
    }
}
