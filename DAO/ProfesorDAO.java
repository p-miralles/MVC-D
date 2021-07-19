package Dao;


import Model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO extends MyConnection {

    private final String SQL_INSERT = "INSERT INTO profesor (dni, nombre, apellido, fechaNac, domicilio, telefono) VALUES (?,?,?,?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM profesor";
    private final String SQL_DELETE = "DELETE FROM profesor WHERE dni=?";
    private final String SQL_UPDATE = "UPDATE profesor SET nombre=?,apellido=?,fechaNac=?,domicilio=?, telefono=? WHERE dni=?";
    private final String SQL_FIND = "SELECT * FROM profesor WHERE dni = ?";
    private final String SQL_FINDPROF = "SELECT * FROM materia WHERE matProfDNI =?";


    public boolean create(Profesor profesor) {
        PreparedStatement ps = null;

        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, profesor.getDni());
            ps.setString(2, profesor.getNombre());
            ps.setString(3, profesor.getApellido());
            ps.setDate(4, profesor.getfNac());
            ps.setString(5, profesor.getDomicilio());
            ps.setString(6, profesor.getTelefono());

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

    public List<Profesor> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Profesor profesor;
        List<Profesor> listaProfesores = new ArrayList<>();

        try {
            conn = getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);//ejecuto la query
            rs = ps.executeQuery();//igualo la respuesta de la query a un resultSet

            while (rs.next()) {
                profesor = new Profesor();

                profesor.setDni(rs.getInt(1));
                profesor.setNombre(rs.getString(2));
                profesor.setApellido(rs.getString(3));
                profesor.setfNac(rs.getDate(4));
                profesor.setDomicilio(rs.getString(5));
                profesor.setTelefono(rs.getString(6));

                listaProfesores.add(profesor);

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
        return listaProfesores;
    }

    public boolean update(Profesor profesor) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setDate(3, profesor.getfNac());
            ps.setString(4, profesor.getDomicilio());
            ps.setString(5, profesor.getTelefono());

            ps.setInt(6, profesor.getDni());
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

    public boolean delete(int idProf) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, idProf);
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

    public Profesor find(int dni) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Profesor profesor = new Profesor();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                profesor.setDni(rs.getInt(1));
                profesor.setNombre(rs.getString(2));
                profesor.setApellido(rs.getString(3));
                profesor.setfNac(rs.getDate(4));
                profesor.setDomicilio(rs.getString(5));
                profesor.setTelefono(rs.getString(6));
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

        return profesor;
    }
    
    public boolean exist(int dniProf) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, dniProf);
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
       public boolean daMaterias(int dni) {

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
              //conn.close(ps);
            }
          catch(Exception e) {
            System.out.println(e);
            }
        }

        return false;
    }

}
