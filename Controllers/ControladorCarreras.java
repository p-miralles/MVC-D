package Controllers;
import Model.Carrera;
import Model.Inscripcion;
import Views.AgregaAP;
import Views.AgregaCMC;
import Views.MenuAlumnoProfesor;
import Views.MenuCMC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User-PC
 */
public class ControladorCarreras implements ActionListener {

    //Vistas
    public MenuCMC menu;
    AgregaCMC agrega = new AgregaCMC();
    public Carrera carrera = new Carrera(); //Modelo
    public Inscripcion inscripcion = new Inscripcion(); //instancia para chequeo de existencia de incripción

     
    private String StatusFlag = "";

    public ControladorCarreras(MenuCMC menu, AgregaCMC agrega) {
        this.menu = menu;
        this.menu.jbAdd.addActionListener(this);
        this.menu.jbDel.addActionListener(this);
        this.menu.jbEdit.addActionListener(this);
        this.menu.jbClose.addActionListener(this);
        
        this.agrega.jbAdd.addActionListener(this);
        this.agrega.jbCancel.addActionListener(this);
    }

    public ControladorCarreras() {
    }


    public void iniciar() {
        menu.setTitle("Menu Carreras");
        menu.setLocationRelativeTo(null);
        menu.menuTitulo.setText("Gestión de Carreras");
        listar();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menu.jbAdd)) {
            System.out.println("Menu Add Carrera presionado");
            StatusFlag = "adding";            
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.CreaUpdTitulo.setText("Nueva Carrera");
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png")));
            agrega.jlA.setText("Código");
            agrega.jlB.setText("Nombre");
            agrega.jlC.setText("Duración");
            //ctrlAlumnos.iniciar();
            agrega.setVisible(true);

        } 
        
        if(e.getSource().equals(menu.jbDel)){
            eliminar();
        }
        
        if(e.getSource().equals(menu.jbEdit)){
            System.out.println("Menu Editar Carreras presionado");            
            preparaEdicion();            
        }
        
        if(e.getSource().equals(menu.jbClose)){
            menu.setVisible(false);
        }        
        
        
        if(e.getSource().equals(agrega.jbCancel)){
            agrega.setVisible(false);
            menu.setEnabled(true);
            agrega.jbA.setText("");
            agrega.jbB.setText("");
            agrega.jbC.setText("");
        }
        if(e.getSource().equals(agrega.jbAdd)){
            System.out.println("Boton ADD/OK");
            if(StatusFlag=="adding") agregar();
            else{
                if(StatusFlag=="editing") editar();
                else System.out.println("log: error de lógica de botones!!");
            }
        }
        
        
        
    }//end action performed
    
    
    public void listar() {
        List<Carrera> careerList = carrera.readCarrera();
        //panelAlumnos.getTblAlumnos().setRowHeight(30);
        String column[]={"Codigo","Nombre","Duración [años]"};  
        String data[][]={};
        DefaultTableModel dtm= new DefaultTableModel(data,column); 

        for (int i = 0; i < careerList.size(); i++) {
            dtm.addRow(new Object[] { 
                    String.valueOf(careerList.get(i).getCod_Carrera()),
                    careerList.get(i).getNombre(),
                    careerList.get(i).getDuracion()
            });
        }
        menu.jTable1.setModel(dtm);
    }
    

    public void agregar() {
        //to do: agregar más validaciones a todos los campos
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Ningun campo debe quedar vacío!!");
        } else if (carrera.carreraExist(Integer.valueOf(agrega.jbA.getText())) == true) {
            JOptionPane.showMessageDialog(null, "El código de carrera que intenta agregar ya Existe!");
        } else {
            System.out.println("Intentando guardar...");
            //Se Crea El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista
            carrera.setCod_Carrera(Integer.valueOf(agrega.jbA.getText()));
            carrera.setNombre(agrega.jbB.getText());
            carrera.setDuracion(agrega.jbC.getText());
            if (carrera.createCarrera(carrera) == true) {
            agrega.setVisible(false);
            menu.setEnabled(true);
            agrega.jbA.setText("");
            agrega.jbB.setText("");
            agrega.jbC.setText("");
            
                listar();
                JOptionPane.showMessageDialog(null, "Guardado Con Exito!");

            } else {
                JOptionPane.showMessageDialog(null, "ERROR");

            }
        }
    }
    
    public void eliminar() {
        int row = menu.jTable1.getSelectedRow();
        if (row == -1) 
            JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
             else {
            int cod = Integer.parseInt((String) menu.jTable1.getValueAt(row, 0).toString());
            //System.out.println("[debug] Codigo: "+cod);
            //System.out.println("[debug] Cod desde insc.: "+inscripcion.findCarrera(cod).getCodCarrera());
            if (inscripcion.findCarrera(cod).getCodCarrera() != 0) {
                JOptionPane.showMessageDialog(null, "Error: La carrera que intenta eliminar tiene inscripciones asociadas.");
            }
        else {
            if (JOptionPane.showConfirmDialog(null, "Eliminar registro", "Esta seguro de eiminar la carrera?", JOptionPane.YES_NO_OPTION) == 0) {
                carrera.deleteCarrera(cod);
                listar();                
                JOptionPane.showMessageDialog(null, "Eliminado!");
            }
            }
        }
    }

        public void preparaEdicion() {
        int row = menu.jTable1.getSelectedRow();
        if (row == -1)
            JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
        else {
            StatusFlag = "editing";
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.CreaUpdTitulo.setText("Editar Carrera");
            //agrega.jbAdd.setIcon(new ImageIcon("Images//ok.png"));
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok.png")));
            //cambiar nombres campos aqui
            agrega.setVisible(true);
            //carga los campos con los valores a editar
            int dni = Integer.valueOf(menu.jTable1.getValueAt(row, 0).toString());
            //cargo Alumno del DAO (DB)
            carrera = carrera.findCarrera(dni);
            //Lleno el formulario
            agrega.jbA.setText(String.valueOf(carrera.getCod_Carrera()));
            agrega.jbB.setText(String.valueOf(carrera.getNombre()));
            agrega.jbC.setText(String.valueOf(carrera.getDuracion()));
            
    }
   }
//-----------------------------------------------
    public void editar(){
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Todos Los Campos Deben Estar Completos!");
        } else 
            //TODO: realizar validaciones extra
          {
            //Se Actualiza El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista
            carrera.setCod_Carrera(Integer.valueOf(agrega.jbA.getText()));
            carrera.setNombre(agrega.jbB.getText());
            carrera.setDuracion(agrega.jbC.getText());
            
            if (carrera.updateCarrera(carrera) == true) {
                listar();
                StatusFlag ="";
                agrega.setVisible(false);
                menu.setEnabled(true);
                agrega.jbA.setText("");
                agrega.jbB.setText("");
                agrega.jbC.setText("");
                JOptionPane.showMessageDialog(null, "Edición exitosa");
            }
            else {
               JOptionPane.showMessageDialog(null, "ERROR");
            }
        }
    }
//-----------------------------------------------
    public boolean validarVacios() {
        if ( agrega.jbA.getText().isEmpty() || 
             agrega.jbB.getText().isEmpty() ||
             agrega.jbC.getText().isEmpty()
            )
             return false;
        else return true;
    }
//------------------------------------------------

}//end class