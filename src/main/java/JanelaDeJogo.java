import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaDeJogo extends JFrame {
    private JPanel painelJogo;
    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;


    public JanelaDeJogo(CampoMinado campoMinado) {
        this.campoMinado = campoMinado;
        var largura = campoMinado.getLargura();
        var altura = campoMinado.getAltura();
        this.botoes = new BotaoCampoMinado[largura][altura];

        painelJogo.setLayout(new GridLayout(altura, largura));

        // Criar e adicionar os botões à janela
        for (int coluna = 0; coluna < altura; ++coluna) {
            for (int linha = 0; linha < largura; ++linha) {
                botoes[linha][coluna] = new BotaoCampoMinado(linha, coluna);
                botoes[linha][coluna].addActionListener(this::btnCampoMinadoActionPerformed);

                painelJogo.add(botoes[linha][coluna]);
            }
        }
        setContentPane(painelJogo);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();
        setVisible(true);
    }

    private void actualizarEstadoBotoes () {
        for (int x = 0; x < campoMinado.getLargura(); x++) {
            for (int y = 0; y < campoMinado.getAltura(); y++) {
                botoes[x][y].setEstado(campoMinado.getEstadoQuadricula(x, y));
            }
        }
    }

    public void btnCampoMinadoActionPerformed(ActionEvent e) {
        actualizarEstadoBotoes();
        if (campoMinado.isJogoTerminado(campoMinado.jogoTerminado)) {
            if (campoMinado.isJogadorDerrotado(campoMinado.jogadorDerrotado))
                JOptionPane.showMessageDialog(null, "Oh, rebentou uma mina",
                        "Perdeu!", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Parabéns. Conseguiu descobrir todas as minas em " + (campoMinado.getDuracaoJogo() / 1000) + " segundos", "Vitória", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);

        }
    }
}