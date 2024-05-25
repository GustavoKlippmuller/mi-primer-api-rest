package com.std.ec.controller;

import com.std.ec.model.entity.Persona;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class InputController {

    @GetMapping("without/param")
    public String withParam() { return "This is a request without parameter"; }

    @GetMapping("inputQuery")
    public String inputQuery(@RequestParam String name, @RequestParam(required = false) Integer age) { return "The name is: " + name + ", age is: " + age; }

    @GetMapping("inputParameter/{name}")
    public String inputParameter(@PathVariable String name) { return "The name is: " + name; }

    @PostMapping("inputBody")
    public String inputBody(@RequestBody Map<String, String> body) { return "The name is: " + body.get("name"); }

    @PostMapping("inputPersona")
    public String inputPersona(@RequestBody Persona persona) { return "The name is: " + persona.getNombre(); }

}
