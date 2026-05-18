Complexidades

N-rainhas

Tempo: O(n!) no pior caso, porque o algoritmo tenta posicionar uma rainha por linha e evita repetir colunas.

Espaco: O(n), considerando os vetores de controle e a pilha de recursao.

Soma dos subconjuntos

Uma solucao: O(2^n) no pior caso, pois cada elemento pode entrar ou nao no subconjunto.

Todas as solucoes: O(2^n) para visitar os subconjuntos, mais o custo de copiar e armazenar as respostas.

Espaco: O(n) durante a recursao. Se armazenar todas as solucoes, pode chegar a O(k * n), onde k é a quantidade de solucoes encontradas.
