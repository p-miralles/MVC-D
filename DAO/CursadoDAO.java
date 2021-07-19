package Dao;
import Model.Cursado;
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
public class CursadoDAO extends MyConnection {

    private final String SQL_INSERT = "INSERT INTO cursado (cursadoDniAlumno, cursadoCodMateria, cursadoNota) VALUES (?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM cursado";
    private final String SQL_DELETE = "DELETE FROM cursado WHERE cursadoDniAlumno=?";
    private final String SQL_UPDATE = "UPDATE cursado SET cursadoCodMateria=?, cursadoNota=? WHERE cursadoDniAlumno=?";
    private final String SQL_FIND = "SELECT * FROM cursado WHERE cursadoDniAlumno=?";//Falta Optimizar!
    private final String SQL_FINDMAT = "SELECT * FROM cursado WHERE cursadoCodMateria=?";//Falta Optimizar!
    

    public boolean create(Cursado cursado) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, cursado.getDniAlumno());
            ps.setInt(2, cursado.getCodMateria());
            ps.setDouble(3, cursado.getNota());

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

    public List<Cursado> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cursado cursado;
        List<Cursado> listaCursados = new ArrayList<>();

        try {
            conn = getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);//ejecuto la query
            rs = ps.executeQuery();//igualo la respuesta de la query a un resultSet


            while (rs.next()) {
                cursado = new Cursado();

                cursado.setDniAlumno(rs.getInt(1));
                cursado.setCodMateria(rs.getInt(2));
                cursado.setNota(rs.getDouble(3));

                listaCursados.add(cursado);

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
        return listaCursados;
    }

    public boolean update(Cursado cursado) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setInt(1, cursado.getCodMateria());
            ps.setDouble(2, cursado.getNota());

            ps.setInt(3, cursado.getDniAlumno());
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

    public boolean delete(int codCursado) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, codCursado);
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

    public Cursado find(int codCursado) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cursado cursado = new Cursado();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codCursado);
            rs = ps.executeQuery();

            while (rs.next()) {
                cursado.setDniAlumno(rs.getInt(1));
                cursado.setCodMateria(rs.getInt(2));
                cursado.setNota(rs.getDouble(3));
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

        return cursado;
    }

        public Cursado findMat(int codMat) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cursado cursado = new Cursado();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FINDMAT);
            ps.setInt(1, codMat);
            rs = ps.executeQuery();

            while (rs.next()) {
                cursado.setDniAlumno(rs.getInt(1));
                cursado.setCodMateria(rs.getInt(2));
                cursado.setNota(rs.getDouble(3));
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

        return cursado;
    }
}
