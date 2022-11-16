package com.example.clinica.dao;

import com.example.clinica.model.Paciente;
import com.example.clinica.model.Turno;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class TurnoDao implements IDao<Turno> {

    private static final Logger logger= LogManager.getLogger(OdontologoH2.class);

    private final static String SELECT="SELECT * FROM TURNOS";
    private final static String SELECT2="select concat_ws(', ', o.APELLIDO, o.NOMBRE) ODONTOLOGOS, concat_ws(', ', P.APELLIDO, P.NOMBRE) PACIENTES, t.FECHA fecha from TURNOS t\n" +
            "join ODONTOLOGOS o\n" +
            "on t.odontologoId = o.id\n" +
            "join PACIENTES p\n" +
            "on p.id = t.pacienteId\n" +
            "where TURNOS.ID = ?";
    private final static String SELECT_ID="SELECT * FROM TURNOS where id = ?";

    private final static String INSERT="INSERT INTO TURNOS(ODONTOLOGOID, PACIENTEID, FECHA) VALUES(?, ?, ?)";
    private final static String DELETE="DELETE FROM TURNOS where ID = ?";
    private final static String UPDATE="UPDATE TURNOS set FECHA = ? where ID = ?";


    @Override
    public Turno guardar(Turno t) {
        try(var connection=H2Connect.getConnection()){

            var statement=connection.prepareStatement(INSERT);

            statement.setInt(1,t.getOdontologoId());
            statement.setInt(2,t.getPacienteId());
            statement.setTimestamp(3,Timestamp.valueOf(t.getFecha()));
            statement.execute();
            logger.debug("Paciente guardado.");
        }catch (SQLException e){
            e.printStackTrace();
        }return t;

    }


    @Override
    public void eliminar(int id) {
        try(var connection=H2Connect.getConnection()){

            var statement=connection.prepareStatement(DELETE);
            statement.setLong(1,id);
            statement.executeUpdate();
            logger.debug("Paciente eliminado.");
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }

    }



    @Override
    public void modificar(Turno t) {

        try(var connection=H2Connect.getConnection()){

            var update=connection.prepareStatement(UPDATE);
            update.setTimestamp(1,Timestamp.valueOf(t.getFecha()));
            update.setInt(2,t.getId());

            update.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }

    }


    @Override
    public Optional<Turno> buscar(int id) {

        try(var connection=H2Connect.getConnection()){

            var statement=connection.prepareStatement(SELECT_ID);
            statement.setInt(1,id);
            var result=statement.executeQuery();
            while(result.next()){
                var idTurno =result.getInt(1);
               var odontologoId=result.getInt(2);
               var pacienteId=result.getInt(3);
               var fecha=result.getTimestamp(4).toLocalDateTime();
                logger.debug("Turno encontrado");
                return Optional.of(new Turno(idTurno,odontologoId,pacienteId,fecha));
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }
        logger.debug("Turno no encontrado");
        return Optional.empty();
    }



    @Override
    public List<Turno> listar() {

        var turnos=new ArrayList<Turno>();
        try(var connecion=H2Connect.getConnection()){

            var statement=connecion.prepareStatement(SELECT);
            var result=statement.executeQuery();
            while(result.next()){
                var id=result.getInt(1);
                var odontologoId=result.getInt(2);
                var pacienteId=result.getInt(3);
                var fecha=result.getTimestamp(4).toLocalDateTime();
                var turno=new Turno(id,odontologoId,pacienteId,fecha);
                turnos.add(turno);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }return turnos;
    }
}
