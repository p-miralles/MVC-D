package Dao;

import Model.Alumno;
import com.mysql.jdbc.Connection;
import static java.lang.System.console;
import java.sql.SQLException;

/**
 *
 * @author User-PC
 */
public class TestDAO extends MyConnection {

           public  static AlumnoDAO alumno = new AlumnoDAO();
           public  static MateriaDAO materia = new MateriaDAO();

           //public Alumno alumno;
        public  static void main(String[] args) {

        Connection conn = null;

            try {
            conn = getConnection();
            } catch (Exception e) {
            System.out.println(e);
        }
       //  alumno.find(9564857);
        System.out.println(alumno.find(9564857).getApellido());
        System.out.println(alumno.find(777).getDni());
        System.out.println(alumno.find(777).getTelefono());
        
        if(alumno.exist(777)==true)         System.out.println("Alumno Exists ");
        else  System.out.println("Alumno doesn´t exists!!!");
        if(alumno.exist(13457345)==true)         System.out.println("Alumno Exists ");
        else  System.out.println("Alumno doesn´t exists!!!");
        
        if(materia.profExists(777)==true)         System.out.println("Profesor tiene materias asignadas");
        else  System.out.println("Pofesor no da ninguna materia");
        
        if(materia.profExists(47118211)==true)         System.out.println("Profesor tiene materias asignadas");
        else  System.out.println("Pofesor no da ninguna materia");
        }
        
    
}
