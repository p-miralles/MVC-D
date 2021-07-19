package Model;

import Dao.MateriaDAO;
import java.util.List;
/**
 *
 * @author User-PC
 */
public class Materia {

    private int codigo;
    private String nombre;
    private int dniProfesor;
    private MateriaDAO materia_DAO = new MateriaDAO();

    public Materia(int codigo, String nombre, int dniProfesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dniProfesor = dniProfesor;
    }

    public Materia() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(int dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public MateriaDAO getMateria_DAO() {
        return materia_DAO;
    }

    public void setMateria_DAO(MateriaDAO materia_DAO) {
        this.materia_DAO = materia_DAO;
    }


    //----------------------------

    public boolean createMateria(Materia materia) {
        return materia_DAO.create(materia);
    }

    public List<Materia> readMaterias() {
        return materia_DAO.read();
    }

    public boolean updateMateria(Materia materia) {
        return materia_DAO.update(materia);
    }

    public boolean deleteMateria(int matCod) {
        return materia_DAO.delete(matCod);
    }

    public boolean materiaExist(int matCod) {
        return materia_DAO.exist(matCod);
    }

    public Materia findMateria(int matCod ) {
        return materia_DAO.find(matCod);
    }
    
}
