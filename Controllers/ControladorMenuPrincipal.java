package Controllers;


import Model.Alumno;
import Views.AgregaAP;
import Views.AgregaCMC;
import Views.MenuAlumnoProfesor;
import Views.MenuCMC;
import Views.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;

/**
 *
 * @author User-PC
 */
public class ControladorMenuPrincipal implements ActionListener {

    
    
    //instancio vistas
    private MenuPrincipal menu; //Vista MP
    MenuAlumnoProfesor MenuAP = new MenuAlumnoProfesor();
    AgregaAP agregaAP = new AgregaAP();
    MenuCMC menuCMC = new MenuCMC();
    AgregaCMC agregaCMC = new AgregaCMC();
    MenuCMC menuMaterias = new MenuCMC();
    AgregaCMC agregaMateria = new AgregaCMC();
    MenuAlumnoProfesor menuProfesores = new MenuAlumnoProfesor();
    AgregaAP agregaProfesor = new AgregaAP();
    MenuCMC menuCursados = new MenuCMC();
    AgregaCMC agregaCursado = new AgregaCMC();
    MenuCMC menuInscripciones = new MenuCMC();
    AgregaCMC agregaInscripcion = new AgregaCMC();




    //instancio controladores y les paso las vistas
    ControladorAlumnos ctrlAlumnos = new ControladorAlumnos(MenuAP,agregaAP);
    ControladorCarreras ctrlCarreras = new ControladorCarreras(menuCMC,agregaCMC);
    ControladorMaterias ctrlMaterias = new ControladorMaterias(menuMaterias,agregaMateria);
    ControladorProfesores ctrlProfesores = new ControladorProfesores(menuProfesores,agregaProfesor);
    ControladorCursados  ctrolCursados = new ControladorCursados(menuCursados,agregaCursado);
    ControladorInscripciones ctrolInscripciones = new ControladorInscripciones(menuInscripciones,agregaInscripcion);
    
    public ControladorMenuPrincipal(MenuPrincipal menu) {
        this.menu = menu;
        this.menu.jbAlumnos.addActionListener(this);
        this.menu.jbProfesores.addActionListener(this);
        this.menu.jbMaterias.addActionListener(this);
        this.menu.jbCarreras.addActionListener(this);
        this.menu.jbInscripciones.addActionListener(this);
        this.menu.jbCursados.addActionListener(this);
        
        this.MenuAP.jbClose.addActionListener(this);
        this.menuCMC.jbClose.addActionListener(this);
        this.menuMaterias.jbClose.addActionListener(this);
        this.menuProfesores.jbClose.addActionListener(this);
        this.menuCursados.jbClose.addActionListener(this);
        this.menuInscripciones.jbClose.addActionListener(this);
        }

    public ControladorMenuPrincipal() {
    }



    public void iniciar() {
        menu.setTitle("Menu Principal");
        menu.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menu.jbAlumnos)) {
            System.out.println("Menu Alumnos presionado");
            MenuAP.menuTitulo.setText("Gestión de Alumnos");
            menu.setEnabled(false);
            ctrlAlumnos.iniciar();
            MenuAP.setVisible(true);

        }
        if (e.getSource().equals(menu.jbProfesores)) {
            System.out.println("Menu Profesores presionado");
            MenuAP.menuTitulo.setText("Gestión de Profesores");
            menu.setEnabled(false);
            ctrlProfesores.iniciar();
            menuProfesores.setVisible(true);

        }        
        if (e.getSource().equals(menu.jbMaterias)) {
            System.out.println("Menu Materias presionado");
            MenuAP.menuTitulo.setText("Gestión de Materias");
            menu.setEnabled(false);
            ctrlMaterias.iniciar();
            menuMaterias.setVisible(true);

        }        

        if (e.getSource().equals(menu.jbCursados)) {
            System.out.println("Menu Cursados presionado");
            MenuAP.menuTitulo.setText("Gestión de Cursados");
            menu.setEnabled(false);
            ctrolCursados.iniciar();
            menuCursados.setVisible(true);

        }  
        if (e.getSource().equals(menu.jbInscripciones)) {
            System.out.println("Menu Inscripciones presionado");
            MenuAP.menuTitulo.setText("Gestión de Inscripciones");
            menu.setEnabled(false);
            ctrolInscripciones.iniciar();
            menuInscripciones.setVisible(true);

        }        
        if (e.getSource().equals(menu.jbCarreras)) {
            System.out.println("Menu Carreras presionado");
            MenuAP.menuTitulo.setText("Gestión de Carreras");
            menu.setEnabled(false);
            ctrlCarreras.iniciar();
            menuCMC.setVisible(true);
 
        }        
        if ( e.getSource().equals(MenuAP.jbClose)       ||
             e.getSource().equals(menuProfesores.jbClose) ||
             e.getSource().equals(menuCMC.jbClose)      ||
             e.getSource().equals(menuMaterias.jbClose) ||
             e.getSource().equals(menuCursados.jbClose) ||
             e.getSource().equals(menuInscripciones.jbClose) 
            )
              {   
              menu.setEnabled(true);            
              }     
   }
}