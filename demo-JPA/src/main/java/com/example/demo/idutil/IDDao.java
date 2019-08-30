package com.example.demo.idutil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

@Repository
@Slf4j
public class IDDao {

    public boolean isSequence(String sequencename,String schemaname) throws Exception {
        boolean isSeqExit=false ;

        DataSource dataSource = (DataSource)BeanUtils.getBean(DataSource.class);
        NamedParameterJdbcTemplate jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("schemaname", schemaname);
        mapSqlParameterSource.addValue("sequencename", sequencename);

        int iCnt=jdbcTemplate.queryForObject("SELECT count(*) as cnt FROM Information_schema.tables WHERE table_schema =:schemaname AND table_name = :sequencename "
                , mapSqlParameterSource
                ,Integer.class);

        if (iCnt > 0) isSeqExit=true;
        System.out.println(" isSequence result:"+iCnt);
        log.debug(" -------------------------------------------------");
        log.debug(" isSequence result:"+iCnt);


        return  isSeqExit;
    }
}
