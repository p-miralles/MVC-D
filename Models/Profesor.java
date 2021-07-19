package Model;
import Dao.ProfesorDAO;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author User-PC
 */
public class Profesor {

    private int dni;
    private String nombre;
    private String apellido;
    private Date fNac;
    private String domicilio;
    private String telefono;
    private ProfesorDAO profesor_DAO = new ProfesorDAO();

    public Profesor(int dni, String nombre, String apellido, Date fNac, String domicilio, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fNac = fNac;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    
    public Profesor() {
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getfNac() {
        return fNac;
    }

    public void setfNac(Date fNac) {
        this.fNac = fNac;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    //-----------------------------------------
    public boolean createProfesor(Profesor profesor) {
        return profesor_DAO.create(profesor);
    }

    public List<Profesor> readProfesores() {
        return profesor_DAO.read();
    }

    public boolean updateProfesores(Profesor profesor) {
        return profesor_DAO.update(profesor);
    }

    public boolean deleteProfesor(int dni) {
        return profesor_DAO.delete(dni);
    }

    public boolean profesorExist(int dni) {
        return profesor_DAO.exist(dni);
    }
    
    public Profesor findProfesor(int dni ) {
        return profesor_DAO.find(dni);
    }   
    
    public boolean daMaterias(int dni)  {
        return profesor_DAO.daMaterias(dni);
    }  
}
