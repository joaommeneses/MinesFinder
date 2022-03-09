import java.util.Random;

public class CampoMinado {

    private boolean[][] minas;
    public static final int VAZIO = 0;
    /* de 1 a 8 são o número de minas à volta */
    public static final int TAPADO = 9;
    public static final int DUVIDA = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;
    private int[][] estado;
    private int largura;
    private int altura;
    private int numMinas;
    private boolean primeiraJogada = true;
    public boolean jogoTerminado;
    public boolean jogadorDerrotado;
    private long instanteInicioJogo;
    private long duracaoJogo;


    public CampoMinado(int largura, int altura, int numMinas) {
        this.largura = largura;
        this.altura = altura;
        this.numMinas = numMinas;
        this.jogadorDerrotado = false;
        this.jogoTerminado = false;
        minas = new boolean[largura][altura]; // Valores começam a false
        estado = new int[largura][altura]; // Valores começam a 0

        for (var x = 0; x < largura; ++x) {
            for (var y = 0; y < altura; ++y) {
                estado[x][y] = TAPADO;
            }
        }
    }

    public int getEstadoQuadricula(int x, int y) {
        return estado[x][y];
    }

    public boolean hasMina(int x, int y) {
        return minas[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public void revelarQuadricula(int x, int y) {
        int quantidadeMinasVizinhas;
        if (jogoTerminado || estado[x][y] < TAPADO) {        // O if no código garante que só são reveladas quadrículas cujo estado actual seja TAPADO, obedecendo, portanto, à regra
            return;
        }

        if(isVitoria()){
            jogoTerminado = true;
            jogadorDerrotado = false;
            duracaoJogo=System.currentTimeMillis()-instanteInicioJogo;
        }
        if(minas[x][y]){                         //se a quadricula que é revelada estiver tapada
            estado[x][y] = REBENTADO;
            jogoTerminado = true;
            jogadorDerrotado = true;
            duracaoJogo=System.currentTimeMillis()-instanteInicioJogo;
        }

        if (primeiraJogada) {

            primeiraJogada = false;
            colocarMinas(x, y);                 //apos a primeira do jogador, as minas sao colocadas, argumentos sao as coordenadas da primeira quadricula escolhida
            instanteInicioJogo = System.currentTimeMillis();
        }

        quantidadeMinasVizinhas = contarMinasVizinhas(x,y);

        if(quantidadeMinasVizinhas == 0){
            estado[x][y] = VAZIO;
            revelarQuadriculasVizinhas(x,y);

        }else{
            estado[x][y] = quantidadeMinasVizinhas;
        }


        // Faz aqui qualquer coisa...
    }

    public static boolean isJogoTerminado(boolean jogoTerminado){
        return jogoTerminado;
    }

    public static boolean isJogadorDerrotado(boolean jogadorDerrotado){
        return jogadorDerrotado;
    }


    private void colocarMinas(int exceptoX, int exceptoY) {     //distribua as minas pelo campo minado, garantindo que não fica nenhuma mina na posição da primeira jogada e que todas as minas ficam em quadrículas diferentes
        var aleatorio = new Random();
        var x = 0;
        var y = 0;
        for (var i = 0; i < numMinas; ++i) {
            do {
                x = aleatorio.nextInt(largura);
                y = aleatorio.nextInt(altura);
            } while (minas[x][y] || (x == exceptoX && y == exceptoY));
            minas[x][y] = true;
        }
    }

    private int contarMinasVizinhas(int x, int y) {
        var numMinasVizinhas = 0;
        for (var i = Math.max(0, x - 1); i < Math.min(largura, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(altura, y + 2); ++j) {
                if (minas[i][j]) {
                    ++numMinasVizinhas;
                }
            }
        }
        return numMinasVizinhas;
    }

    private void revelarQuadriculasVizinhas(int x, int y){
        estado[x+1][y] = VAZIO;
        estado[x][y+1] = VAZIO;
        estado[x+1][y+1] = VAZIO;
        estado[x-1][y-1] = VAZIO;
    }

    private boolean isVitoria() {
        for (int i = 0; i < largura; ++i) {
            for (var j = 0 ; j < altura; ++j) {
                if (!minas[i][j] && estado[i][j] >= TAPADO) {
                    return false;
                }
            }
        }
        return true;
    }

    private void marcarComoTendoMina(int x, int y){
        if(estado[x][y] == TAPADO || estado[x][y] == DUVIDA){
            estado[x][y] = MARCADO;
        }
    }

    private void marcarComoSuspeita(int x, int y){
        if(estado[x][y] == TAPADO || estado[x][y] == MARCADO){
            estado[x][y] = DUVIDA;
        }
    }

    private void desmarcarQuadricula(int x, int y){
        if(estado[x][y] == MARCADO || estado[x][y] == DUVIDA){
            estado[x][y] = TAPADO;
        }
    }

    public long getDuracaoJogo() {
        if (primeiraJogada) {
            return 0;
        }
        if (!jogoTerminado) {
            return System.currentTimeMillis() - instanteInicioJogo;
        }

        return duracaoJogo;
    }


}
