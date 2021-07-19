package Model;
import Dao.CursadoDAO;
import java.util.List;
/**
 *
 * @author User-PC
 */
public class Cursado {

    private int dniAlumno;
    private int codMateria;
    private double nota;
    private CursadoDAO cursado_DAO = new CursadoDAO();

    public Cursado(int dniAlumno, int codMateria, double nota) {
        this.dniAlumno = dniAlumno;
        this.codMateria = codMateria;
        this.nota = nota;
    }

    public Cursado() {
    }

    public int getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(int dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public CursadoDAO getCursado_DAO() {
        return cursado_DAO;
    }

    public void setCursado_DAO(CursadoDAO cursado_DAO) {
        this.cursado_DAO = cursado_DAO;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    //------------------------------
    public boolean createCursado(Cursado cursado) {
        return cursado_DAO.create(cursado);
    }

    public List<Cursado> readCursado() {
        return cursado_DAO.read();
    }

    public boolean updateCursado(Cursado cursado) {
        return cursado_DAO.update(cursado);
    }

    public boolean deleteCursado(int dni) {
        return cursado_DAO.delete(dni);
    }

    public Cursado findCursado(int dni) {
        return cursado_DAO.find(dni);
    }
     public Cursado findMateria(int codMat) {
        return cursado_DAO.findMat(codMat);
    }
}
