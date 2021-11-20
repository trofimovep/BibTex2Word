package com.textoword.api

import com.textoword.service.TransformService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class TexToWordController(val transformService: TransformService) {

    @CrossOrigin
    @PostMapping("/transform")
    fun transform(@RequestBody bibItems: String) = ResponseEntity.ok(transformService.transform(bibItems))

}