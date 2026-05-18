public class NRainhas {

    static int N = 8;
    static char[][] tabuleiro = new char[N][N];

    public static void main(String[] args) {
        iniciarTabuleiro();

        if (resolver(0)) {
            System.out.println("Solução encontrada:");
            imprimirTabuleiro();
        } else {
            System.out.println("Não existe solução.");
        }
    }

    static boolean resolver(int linha) {
        if (linha == N) {
            return true;
        }

        for (int coluna = 0; coluna < N; coluna++) {
            if (posicaoSegura(linha, coluna)) {
                tabuleiro[linha][coluna] = 'Q';

                if (resolver(linha + 1)) {
                    return true;
                }

                tabuleiro[linha][coluna] = '.';
            }
        }

        return false;
    }

    static boolean posicaoSegura(int linha, int coluna) {

        for (int i = 0; i < linha; i++) {
            if (tabuleiro[i][coluna] == 'Q') {
                return false;
            }
        }

        // Verifica diagonal superior esquerda (\)
        for (int i = linha - 1, j = coluna - 1; i >= 0 && j >= 0; i--, j--) {
            if (tabuleiro[i][j] == 'Q') {
                return false;
            }
        }

        // Verifica diagonal superior direita (/)
        for (int i = linha - 1, j = coluna + 1; i >= 0 && j < N; i--, j++) {
            if (tabuleiro[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    static void iniciarTabuleiro() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tabuleiro[i][j] = '.';
            }
        }
    }

    static void imprimirTabuleiro() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}