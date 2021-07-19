package Controllers;
import Model.Alumno;
import Model.Cursado;
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
public class ControladorCursados implements ActionListener {

    //Vistas
    public MenuCMC menu;
    AgregaCMC agrega = new AgregaCMC();
    public Cursado cursado = new Cursado(); //Modelo
    //Controladores
     
    private String StatusFlag = "";

    public ControladorCursados(MenuCMC menu, AgregaCMC agrega) {
        this.menu = menu;
        this.menu.jbAdd.addActionListener(this);
        this.menu.jbDel.addActionListener(this);
        this.menu.jbEdit.addActionListener(this);
        this.menu.jbClose.addActionListener(this);
        
        this.agrega.jbAdd.addActionListener(this);
        this.agrega.jbCancel.addActionListener(this);
    }

    public ControladorCursados() {
    }

    public void iniciar() {
        menu.setTitle("Menu Cursados");
        menu.setLocationRelativeTo(null);
        menu.menuTitulo.setText("Gestión de Cursados");
        listar();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menu.jbAdd)) {
            System.out.println("Menu Add Cursado presionado");
            StatusFlag = "adding";            
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.CreaUpdTitulo.setText("Nuevo Cursado");
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png")));
            agrega.jlA.setText("DNI Alumno");
            agrega.jlB.setText("Codigo Materia");
            agrega.jlC.setText("Nota");
            //ctrlAlumnos.iniciar();
            agrega.setVisible(true);

        } 
        
        if(e.getSource().equals(menu.jbDel)){
            eliminar();
        }
        
        if(e.getSource().equals(menu.jbEdit)){
            System.out.println("Menu Editar Cursados presionado");            
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
        List<Cursado> cursList = cursado.readCursado();
        //panelAlumnos.getTblAlumnos().setRowHeight(30);
        String column[]={"DNI Alumno","Codigo Materia","Nota"};  
        String data[][]={};
        DefaultTableModel dtm= new DefaultTableModel(data,column); 

        for (int i = 0; i < cursList.size(); i++) {
            dtm.addRow(new Object[] { 
                    cursList.get(i).getDniAlumno(),
                    cursList.get(i).getCodMateria(),
                    cursList.get(i).getNota()
            });
        }
        menu.jTable1.setModel(dtm);
    }
    

    public void agregar() {
        Materia materia = new Materia();
        Alumno alumno = new Alumno();
        //to do: agregar más validaciones a todos los campos
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Ningun campo debe quedar vacío!!");
        } else if (materia.findMateria(Integer.valueOf(agrega.jbB.getText())).getCodigo()==0) {
            JOptionPane.showMessageDialog(null, "Error: la materia ingresada para este cursado no existe.");
        } else if (alumno.alumnoExist(Integer.valueOf(agrega.jbA.getText())) == false) {
            JOptionPane.showMessageDialog(null, "Error: el alumno ingresado para este cursado no existe.");
        }
        
        else {
            System.out.println("Intentando guardar...");
            //Se Crea El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista
   
            cursado.setDniAlumno(Integer.valueOf(agrega.jbA.getText()));
            cursado.setCodMateria(Integer.valueOf(agrega.jbB.getText()));
            cursado.setNota(Double.valueOf(agrega.jbC.getText()));
            if (cursado.createCursado(cursado) == true) {
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
                cursado.deleteCursado(cod);
                listar();                
                JOptionPane.showMessageDialog(null, "Eliminado!");
          

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
            agrega.CreaUpdTitulo.setText("Editar Cursado");
            //agrega.jbAdd.setIcon(new ImageIcon("Images//ok.png"));
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok.png")));
            //cambiar nombres campos aqui
            agrega.setVisible(true);
            //carga los campos con los valores a editar
            int dni = Integer.valueOf(menu.jTable1.getValueAt(row, 0).toString());
            //cargo Alumno del DAO (DB)
            cursado = cursado.findCursado(dni);
            //Lleno el formulario
            agrega.jbA.setText(String.valueOf(cursado.getDniAlumno()));
            agrega.jbB.setText(String.valueOf(cursado.getCodMateria()));
            agrega.jbC.setText(String.valueOf(cursado.getNota()));
            
    }
   }
//-----------------------------------------------
    public void editar(){
        Materia materia = new Materia();
        Alumno alumno = new Alumno();        
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Todos Los Campos Deben Estar Completos!");
           } else if (materia.findMateria(Integer.valueOf(agrega.jbB.getText())).getCodigo()==0) {
            JOptionPane.showMessageDialog(null, "Error: la materia ingresada para este cursado no existe.");
        } else if (alumno.alumnoExist(Integer.valueOf(agrega.jbA.getText())) == false) {
            JOptionPane.showMessageDialog(null, "Error: el alumno ingresado para este cursado no existe.");
        }
        
        else 
            //TODO: realizar validaciones extra
          {
            //Se Actualiza El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista
            cursado.setDniAlumno(Integer.valueOf(agrega.jbA.getText()));
            cursado.setCodMateria(Integer.valueOf(agrega.jbB.getText()));
            cursado.setNota(Double.valueOf(agrega.jbC.getText()));
            
            if (cursado.updateCursado(cursado) == true) {
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
               JOptionPane.showMessageDialog(null, "ERROR.");
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