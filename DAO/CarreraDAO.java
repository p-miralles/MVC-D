package Dao;
import Model.Carrera;
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
public class CarreraDAO extends MyConnection {

    private final String SQL_INSERT = "INSERT INTO carrera (carreraCod, carreraNombre,carreraDuracion) VALUES (?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM carrera";
    private final String SQL_DELETE = "DELETE FROM carrera WHERE carreraCod=?";
    private final String SQL_UPDATE = "UPDATE carrera SET carreraNombre=?,carreraDuracion=? WHERE carreraCod=?";
    private final String SQL_FIND = "SELECT * FROM carrera WHERE carreraCod=?";

    public boolean create(Carrera carrera) {
        PreparedStatement ps = null;

        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, carrera.getCod_Carrera());
            ps.setString(2, carrera.getNombre());
            ps.setString(3, carrera.getDuracion());

            ps.executeUpdate();
            System.out.println("Agregado Con Exito");

            return true;
        } catch (SQLException e) {
            System.out.println("Error al Crear : " + e);
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

    public List<Carrera> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Carrera carrera;
        List<Carrera> listaCarreras = new ArrayList<>();

        try {
            conn = getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);//ejecuto la query
            rs = ps.executeQuery();//igualo la respuesta de la query a un resultSet

            while (rs.next()) {
                carrera = new Carrera();

                carrera.setCod_Carrera(rs.getInt(1));
                carrera.setNombre(rs.getString(2));
                carrera.setDuracion(rs.getString(3));

                listaCarreras.add(carrera);

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
        return listaCarreras;
    }

    public boolean update(Carrera carrera) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setString(1, carrera.getNombre());
            ps.setString(2, carrera.getDuracion());

            ps.setInt(3, carrera.getCod_Carrera());
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

    public boolean delete(int codCarrera) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, codCarrera);
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

    public Carrera find(int codCarrera) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Carrera carrera = new Carrera();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codCarrera);
            rs = ps.executeQuery();

            while (rs.next()) {
                carrera.setCod_Carrera(rs.getInt(1));
                carrera.setNombre(rs.getString(2));
                carrera.setDuracion(rs.getString(3));
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

        return carrera;
    }

    public boolean exist(int codCarrera) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codCarrera);
            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
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
        return false;
    }

}
