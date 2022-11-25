package com.example.demo1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
class Demo1Application

fun main(args: Array<String>) {
    runApplication<Demo1Application>(*args)
}

@RestController
@RequestMapping("api/v1")
class TestController(val demo2Service: Demo2Service) {

    @GetMapping
    fun hello(): String {
        return "Hello from Demo1 ${demo2Service.hello()}"
    }

    @GetMapping("message")
    fun message(id:Long):MessageDto{
        val responseFromDemo2 = demo2Service.message(id)
        return MessageDto(responseFromDemo2.id, responseFromDemo2.message, Date().time)
    }
}
