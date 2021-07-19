package Dao;
import static Dao.MyConnection.getConnection;
import Model.Cursado;
import Model.Inscripcion;
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
public class InscripcionDAO extends MyConnection {

    private final String SQL_INSERT = "INSERT INTO inscripcion (inscCodigo, inscNombre, inscFecha, inscCodCarrera) VALUES (?,?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM inscripcion";
    private final String SQL_DELETE = "DELETE FROM inscripcion WHERE inscCodigo=?";
    private final String SQL_UPDATE = "UPDATE inscripcion SET inscNombre=?,inscFecha=?, inscCodCarrera=? WHERE inscCodigo=?";
    private final String SQL_FIND = "SELECT * FROM inscripcion WHERE inscCodigo=?";
    private final String SQL_FINDCARR = "SELECT * FROM inscripcion WHERE inscCodCarrera=?";
    private final String SQL_FINDLASTINSC = "SELECT MAX(inscCodigo) FROM inscripcion";

    public boolean create(Inscripcion inscripcion) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, inscripcion.getCodInscripcion());
            ps.setString(2, inscripcion.getNombre());
            ps.setDate(3, inscripcion.getFecha());
            ps.setInt(4, inscripcion.getCodCarrera());

            ps.executeUpdate();
            System.out.println("Agregado Con Exito");

            return true;
        } catch (SQLException e) {
            System.out.println("Error al Crear : " + e);
            return false;

        } finally {
            try{
              conn.close();
              //conn.close(ps);
            }
          catch(Exception e) {
            System.out.println(e);
            }
        }
    }

        public List<Inscripcion> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inscripcion inscripcion;
        List<Inscripcion> listaInscripciones = new ArrayList<>();

        try {
            conn = getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);//ejecuto la query
            rs = ps.executeQuery();//igualo la respuesta de la query a un resultSet


            while (rs.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setCodInscripcion(rs.getInt(1));
                inscripcion.setNombre(rs.getString(2));
                inscripcion.setFecha(rs.getDate(3));
                inscripcion.setCodCarrera(rs.getInt(4));

                listaInscripciones.add(inscripcion);

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
        return listaInscripciones;
    }
  
    public boolean update(Inscripcion inscripcion) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setString(1, inscripcion.getNombre());
            ps.setDate(2, inscripcion.getFecha());
            ps.setInt(3, inscripcion.getCodCarrera());

            ps.setInt(4, inscripcion.getCodInscripcion());
            ps.executeUpdate();
            System.out.println("Actualizado Con Exito");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al Actualizar : " + e);
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

    public boolean delete(int codInscripcion) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, codInscripcion);
            ps.executeUpdate();
            System.out.println("Eliminado con Exito");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al Eliminar : " + e);
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
    
        public Inscripcion find(int codInsc) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Inscripcion inscripcion = new Inscripcion();

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codInsc);
            rs = ps.executeQuery();

            while (rs.next()) {
                inscripcion.setCodInscripcion(rs.getInt(1));
                inscripcion.setNombre(rs.getString(2));
                inscripcion.setFecha(rs.getDate(3));
                inscripcion.setCodCarrera(rs.getInt(4));
                
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
        return inscripcion;
    }

        public Inscripcion findCarrera(int codCarr) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Inscripcion inscripcion = new Inscripcion();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FINDCARR);
            ps.setInt(1, codCarr);
            rs = ps.executeQuery();

            while (rs.next()) {

                inscripcion.setCodInscripcion(rs.getInt(1));
                inscripcion.setNombre(rs.getString(2));
                inscripcion.setFecha(rs.getDate(3));
                inscripcion.setCodCarrera(rs.getInt(4));
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
        return inscripcion;
    }
        public Inscripcion findLast() {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Inscripcion inscripcion = new Inscripcion();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FINDLASTINSC);
            //ps.setInt(3, codInsc);
            rs = ps.executeQuery();

            while (rs.next()) {
                inscripcion.setCodInscripcion(rs.getInt(1));
                inscripcion.setNombre(rs.getString(2));
                inscripcion.setFecha(rs.getDate(3));
                inscripcion.setCodCarrera(rs.getInt(4));
                
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
        return inscripcion;
    }
        
}
