import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class somaSubconjutos {

    static long iteracoes = 0;

    /*
     * O método todasSolucoes() para 500 ou 1000 elementos demora uma vida (Complexidade O(2^n)).
     */

    public static void main(String[] args) {

        int[] conjunto1 = {-7, -3, -2, 5, 8};
        int[] conjunto2 = {1, 2, 3, 4, 5, 10};
        int[] conjunto3 = {-5, 2, 3, -1, 1};

        testar(conjunto1);
        testar(conjunto2);
        testar(conjunto3);

        testarPerformance(50);
        testarPerformance(100);
        testarPerformance(500);
        testarPerformance(1000);
    }

    public static List<Integer> primeiraSolucao(int[] conjunto) {
        iteracoes = 0;

        List<Integer> atual = new ArrayList<>();

        boolean encontrou = buscarPrimeira(conjunto, 0, 0, atual);

        if (encontrou) {
            return atual;
        }

        return null;
    }

    private static boolean buscarPrimeira(
            int[] conjunto,
            int indice,
            int soma,
            List<Integer> atual
    ) {
        iteracoes++;

        if (!atual.isEmpty() && soma == 0) {
            return true;
        }

        if (indice == conjunto.length) {
            return false;
        }

        // Escolha 1: pega o número
        atual.add(conjunto[indice]);

        if (buscarPrimeira(conjunto, indice + 1, soma + conjunto[indice], atual)) {
            return true;
        }

        // Backtracking: desfaz a escolha
        atual.remove(atual.size() - 1);

        // Escolha 2: não pega o número
        return buscarPrimeira(conjunto, indice + 1, soma, atual);
    }


    public static List<List<Integer>> todasSolucoes(int[] conjunto) {
        iteracoes = 0;

        List<List<Integer>> respostas = new ArrayList<>();

        buscarTodas(conjunto, 0, 0, new ArrayList<>(), respostas);

        return respostas;
    }

    private static void buscarTodas(
            int[] conjunto,
            int indice,
            int soma,
            List<Integer> atual,
            List<List<Integer>> respostas
    ) {
        iteracoes++;

        if (!atual.isEmpty() && soma == 0) {
            respostas.add(new ArrayList<>(atual));
        }

        if (indice == conjunto.length) {
            return;
        }

        // Escolha 1: pega o número
        atual.add(conjunto[indice]);

        buscarTodas(
                conjunto,
                indice + 1,
                soma + conjunto[indice],
                atual,
                respostas
        );

        // Backtracking
        atual.remove(atual.size() - 1);

        // Escolha 2: não pega o número
        buscarTodas(
                conjunto,
                indice + 1,
                soma,
                atual,
                respostas
        );
    }


    public static void testar(int[] conjunto) {
        System.out.println("\nConjunto: " + arrayParaString(conjunto));

        long inicio = System.nanoTime();

        List<Integer> primeira = primeiraSolucao(conjunto);

        long fim = System.nanoTime();

        System.out.println("Primeira solução: " + primeira);
        System.out.println("Iterações: " + iteracoes);
        System.out.println("Tempo ms: " + (fim - inicio) / 1_000_000.0);

        inicio = System.nanoTime();

        List<List<Integer>> todas = todasSolucoes(conjunto);

        fim = System.nanoTime();

        System.out.println("Todas as soluções: " + todas);
        System.out.println("Quantidade: " + todas.size());
        System.out.println("Iterações: " + iteracoes);
        System.out.println("Tempo ms: " + (fim - inicio) / 1_000_000.0);
        }

    public static void testarPerformance(int tamanho) {
        int[] conjunto = gerarConjunto(tamanho);

        System.out.println("\nPerformance com " + tamanho + " elementos");

        long inicio = System.nanoTime();

        List<Integer> resposta = primeiraSolucao(conjunto);

        long fim = System.nanoTime();

        System.out.println("Encontrou primeira solução? " + (resposta != null));
        System.out.println("Iterações: " + iteracoes);
        System.out.println("Tempo ms: " + (fim - inicio) / 1_000_000.0);
    }

    public static int[] gerarConjunto(int tamanho) {
        Random random = new Random();
        int[] conjunto = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            conjunto[i] = random.nextInt(201) - 100;
        }

        return conjunto;
    }

    public static String arrayParaString(int[] vetor) {
        StringBuilder sb = new StringBuilder("{ ");

        for (int i = 0; i < vetor.length; i++) {
            sb.append(vetor[i]);

            if (i < vetor.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(" }");

        return sb.toString();
    }
    }