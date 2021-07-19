package Controllers;


import Model.Alumno;
import Model.Cursado;
import Model.Inscripcion;
import Views.AgregaAP;
import Views.AgregaAlumno;
import Views.MenuAlumnoProfesor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User-PC
 */
public class ControladorAlumnos implements ActionListener {

    //Vistas
    public MenuAlumnoProfesor menu;
    AgregaAlumno agrega = new AgregaAlumno();
    public Alumno alumno = new Alumno(); //Modelo
    public Cursado cursado = new Cursado(); //modelo para chequeo de alumno en cursado
    public Inscripcion inscripcion = new Inscripcion(); //modelo para chequeo de alumno en cursado

    
    private String StatusFlag = "";
    public ControladorAlumnos(MenuAlumnoProfesor menu, AgregaAP agrega) {
        this.menu = menu;
        this.menu.jbAdd.addActionListener(this);
        this.menu.jbDel.addActionListener(this);
        this.menu.jbEdit.addActionListener(this);
        this.menu.jbClose.addActionListener(this);
        
        this.agrega.jbAdd.addActionListener(this);
        this.agrega.jbCancel.addActionListener(this);
    }

    public ControladorAlumnos() {
    }

    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    final int WIDTH = screenSize.width;
    final int HEIGHT = screenSize.height;


    public void iniciar() {
        menu.setTitle("Menu Alumno");
        menu.setLocationRelativeTo(null);
        menu.menuTitulo.setText("Gestión de Alumnos");
        listar();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menu.jbAdd)) {
            System.out.println("Menu Add Alumnos presionado");
            StatusFlag = "adding";
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.AgregaAPTitulo.setText("Nuevo Alumno");
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png")));

            agrega.setLocation(WIDTH/6,HEIGHT/4);            
            agrega.setVisible(true);
        } 
        
        if(e.getSource().equals(menu.jbDel)){
            eliminar();
        }
        
        if(e.getSource().equals(menu.jbEdit)){
            System.out.println("Menu Editar Alumnos presionado");            
            preparaEdicion();
        }
        if(e.getSource().equals(menu.jbClose)){
            menu.setVisible(false);
        }        

        if(e.getSource().equals(agrega.jbCancel)){
            agrega.setVisible(false);
            menu.setEnabled(true);
            agrega.jtDNI.setText("");
            agrega.jtNombre.setText("");
            agrega.jtApellido.setText("");
            agrega.jtDomicilio.setText("");
            agrega.jtFechNac.setText("");
            agrega.jtTel.setText("");
            agrega.jtInscripcion.setText("");

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
        List<Alumno> alumnList = alumno.readAlumnos();
        //panelAlumnos.getTblAlumnos().setRowHeight(30);
        String column[]={"DNI","Codigo Insc.","Nombre","Apellido","F-Nac","Dirección","Telefono"};  
        String data[][]={};
        DefaultTableModel dtm= new DefaultTableModel(data,column); 

        for (int i = 0; i < alumnList.size(); i++) {
            dtm.addRow(new Object[] { 
                    String.valueOf(alumnList.get(i).getDni()),
                    String.valueOf(alumnList.get(i).getCodInscripcion()),
                    alumnList.get(i).getNombre(),
                    alumnList.get(i).getApellido(),
                    alumnList.get(i).getfNac().toString(),
                    alumnList.get(i).getDomicilio(),
                    alumnList.get(i).getTelefono()
            });
        }
        menu.jtLista.setModel(dtm);
    }
    

    public void agregar() {
        //to do: agregar más validaciones a todos los campos
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
        } else if (alumno.alumnoExist(Integer.valueOf(agrega.jtDNI.getText())) == true) {
            JOptionPane.showMessageDialog(null, "El DNI que intenta cargar ya existe");
        }
          else if (inscripcion.findInscripcion(Integer.valueOf(agrega.jtInscripcion.getText())).getCodInscripcion() == 0) {
            JOptionPane.showMessageDialog(null, "La inscripción a la que intenta asignar al alumno no existe");            
        } else {
            System.out.println("Intentando guardar...");
            //Se Crea El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista
   
            alumno.setCodInscripcion(Integer.valueOf(agrega.jtInscripcion.getText()));
            alumno.setDni(Integer.valueOf(agrega.jtDNI.getText()));
            alumno.setNombre(agrega.jtNombre.getText());
            alumno.setApellido(agrega.jtApellido.getText());
            alumno.setfNac(Date.valueOf(agrega.jtFechNac.getText()));
            //alumno.setfNac(Date.valueOf("2010-09-09"));
            alumno.setDomicilio(agrega.jtDomicilio.getText());
            alumno.setTelefono(agrega.jtTel.getText());


         Alumno a2 = new Alumno(4458865,"Pepe","Honguito",Date.valueOf("2000-12-12"),"Azcuenaga 90809809","261444555",777);
            if (alumno.createAlumno(alumno) == true) {
            agrega.setVisible(false);
            menu.setEnabled(true);
            agrega.jtDNI.setText("");
            agrega.jtNombre.setText("");
            agrega.jtApellido.setText("");
            agrega.jtDomicilio.setText("");
            agrega.jtFechNac.setText("");
            agrega.jtTel.setText("");
                listar();
                JOptionPane.showMessageDialog(null, "Guardado Con Exito!");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR, Revisar Consola...");
            }
        }
    }
    
    public void eliminar() {
        int row = menu.jtLista.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
        } else {
            int id = Integer.parseInt((String) menu.jtLista.getValueAt(row, 0).toString());
            if (cursado.findCursado(id).getCodMateria() != 0) {  //optimizar: crear relación alumno-cursado y buscar por get alumno.cursado.alumnoDNI
                JOptionPane.showMessageDialog(null, "Error: El alumno que intenta eliminar está presente en uno o más cursados.");
            }
            else  {
                    alumno.deleteAlumno(id);
                    listar();
                    JOptionPane.showMessageDialog(null, "Eliminado!");
                }
            }
        
    }

    public void preparaEdicion() {
        int row = menu.jtLista.getSelectedRow();
        if (row == -1)
            JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
        else {
            StatusFlag = "editing";
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.AgregaAPTitulo.setText("Editar Alumno");
            //agrega.jbAdd.setIcon(new ImageIcon("Images//ok.png"));
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok.png")));
            //cambiar nombres campos aqui
            agrega.setVisible(true);
            //carga los campos con los valores a editar
            int dni = Integer.valueOf(menu.jtLista.getValueAt(row, 0).toString());
            //cargo Alumno del DAO (DB)
            alumno = alumno.findAlumno(dni);
            //Lleno el formulario
            agrega.jtDNI.setText(String.valueOf(alumno.getDni()));
            agrega.jtApellido.setText(String.valueOf(alumno.getApellido()));
            agrega.jtNombre.setText(String.valueOf(alumno.getNombre()));
            agrega.jtFechNac.setText(String.valueOf(alumno.getfNac()));
            agrega.jtDomicilio.setText(String.valueOf(alumno.getDomicilio()));
            agrega.jtTel.setText(String.valueOf(alumno.getTelefono()));
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
            alumno.setDni(Integer.valueOf(agrega.jtDNI.getText()));
            alumno.setNombre(agrega.jtNombre.getText());
            alumno.setApellido(agrega.jtApellido.getText());
            alumno.setfNac(Date.valueOf(agrega.jtFechNac.getText()));
            alumno.setDomicilio(agrega.jtDomicilio.getText());
            alumno.setTelefono(agrega.jtTel.getText());

            if (alumno.updateAlumno(alumno) == true) {
                listar();
                StatusFlag ="";
                agrega.setVisible(false);
                menu.setEnabled(true);
                agrega.jtDNI.setText("");
                agrega.jtNombre.setText("");
                agrega.jtApellido.setText("");
                agrega.jtDomicilio.setText("");
                agrega.jtFechNac.setText("");
                agrega.jtTel.setText("");                
                JOptionPane.showMessageDialog(null, "Edición exitosa");
            }
            else {
               JOptionPane.showMessageDialog(null, "ERROR");
            }
        }
    }
         
//-----------------------------------------------    
    public boolean validarVacios() {
        if ( agrega.jtNombre.getText().isEmpty() || 
             agrega.jtApellido.getText().isEmpty() ||
             agrega.jtDomicilio.getText().isEmpty()||
             agrega.jtTel.getText().isEmpty() || 
             agrega.jtDNI.getText().isEmpty()     ) 
             return false;
        else return true;
    }
//------------------------------------------------
   
//------------------------------------------------

}//end class