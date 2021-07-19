package Controllers;
import Model.Alumno;
import Model.Profesor;
import Views.AgregaAP;
import Views.MenuAlumnoProfesor;
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
public class ControladorProfesores implements ActionListener {

    //Vistas
    public MenuAlumnoProfesor menu;
    AgregaAP agrega = new AgregaAP();
    public Profesor profesor = new Profesor(); //Modelo

    private String StatusFlag = "";

    public ControladorProfesores(MenuAlumnoProfesor menu, AgregaAP agrega) {
        this.menu = menu;
        this.menu.jbAdd.addActionListener(this);
        this.menu.jbDel.addActionListener(this);
        this.menu.jbEdit.addActionListener(this);
        this.menu.jbClose.addActionListener(this);

        this.agrega.jbAdd.addActionListener(this);
        this.agrega.jbCancel.addActionListener(this);
    }

    public ControladorProfesores() {
    }

    public void iniciar() {
        menu.setTitle("Menu Profesor");
        menu.setLocationRelativeTo(null);
        menu.menuTitulo.setText("Gestión de Profesores");
        listar();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menu.jbAdd)) {
            System.out.println("Menu Add Profesor presionado");
            StatusFlag = "adding";
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.AgregaAPTitulo.setText("Nuevo Profesor");
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png")));
            agrega.setVisible(true);

        }

        if (e.getSource().equals(menu.jbDel)) {
            eliminar();
        }

        if (e.getSource().equals(menu.jbEdit)) {
            System.out.println("Menu Editar Profesor presionado");
            preparaEdicion();
        }

        if (e.getSource().equals(menu.jbClose)) {
            menu.setVisible(false);
        }

        if (e.getSource().equals(agrega.jbCancel)) {
            agrega.setVisible(false);
            menu.setEnabled(true);
            agrega.jtDNI.setText("");
            agrega.jtNombre.setText("");
            agrega.jtApellido.setText("");
            agrega.jtDomicilio.setText("");
            agrega.jtFechNac.setText("");
            agrega.jtTel.setText("");
        }
        if (e.getSource().equals(agrega.jbAdd)) {
            System.out.println("Boton ADD/OK");
            if (StatusFlag == "adding") {
                agregar();
            } else {
                if (StatusFlag == "editing") {
                    editar();
                } else {
                    System.out.println("log: error de lógica de botones!!");
                }
            }
        }

    }//end action performed

    public void listar() {
        List<Profesor> profList = profesor.readProfesores();
        //panelAlumnos.getTblAlumnos().setRowHeight(30);
        String column[] = {"DNI", "Nombre", "Apellido", "F-Nac", "Dirección", "Telefono"};
        String data[][] = {};
        DefaultTableModel dtm = new DefaultTableModel(data, column);

        for (int i = 0; i < profList.size(); i++) {
            dtm.addRow(new Object[]{
                String.valueOf(profList.get(i).getDni()),
                profList.get(i).getNombre(),
                profList.get(i).getApellido(),
                profList.get(i).getfNac().toString(),
                profList.get(i).getDomicilio(),
                profList.get(i).getTelefono()
            });
        }
        menu.jtLista.setModel(dtm);
    }

    public void agregar() {
        //to do: agregar más validaciones a todos los campos
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
        } else if (profesor.profesorExist(Integer.valueOf(agrega.jtDNI.getText())) == true) {
            JOptionPane.showMessageDialog(null, "El DNI que intenta cargar ya existe");
        } else {
            System.out.println("Intentando guardar...");
            //Se Crea El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista

            profesor.setDni(Integer.valueOf(agrega.jtDNI.getText()));
            profesor.setNombre(agrega.jtNombre.getText());
            profesor.setApellido(agrega.jtApellido.getText());
            profesor.setfNac(Date.valueOf(agrega.jtFechNac.getText()));
            profesor.setDomicilio(agrega.jtDomicilio.getText());
            profesor.setTelefono(agrega.jtTel.getText());

            // Alumno a2 = new Alumno(4458865,"Pepe","Honguito",Date.valueOf("2000-12-12"),"Azcuenaga 90809809","261444555",777);
            if (profesor.createProfesor(profesor) == true) {
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
                JOptionPane.showMessageDialog(null, "ERROR");

            }
        }
    }

    public void eliminar() {
        int row = menu.jtLista.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
        } else {
            int id = Integer.parseInt((String) menu.jtLista.getValueAt(row, 0).toString());
            if (profesor.daMaterias(id) == true) {
                JOptionPane.showMessageDialog(null, "Error: El profesor que intenta eliminar tiene materias asignadas.");
            } else  {
                    
                    profesor.deleteProfesor(id);
                    listar();
                    JOptionPane.showMessageDialog(null, "Eliminado!");
                }
            }
        
    }

    public void preparaEdicion() {
        int row = menu.jtLista.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
        } else {
            StatusFlag = "editing";
            menu.setEnabled(false);
            menu.setLocationRelativeTo(null);
            agrega.AgregaAPTitulo.setText("Editar Profesor");
            //agrega.jbAdd.setIcon(new ImageIcon("Images//ok.png"));
            agrega.jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok.png")));
            //cambiar nombres campos aqui
            agrega.setVisible(true);
            //carga los campos con los valores a editar
            int dni = Integer.valueOf(menu.jtLista.getValueAt(row, 0).toString());
            //cargo Alumno del DAO (DB)
            profesor = profesor.findProfesor(dni);
            //Lleno el formulario
            agrega.jtDNI.setText(String.valueOf(profesor.getDni()));
            agrega.jtApellido.setText(String.valueOf(profesor.getApellido()));
            agrega.jtNombre.setText(String.valueOf(profesor.getNombre()));
            agrega.jtFechNac.setText(String.valueOf(profesor.getfNac()));
            agrega.jtDomicilio.setText(String.valueOf(profesor.getDomicilio()));
            agrega.jtTel.setText(String.valueOf(profesor.getTelefono()));
        }
    }
//-----------------------------------------------

    public void editar() {
        if (validarVacios() == false) {
            JOptionPane.showMessageDialog(null, "Todos Los Campos Deben Estar Completos!");
        } else //TODO: realizar validaciones extra
        {
            //Se Actualiza El Alumno Una Vez Que Se Validaron Los Datos Ingresados Por La Vista
            profesor.setDni(Integer.valueOf(agrega.jtDNI.getText()));
            profesor.setNombre(agrega.jtNombre.getText());
            profesor.setApellido(agrega.jtApellido.getText());
            profesor.setfNac(Date.valueOf(agrega.jtFechNac.getText()));
            profesor.setDomicilio(agrega.jtDomicilio.getText());
            profesor.setTelefono(agrega.jtTel.getText());

            if (profesor.updateProfesores(profesor) == true) {
                listar();
                StatusFlag = "";
                agrega.setVisible(false);
                menu.setEnabled(true);
                agrega.jtDNI.setText("");
                agrega.jtNombre.setText("");
                agrega.jtApellido.setText("");
                agrega.jtDomicilio.setText("");
                agrega.jtFechNac.setText("");
                agrega.jtTel.setText("");
                JOptionPane.showMessageDialog(null, "Edición exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR, Revisar Consola...");
            }
        }
    }

//-----------------------------------------------    
    public boolean validarVacios() {
        if (agrega.jtNombre.getText().isEmpty()
                || agrega.jtApellido.getText().isEmpty()
                || agrega.jtDomicilio.getText().isEmpty()
                || agrega.jtTel.getText().isEmpty()
                || agrega.jtDNI.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
//------------------------------------------------

//------------------------------------------------
}//end class
