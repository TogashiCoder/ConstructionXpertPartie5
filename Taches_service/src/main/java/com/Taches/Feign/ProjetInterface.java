package com.Taches.Feign;

import com.Taches.Response.ProjetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "projet-service", url = "http://localhost:8081/api/Projets")
public interface ProjetInterface {

    @GetMapping("/{id}")
    ProjetResponse getProjectById(@PathVariable("id") Long id);
}

