import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JLabel JLabelNorth;
    private JButton sairButton;
    private JButton jogoMédioButton;
    private JButton jogoFácilButton;
    private JButton jogoDíficilButton;
    private JButton nomeJogadorButton2;
    private JButton nívelDíficil9999Button;
    private JButton nomeJogadorButton1;
    private JButton nívelMédio9999Button;
    private JButton nomeJogadorButton;
    private JButton nívelFácil9999Button;
    private JButton recordesButton;


    public MinesFinder(String title) {
            super(title);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setContentPane(painelPrincipal);

            pack();

            sairButton.addActionListener(this::btnSairActionPerformed);
            jogoFácilButton.addActionListener(this::btnJogoFacilActionPerformed);
            jogoMédioButton.addActionListener(this::btnJogoMedioActionPerformed);
            jogoDíficilButton.addActionListener(this::btnJogoDificilActionPerformed);
        }
        private void btnSairActionPerformed(ActionEvent e) {
            System.exit(0);
        }
        private void btnJogoFacilActionPerformed(ActionEvent e) {
            JanelaDeJogo janela=new JanelaDeJogo(new CampoMinado(9,9,10));
            janela.setVisible(true); // se não foi executado no construtor…
        }
        private void btnJogoMedioActionPerformed(ActionEvent e) {
            JanelaDeJogo janela = new JanelaDeJogo(new CampoMinado(16, 16, 40));
            janela.setVisible(true);
        }
        private void btnJogoDificilActionPerformed(ActionEvent e) {
            JanelaDeJogo janela = new JanelaDeJogo(new CampoMinado(16, 30, 90));
            janela.setVisible(true);
        }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}