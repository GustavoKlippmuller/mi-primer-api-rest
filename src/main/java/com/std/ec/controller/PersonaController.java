package com.std.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PersonaController {

    /**@Autowired
    private IPersona personaService;

    @DeleteMapping("persona/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Persona persona = personaService.findById(id);
            personaService.delete(persona);
            return new ResponseEntity<>(persona, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt) {
            response.put("mensaje", exDt.getMessage());
            response.put("persona", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



*/
}
