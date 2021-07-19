package Model;
import Dao.CarreraDAO;
import java.util.List;
/**
 *
 * @author User-PC
 */
public class Carrera {

    private int cod_Carrera;
    private String nombre;
    private String duracion;
    private CarreraDAO carrera_DAO = new CarreraDAO();

    public Carrera() {
    }

    public int getCod_Carrera() {
        return cod_Carrera;
    }

    public void setCod_Carrera(int cod_Carrera) {
        this.cod_Carrera = cod_Carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    //----------------------------------------
    public boolean createCarrera(Carrera carrera) {
        return carrera_DAO.create(carrera);
    }

    //READ
    public List<Carrera> readCarrera() {
        return carrera_DAO.read();
    }

    //UPDATE
    public boolean updateCarrera(Carrera carrera) {
        return carrera_DAO.update(carrera);
    }

    //DELETE
    public boolean deleteCarrera(int codCarr) {
        return carrera_DAO.delete(codCarr);
    }

    public Carrera findCarrera(int codCarr) {
        return carrera_DAO.find(codCarr);
    }

    public boolean carreraExist(int codCarr) {
        return carrera_DAO.exist(codCarr);
    }
}
