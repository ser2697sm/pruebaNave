package com.prueba.nave.commons;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NegativeIdAspect {

    private static final Logger logger = LoggerFactory.getLogger(NegativeIdAspect.class);

    // Intercepta el método 'findById' que pertenece a la clase en la que se encuentra el servicio
    @Before("execution(* com.prueba.nave.service.NaveServiceImpl.findById(long)) && args(id)")
    public void logNegativeId(long id) {
        if (id < 0) {
            // Aquí puedes usar cualquier framework de logging, como log4j, slf4j, etc.
            logger.warn("¡Alerta! Se intentó buscar una nave con un id negativo: " + id);
        }
    }
}
