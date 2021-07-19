package Model;
import Dao.AlumnoDAO;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author User-PC
 */
public class Alumno {
    private int dni;
    private String nombre;
    private String apellido;
    private Date fNac;
    private String domicilio;
    private String telefono;
    private int codInscripcion;
    private AlumnoDAO alumno_DAO = new AlumnoDAO();

    public Alumno(int dni, String nombre, String apellido, Date fNac, String domicilio, String telefono, int codInscripcion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fNac = fNac;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.codInscripcion = codInscripcion;
    }



    public Alumno() {
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

    public int getCodInscripcion() {
        return codInscripcion;
    }

    public void setCodInscripcion(int codInscripcion) {
        this.codInscripcion = codInscripcion;
    }

    public AlumnoDAO getAlumno_DAO() {
        return alumno_DAO;
    }

    public void setAlumno_DAO(AlumnoDAO alumno_DAO) {
        this.alumno_DAO = alumno_DAO;
    }

    //--------------------
    public boolean createAlumno(Alumno alumno) {
        return alumno_DAO.create(alumno);
    }

    public List<Alumno> readAlumnos() {
        return alumno_DAO.read();
    }

    public boolean updateAlumno(Alumno alumno) {
        return alumno_DAO.update(alumno);
    }

    public boolean deleteAlumno(int dni) {
        return alumno_DAO.delete(dni);
    }

    public Alumno findAlumno(int dni) {
        return alumno_DAO.find(dni);
    }

    public boolean alumnoExist(int dni) {
        return alumno_DAO.exist(dni);
    }
    
    public Alumno findInscripcion (int codInsc) {
        return alumno_DAO.findInscripcion(codInsc);
    }   
}
