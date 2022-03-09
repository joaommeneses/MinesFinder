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
        //Código criado ao fazer botao direito no botao sair e create listener action listener
        sairButton.addActionListener(this::btnSairActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}