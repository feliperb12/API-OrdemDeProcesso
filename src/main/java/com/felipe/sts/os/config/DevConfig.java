package com.felipe.sts.os.config;


import com.felipe.sts.os.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

   /* @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl = "create";*/

    @Bean
    public void instanciaDB() {
       // if(ddl.equals("create")){
       // }
        //return false;
        this.dbService.instanciaDB();
    }
    }

