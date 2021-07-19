package Model;
import Dao.InscripcionDAO;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author User-PC
 */
public class Inscripcion {

    private int codInscripcion; //ID
    private String nombre;
    private Date fecha;
    private int codCarrera;
    private InscripcionDAO inscripcion_DAO = new InscripcionDAO();

    public Inscripcion(int codInscripcion, String nombre, Date fecha, int codCarrera) {
        this.codInscripcion = codInscripcion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.codCarrera = codCarrera;
    }

    public Inscripcion() {
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodInscripcion() {
        return codInscripcion;
    }

    public void setCodInscripcion(int codInscripcion) {
        this.codInscripcion = codInscripcion;
    }

    public int getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(int codCarrera) {
        this.codCarrera = codCarrera;
    }

    public InscripcionDAO getInscripcion_DAO() {
        return inscripcion_DAO;
    }

    public void setInscripcion_DAO(InscripcionDAO inscripcion_DAO) {
        this.inscripcion_DAO = inscripcion_DAO;
    }


    //-----------------------------------------
    public boolean createInscripcion(Inscripcion inscripcion) {
        return inscripcion_DAO.create(inscripcion);
    }

    public List<Inscripcion> readInscripciones() {
        return inscripcion_DAO.read();
    }

    public boolean updateInscripcion(Inscripcion codInsc) {
        return inscripcion_DAO.update(codInsc);
    }

    public boolean deleteInscripcion(int codInsc) {
        return inscripcion_DAO.delete(codInsc);
    }
    
    public Inscripcion findInscripcion(int codInsc ) {
        return inscripcion_DAO.find(codInsc);
    }

    public Inscripcion findCarrera(int codCarr ) {
        return inscripcion_DAO.findCarrera(codCarr);
    }
    
    public Inscripcion findLast() {
        return inscripcion_DAO.findLast();
    }
}
