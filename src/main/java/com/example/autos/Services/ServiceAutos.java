package com.example.autos.Services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.autos.Connections.ConexionMariaDB;
import com.example.autos.Models.ModelAutoReserva;

@Service
public class ServiceAutos {

    public List<String> listAutos() throws Exception {

        List<String> list = new ArrayList<>();

        String query = "{CALL logic_studio.sp_autos_lista()}";

        try (Connection mariaDB = ConexionMariaDB.getConexion();
                CallableStatement cst = mariaDB.prepareCall(query);) {

            ResultSet rs = cst.executeQuery();

            while (rs.next()) {

                list.add(rs.getString("placa"));
            }

        } catch (Exception e) {
            throw new Exception();
        }

        return list;
    }

    public List<ModelAutoReserva> getHistorialByPlaca(String placa) throws Exception {

        List<ModelAutoReserva> list = new ArrayList<>();

        String query = "{CALL logic_studio.sp_auto_placa(?)}";

        try (Connection mariaDB = ConexionMariaDB.getConexion();
                CallableStatement cst = mariaDB.prepareCall(query);) {

            cst.setString(1, placa);

            ResultSet rs = cst.executeQuery();

            while (rs.next()) {

                ModelAutoReserva resultado = new ModelAutoReserva();

                resultado.setPlaca(rs.getString("placa"));
                resultado.setEstado(rs.getString("estado"));
                resultado.setCita(rs.getString("cita"));

                list.add(resultado);
            }

        } catch (Exception e) {
            throw new Exception();
        }

        return list;
    }

    public List<ModelAutoReserva> crearReserva(ModelAutoReserva reserva) throws Exception {

        List<ModelAutoReserva> list = new ArrayList<>();

        String query = "{CALL logic_studio.sp_crear_reserva(?,?,?)}";

        try (Connection mariaDB = ConexionMariaDB.getConexion();
                CallableStatement cst = mariaDB.prepareCall(query);) {

            cst.setString(1, reserva.getPlaca());
            cst.setString(2, reserva.getEstado());
            cst.setString(3, reserva.getCita());
            cst.execute();

        } catch (Exception e) {
            throw new Exception();
        }

        return list;
    }

    public List<String> generarCalendario() {
        List<String> timeSlots = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now;

        if (now.getDayOfWeek() == DayOfWeek.SATURDAY) {
            startDate = now.plusDays(2);
        } else if (now.getDayOfWeek() == DayOfWeek.SUNDAY) {
            startDate = now.plusDays(1);
        }

        LocalDateTime endDate = startDate.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        generarFechasPorDia(timeSlots, startDate, formatter);
        generarFechasPorDia(timeSlots, endDate, formatter);

        return timeSlots;
    }

    private void generarFechasPorDia(List<String> timeSlots, LocalDateTime date, DateTimeFormatter formatter) {
        for (LocalDateTime time = date.with(LocalTime.of(8, 0)); !time
                .isAfter(date.with(LocalTime.of(13, 30))); time = time.plusMinutes(30)) {

            timeSlots.add(time.format(formatter));
        }
    }

}
