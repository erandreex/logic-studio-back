package com.example.autos.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.autos.Models.ModelAutoReserva;
import com.example.autos.Models.ModelGeneralResponse;
import com.example.autos.Services.ServiceAutos;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("autos")
@RequiredArgsConstructor
public class ControllerAutos {

    private final ServiceAutos serviceautos;

    @GetMapping("/lista")
    public ResponseEntity<ModelGeneralResponse> getList() {
        ModelGeneralResponse resp = new ModelGeneralResponse();
        Map<String, Object> data = new HashMap<>();

        try {
            data.put("Placas", serviceautos.listAutos());
            resp.setOk(true);
            resp.setCode(HttpStatus.OK.value());
            resp.setStatus(HttpStatus.OK);
            resp.setMessage("Se han obtenido la lista de autos exitosamente!");
            resp.setData(data);

        } catch (Exception e) {

            resp.setOk(false);
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            resp.setMessage("Ha ocurrido un error!");
            resp.setData(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(resp);

    }

    @GetMapping("/historial/{placa}")
    public ResponseEntity<ModelGeneralResponse> getList(@PathVariable String placa) {
        ModelGeneralResponse resp = new ModelGeneralResponse();
        Map<String, Object> data = new HashMap<>();

        try {
            data.put("Historial", serviceautos.getHistorialByPlaca(placa));
            resp.setOk(true);
            resp.setCode(HttpStatus.OK.value());
            resp.setStatus(HttpStatus.OK);
            resp.setMessage("Se han obtenido la lista de autos exitosamente!");
            resp.setData(data);

        } catch (Exception e) {

            resp.setOk(false);
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            resp.setMessage("Ha ocurrido un error!");
            resp.setData(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(resp);

    }

    @PostMapping("/reserva")
    public ResponseEntity<ModelGeneralResponse> create(@RequestBody ModelAutoReserva body) {
        ModelGeneralResponse resp = new ModelGeneralResponse();
        Map<String, Object> data = new HashMap<>();

        try {

            serviceautos.crearReserva(body);
            resp.setOk(true);
            resp.setCode(HttpStatus.OK.value());
            resp.setStatus(HttpStatus.OK);
            resp.setMessage("Se ha registrado exitosamente!");
            resp.setData(data);

        } catch (Exception e) {

            resp.setOk(false);
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            resp.setMessage("Ha ocurrido un error!");
            resp.setData(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(resp);

    }

    @GetMapping("/calendario")
    public ResponseEntity<ModelGeneralResponse> calendario() {
        ModelGeneralResponse resp = new ModelGeneralResponse();
        Map<String, Object> data = new HashMap<>();

        try {

            data.put("Calendario", serviceautos.generarCalendario());
            resp.setOk(true);
            resp.setCode(HttpStatus.OK.value());
            resp.setStatus(HttpStatus.OK);
            resp.setMessage("Se han obtenido la lista de autos exitosamente!");
            resp.setData(data);

        } catch (Exception e) {

            resp.setOk(false);
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            resp.setMessage("Ha ocurrido un error!");
            resp.setData(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(resp);

    }
}
