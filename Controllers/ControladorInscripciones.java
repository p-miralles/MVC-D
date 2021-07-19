package Controllers;



import Model.Alumno;
import Model.Carrera;
import Model.Inscripcion;
import Model.Materia;
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
public class ControladorInscripciones implements ActionListener {

    //Vistas
    public MenuCMC menu;
    AgregaCMC agrega = new AgregaCMC();
    public Inscripcion inscripcion = new Inscripcion(); //Modelo
    public Alumno alumno = new Alumno(); //Modelo

    //Controladores
     
    private String StatusFlag = "";

    public ControladorInscripciones(MenuCMC menu, AgregaCMC agrega) {
        this.menu = menu;
        this.menu.jbAdd.addActionListener(this);
        this.menu.jbDel.addActionListener(this);
        this.menu.jbEdit.addActionListener(this);
        this.menu.jbClose.addActionListener(this);
        
        this.agrega.jbAdd.addActionListener(this);
        this.agrega.jbCancel.addActionListener(this);
    }

    public ControladorInscripciones() {
    }



    public void iniciar() {
        menu.setTitle("Menu Inscripciones");
        menu.setLocationRelativeTo(null);
        menu.menuTitulo.setText("Gestión de Inscripciones");
        listar();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menu.jbAdd)) {
            System.out.println("Menu Add Inscripcion presionado");
            StatusFlag = "adding";            
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.CreaUpdTitulo.setText("Nueva Inscripción");
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png")));
            agrega.jlA.setText("Nombre");
            agrega.jlB.setText("Fecha");
            agrega.jlC.setText("Código Carrea");
            agrega.setVisible(true);

        } 
        
        if(e.getSource().equals(menu.jbDel)){
            eliminar();
        }
        
        if(e.getSource().equals(menu.jbEdit)){
            System.out.println("Menu Editar Inscripcion presionado");            
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
        List<Inscripcion> inscList = inscripcion.readInscripciones();
        //panelAlumnos.getTblAlumnos().setRowHeight(30);
        String column[]={"Cod. Insc.","Nombre","Fecha","Cod. Carrea"};  
        String data[][]={};
        DefaultTableModel dtm= new DefaultTableModel(data,column); 

        for (int i = 0; i < inscList.size(); i++) {
            dtm.addRow(new Object[] { 
                    inscList.get(i).getCodInscripcion(),
                    inscList.get(i).getNombre(),
                    inscList.get(i).getFecha(),
                    inscList.get(i).getCodCarrera()
            });
        }
        menu.jTable1.setModel(dtm);
    }
    

    public void agregar() {
        Carrera carrera = new Carrera();
        //to do: agregar más validaciones a todos los campos
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Ningun campo debe quedar vacío!!");
         } else if (carrera.carreraExist(Integer.valueOf(agrega.jbC.getText())) == false) {
            JOptionPane.showMessageDialog(null, "Error: la carrera ingresada para esta inscripción no existe.");
        } else {
            System.out.println("Intentando guardar...");
            System.out.println("[debug] ultimo: "+inscripcion.findLast().getCodInscripcion());
            inscripcion.setCodInscripcion(inscripcion.findLast().getCodInscripcion()+1);
            inscripcion.setNombre((agrega.jbA.getText()));
            //inscripcion.setFecha(Date.valueOf(agrega.jbB.getText()));
            inscripcion.setFecha(Date.valueOf("2010-09-09"));
            inscripcion.setCodCarrera(Integer.valueOf(agrega.jbC.getText()));
            if (inscripcion.createInscripcion(inscripcion) == true) {
            agrega.setVisible(false);
            menu.setEnabled(true);
            agrega.jbA.setText("");
            agrega.jbB.setText("");
            agrega.jbC.setText("");      
                listar();
                JOptionPane.showMessageDialog(null, "Guardado Con Exito!");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR, Revisar Consola...");

            }
        }
    }
    
    public void eliminar() {
        Alumno alumno = new Alumno();
        int row = menu.jTable1.getSelectedRow();
        if (row == -1) 
            JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
             else {
            int cod = Integer.parseInt((String) menu.jTable1.getValueAt(row, 0).toString());
            if (alumno.findInscripcion(cod).getCodInscripcion() != 0) {
                JOptionPane.showMessageDialog(null, "Error: La inscripción que intenta eliminar tiene Alumnos asociados.");
            }
        else {
            if (JOptionPane.showConfirmDialog(null, "Eliminar registro", "Esta seguro de eiminar la inscripción?", JOptionPane.YES_NO_OPTION) == 0) {
                inscripcion.deleteInscripcion(cod);
                listar();                
                JOptionPane.showMessageDialog(null, "Eliminada!");
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
            int cod = Integer.valueOf(menu.jTable1.getValueAt(row, 0).toString());
            //cargo Alumno del DAO (DB)
            inscripcion = inscripcion.findInscripcion(cod);
            //Lleno el formulario
            agrega.jbA.setText(String.valueOf(inscripcion.getNombre()));
            agrega.jbB.setText(String.valueOf(inscripcion.getFecha()));
            agrega.jbC.setText(String.valueOf(inscripcion.getCodCarrera()));
            
    }
   }
//-----------------------------------------------
    public void editar(){
        Carrera carrera = new Carrera();
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Todos Los Campos Deben Estar Completos!");
        }
        else if (carrera.carreraExist(Integer.valueOf(agrega.jbC.getText())) == false) {
            JOptionPane.showMessageDialog(null, "Error: la carrera ingresada para esta inscripción no existe.");
        }else 
            //TODO: realizar validaciones extra
          {
            //Se Actualiza El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista
            inscripcion.setNombre(agrega.jbA.getText());
            inscripcion.setFecha(Date.valueOf((agrega.jbB.getText())));
            inscripcion.setCodCarrera(Integer.valueOf(agrega.jbC.getText()));
            
            if (inscripcion.updateInscripcion(inscripcion) == true) {
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
               JOptionPane.showMessageDialog(null, "ERROR, Revisar Consola...");
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