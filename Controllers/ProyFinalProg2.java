package Controllers;
import Views.MenuPrincipal;
/**
 *
 * @author User-PC
 */
public class ProyFinalProg2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instancio vista menu principal
        MenuPrincipal menuppal = new MenuPrincipal();
        //instancio controlador del Menu Ppal y le paso la vista del MP como argumento para que observe
        ControladorMenuPrincipal ctrlppal = new ControladorMenuPrincipal(menuppal);
        menuppal.setResizable(false);
        menuppal.setTitle("Proyecto Final - Programacion II");
        menuppal.setVisible(true);
   
    }
    
}
