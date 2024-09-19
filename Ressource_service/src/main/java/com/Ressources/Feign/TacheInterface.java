package com.Ressources.Feign;


import com.Ressources.Response.TacheResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "taches-service", url = "http://localhost:8082/api/Taches")
public interface TacheInterface {

    @GetMapping("/{id}")
    TacheResponse getTacheById(@PathVariable Long id);
}

