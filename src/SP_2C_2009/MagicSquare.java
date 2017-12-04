package SP_2C_2009;

public class MagicSquare {

    boolean vec[] = null;
    int suma;
    int n;
    int[][] mat = null;

    public MagicSquare(int n) {
        this.n = n;
        int aux = n*n;
        this.suma = ((aux) * (aux + 1) / 2) / n;
        vec = new boolean[aux];
        mat = new int[n][n];
//        System.out.println(mat[0][0]);
    }

    public void solve() {
        solverec(0, 0, suma);
        print();
    }

    private void solverec(int fila, int col, int suma) {
        if (fila == n && col == n) {
            print();
        }

        if(fila >= n || col >= n)
            return;
		for (int i = 1; i < n*n; i++) {
			if (!vec[i]) {
				vec[i] = true;
				System.out.println(fila + "fila");
                System.out.println(col + "col");
				mat[fila][col] = i;
				if(col<n)
					solverec(fila, col + 1, suma - i);
				else if(fila<n)
					solverec(fila+1, col, suma);
				vec[i] = false;
			}
		}

    }

    private void print() {
        for(int i = 0 ; i < mat.length ; i++) {
            for (int j = 0; j < mat.length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println("");
        }
    }


    public static void main(String[] args){
        MagicSquare m = new MagicSquare(3);
        m.solve();
    }
}
