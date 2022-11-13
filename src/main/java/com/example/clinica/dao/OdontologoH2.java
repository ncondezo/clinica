package com.example.clinica.dao;

import com.example.clinica.model.Odontologo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class OdontologoH2 implements IDao<Odontologo> {

    private static final Logger logger= LogManager.getLogger(OdontologoH2.class);

    private final static String SELECT_ID="SELECT * FROM ODONTOLOGOS where id = ?";
    private final static String SELECT="SELECT * FROM ODONTOLOGOS";
    private final static String INSERT="INSERT INTO ODONTOLOGOS(APELLIDO, NOMBRE, MATRICULA) VALUES(?, ?, ?)";
    private final static String DELETE="DELETE FROM ODONTOLOGOS where ID = ?";
    private final static String UPDATE="UPDATE ODONTOLOGOS set APELLIDO = ?, NOMBRE = ?, MATRICULA = ? where ID = ?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        try(var connection=H2Connect.getConnection()){

            var statement=connection.prepareStatement(INSERT);

            statement.setString(1, odontologo.apellido());
            statement.setString(2, odontologo.nombre());
            statement.setString(3,odontologo.matricula());
            statement.execute();
            logger.debug("Profesional guardado.");
        }catch (SQLException e){
            e.printStackTrace();
        }return odontologo;

    }

    @Override
    public void eliminar(int id) {
        try(var connection=H2Connect.getConnection()){

            var statement=connection.prepareStatement(DELETE);
            statement.setLong(1,id);
            statement.executeUpdate();
            logger.debug("Profesional eliminado.");
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }

    }

    @Override
    public void modificar(Odontologo odontologo) {
        try(var connection=H2Connect.getConnection()){

            var update=connection.prepareStatement(UPDATE);
            update.setString(1,odontologo.apellido());
            update.setString(2,odontologo.nombre());
            update.setString(3,odontologo.matricula());
            update.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }

    }

    @Override
    public Optional<Odontologo> buscar(int id) {
       try(var connection=H2Connect.getConnection()){

           var statement=connection.prepareStatement(SELECT_ID);
           statement.setInt(1,id);
           var result=statement.executeQuery();
           while(result.next()){
               String apellido=result.getString(2);
               String nombre=result.getString(3);
               String matricula=result.getString(4);
               logger.debug("Profesional encontrado");
               return Optional.of(new Odontologo(id,apellido,nombre,matricula));
           }
       }catch (SQLException e){
           e.printStackTrace();
           logger.error(e);
       }
       logger.debug("Profesional no encontrado");
       return Optional.empty();
    }

    @Override
    public List<Odontologo> listar() {
        var ondontologos=new ArrayList<Odontologo>();
        try(var connecion=H2Connect.getConnection()){

            var statement=connecion.prepareStatement(SELECT);
            var result=statement.executeQuery();
            while(result.next()){
                var id=result.getInt(1);
                var apellido=result.getString(2);
                var nombre=result.getString(3);
                var matricula=result.getString(3);
                var ondontologo=new Odontologo(id,apellido,nombre,matricula);
                ondontologos.add(ondontologo);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }return ondontologos;
    }
}
