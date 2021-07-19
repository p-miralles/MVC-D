package Dao;
import Model.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User-PC
 */
public class AlumnoDAO extends MyConnection {

    private final String SQL_INSERT = "INSERT INTO alumno (dni, nombre, apellido, fechaNac, domicilio, telefono, codInscripcion) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM alumno";
    private final String SQL_DELETE = "DELETE FROM alumno WHERE dni=?";
    private final String SQL_UPDATE = "UPDATE alumno SET nombre=?, apellido=?, fechaNac=?, domicilio=?, telefono=?, codInscripcion=? WHERE dni=?";
    private final String SQL_UPDATE_CARRERA = "UPDATE alumno SET codInscripcion=? WHERE dni=?";
    private final String SQL_FIND = "SELECT * FROM alumno WHERE dni=?";
    private final String SQL_FINDINSCRIPCION = "SELECT * FROM alumno WHERE codInscripcion=?";


    public boolean create(Alumno alumno) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            System.out.println("Intentodo Insert..");

            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, alumno.getfNac());
            ps.setString(5, alumno.getDomicilio());
            ps.setString(6, alumno.getTelefono());
            ps.setInt(7, alumno.getCodInscripcion());
            ps.executeUpdate();
            System.out.println("Log: Insert OK");
            return true;
        } catch (SQLException e) {
            System.out.println("Log: Insert Error " + e);
            return false;

        } finally {
            try{
              conn.close();
            }
          catch(Exception e) {
            System.out.println(e);
            }
  
        }
    }

    public List<Alumno> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumno alumno;
        List<Alumno> listaAlumnos = new ArrayList<>();

        try {
            conn = getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);//ejecuto la query
            rs = ps.executeQuery();//igualo la respuesta de la query a un resultSet


            while (rs.next()) {
                alumno = new Alumno();

                alumno.setDni(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                alumno.setfNac(rs.getDate(4));
                alumno.setDomicilio(rs.getString(5));
                alumno.setTelefono(rs.getString(6));
                alumno.setCodInscripcion(rs.getInt(7));

                listaAlumnos.add(alumno);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            try{
              conn.close();
            }
          catch(Exception e) {
            System.out.println(e);
            }
        }
        return listaAlumnos;
    }

    public boolean update(Alumno alumno) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, alumno.getfNac());
            ps.setString(4, alumno.getDomicilio());
            ps.setString(5, alumno.getTelefono());
            ps.setInt(6, alumno.getDni());
            ps.setInt(7, alumno.getCodInscripcion());            
            ps.executeUpdate();
            System.out.println("log: Update OK");
            return true;

        } catch (SQLException e) {
            System.out.println("log: Update Error  " + e);
            return false;

        } finally {
            try{
              conn.close();
            }
          catch(Exception e) {
            System.out.println(e);
            }

        }
    }


    public boolean delete(int idAlumno) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, idAlumno);
            ps.executeUpdate();
            System.out.println("log: DELETE OK");
            return true;

        } catch (SQLException e) {
            System.out.println("log: DELETE Error    " + e);
            return false;

        } finally {
            try{
              conn.close();
            }
          catch(Exception e) {
            System.out.println(e);
            }

        }
    }

    public Alumno find(int dniAlumno) {

        System.out.println("On Alumno Find");
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Alumno alumno = new Alumno();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, dniAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                alumno.setDni(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                alumno.setfNac(rs.getDate(4));
                alumno.setDomicilio(rs.getString(5));
                alumno.setTelefono(rs.getString(6));
                alumno.setCodInscripcion(rs.getInt(7));
            }

        } catch (SQLException e) {
            System.out.println("log: GET/FIND error  " + e);

        } finally {
            try{
              conn.close();
            }
          catch(Exception e) {
            System.out.println(e);
            }

        }

        return alumno;
    }

    public boolean exist(int dniAlumno) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, dniAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("log: FIND/GET Error     " + e);

        } finally {
            try{
              conn.close();
            }
          catch(Exception e) {
            System.out.println(e);
            }
        }

        return false;
    }
        public Alumno findInscripcion(int codInsc) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Alumno alumno = new Alumno();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FINDINSCRIPCION);
            ps.setInt(1, codInsc);
            rs = ps.executeQuery();

            while (rs.next()) {
                alumno.setDni(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                alumno.setfNac(rs.getDate(4));
                alumno.setDomicilio(rs.getString(5));
                alumno.setTelefono(rs.getString(6));
                alumno.setCodInscripcion(rs.getInt(7));
            }

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            try{
              conn.close();
            }
          catch(Exception e) {
            System.out.println(e);
            }

        }
        return alumno;
    }    

}
