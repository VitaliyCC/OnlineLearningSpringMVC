package com.learning.spring.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class InitDb {

    @Autowired
    public InitDb() throws IOException {
        Resource resource = new ClassPathResource("dataBase.sql");
        File script = resource.getFile();
        ScriptRunner runner;
        runner = new ScriptRunner(JDBC.getInstance().getConnection());
        runner.runScript(new FileReader(script));
    }
}
