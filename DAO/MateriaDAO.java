package Dao;

import Model.Materia;
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
public class MateriaDAO extends MyConnection {

    private final String SQL_INSERT = "INSERT INTO materia (matCod,matNom, matProfDNI) VALUES (?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM materia";
    private final String SQL_DELETE = "DELETE FROM materia WHERE matCod=?";
    private final String SQL_UPDATE = "UPDATE materia SET matNom =?, matProfDNI=? WHERE matCod=?";
    private final String SQL_FIND = "SELECT * FROM materia WHERE matCod =?";
    private final String SQL_FINDPROF = "SELECT * FROM materia WHERE matProfDNI =?";


    public boolean create(Materia materia) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, materia.getCodigo());
            ps.setString(2, materia.getNombre());
            ps.setInt(3, materia.getDniProfesor());

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

    public List<Materia> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Materia materia;
        List<Materia> listaMaterias = new ArrayList<>();

        try {
            conn = getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);//ejecuto la query
            rs = ps.executeQuery();//igualo la respuesta de la query a un resultSet


            while (rs.next()) {
                materia = new Materia();

                materia.setCodigo(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setDniProfesor(rs.getInt(3));

                listaMaterias.add(materia);

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
        return listaMaterias;
    }

    public boolean update(Materia materia) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            
            
            ps.setInt(1, materia.getCodigo());
            ps.setString(2, materia.getNombre());
            ps.setInt(3, materia.getDniProfesor());
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

    public boolean delete(int idMateria) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, idMateria);
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
        public Materia find(int codMat) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Materia materia = new Materia();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codMat);
            rs = ps.executeQuery();

            while (rs.next()) {
                materia.setCodigo(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setDniProfesor(rs.getInt(3));
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

        return materia;
    }
     public boolean exist(int codMate) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codMate);
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
          public boolean profExists(int dni) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FINDPROF);
            ps.setInt(1, dni);
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
