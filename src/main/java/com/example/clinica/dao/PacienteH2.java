package com.example.clinica.dao;

import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PacienteH2 implements IDao<Paciente>{

    private static final Logger logger= LogManager.getLogger(OdontologoH2.class);
    private final static String SELECT_ID="SELECT * FROM PACIENTES where id = ?";
    private final static String SELECT="SELECT * FROM PACIENTES";
    private final static String INSERT="INSERT INTO PACIENTES(APELLIDO, NOMBRE, DNI,DOMICILIO,ALTA) VALUES(?, ?, ?, ?, ?)";
    private final static String DELETE="DELETE FROM PACIENTES where ID = ?";
    private final static String UPDATE="UPDATE PACIENTES set APELLIDO = ?, NOMBRE = ?, DNI = ?, DOMICILIO = ?, ALTA = ? where ID = ?";

    @Override
    public Paciente guardar(Paciente p) {
        try(var connection=H2Connect.getConnection()){

            var statement=connection.prepareStatement(INSERT);

            statement.setString(1, p.getApellido());
            statement.setString(2, p.getNombre());
            statement.setString(3,p.getDni());
            statement.setString(4,p.getDomicilio());
            statement.setDate(5,java.sql.Date.valueOf(p.getAlta()));
            statement.execute();
            logger.debug("Paciente guardado.");
        }catch (SQLException e){
            e.printStackTrace();
        }return p;

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
    public void modificar(Paciente p) {
        try(var connection=H2Connect.getConnection()){

            var update=connection.prepareStatement(UPDATE);
            update.setString(1,p.getApellido());
            update.setString(2,p.getNombre());
            update.setString(3, p.getDni());
            update.setString(4,p.getDomicilio());
            update.setDate(5,java.sql.Date.valueOf(p.getAlta()));
            update.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }

    }

    @Override
    public Optional<Paciente> buscar(int id) {
        try(var connection=H2Connect.getConnection()){

            var statement=connection.prepareStatement(SELECT_ID);
            statement.setInt(1,id);
            var result=statement.executeQuery();
            while(result.next()){
                String apellido=result.getString(2);
                String nombre=result.getString(3);
                String dni=result.getString(4);
                String domicilio=result.getString(5);
                LocalDate alta=result.getDate(6).toLocalDate();
                logger.debug("Profesional encontrado");
                return Optional.of(new Paciente(id,apellido,nombre,dni,domicilio,alta));
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }
        logger.debug("Profesional no encontrado");
        return Optional.empty();
    }

    @Override
    public List<Paciente> listar() {
        var pacientes=new ArrayList<Paciente>();
        try(var connecion=H2Connect.getConnection()){

            var statement=connecion.prepareStatement(SELECT);
            var result=statement.executeQuery();
            while(result.next()){
                var id=result.getInt(1);
                var apellido=result.getString(2);
                var nombre=result.getString(3);
                var dni=result.getString(4);
                var domicilio=result.getString(5);
                var alta=result.getDate(6).toLocalDate();
                var paciente=new Paciente(id,apellido,nombre,dni,domicilio,alta);
                pacientes.add(paciente);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }return pacientes;
    }

}
