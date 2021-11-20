package com.textoword

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TextowordApplication

fun main(args: Array<String>) {
	runApplication<TextowordApplication>(*args)
}
