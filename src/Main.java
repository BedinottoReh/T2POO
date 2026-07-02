import javax.swing.SwingUtilities;
import view.MainFrame;

public class Main{
    public static void main(String[] args){
        //inicializa a interface gráfica
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}