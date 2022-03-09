import javax.swing.*;
public class JanelaDeJogo extends JFrame {
    private JPanel painelJogo;

    public JanelaDeJogo() {
        setContentPane(painelJogo);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true); // opcional. Pode optar por fazer depois.
    }
}