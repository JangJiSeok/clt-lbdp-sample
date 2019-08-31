package com.example.demo.idutil;

import lombok.Data;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

@Repository
@Slf4j
@Data
public class IDDao {

    private String sequencename;
    private String schemaname;

    protected DataSource dataSource;
    protected NamedParameterJdbcTemplate nJdbcTemplate;
    protected JdbcTemplate jdbcTemplate;

    public IDDao() {
        setJDBCTemplete();
    }
    public IDDao(String sequencename, String schemaname) {
        this.sequencename = sequencename;
        this.schemaname = schemaname;
        setJDBCTemplete();
    }

    private void setJDBCTemplete(){
        this.dataSource = (DataSource)BeanUtils.getBean(DataSource.class);
        this.nJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.NESTED)
    public boolean isSequence() throws Exception {
        boolean isSeqExit=false ;
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("schemaname", this.schemaname);
            mapSqlParameterSource.addValue("sequencename", this.sequencename);

            int iCnt = nJdbcTemplate.queryForObject("SELECT count(*) as cnt FROM Information_schema.tables WHERE table_schema =:schemaname AND table_name = :sequencename "
                    , mapSqlParameterSource
                    , Integer.class);

            if (iCnt > 0) isSeqExit = true;
            log.debug("\n -------------------------------------------------");
            log.debug("\n isSequence result:" + iCnt);
        }catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return  isSeqExit;
    }

    /**
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean createSeqTable() throws Exception {
        boolean isSuccess=false ;
        try {
            jdbcTemplate.execute(" CREATE TABLE " + sequencename + " ( next_val int auto_increment primary key ) ");
            jdbcTemplate.execute(" INSERT INTO " + this.schemaname + "." + sequencename + " (next_val) values( 0 ) ");
            isSuccess=true;
            log.debug("\n -------------------------------------------------");
            log.debug("\n createSeqTable result: true");

        }catch (Exception ex)  {
            ex.printStackTrace();
            throw ex;
        }
        return  isSuccess;
    }


    /**
     * @return
     * @throws Exception
     */
    public long getNextVal() throws Exception {
        long iR=0L;
        try {
                long iNextVal=jdbcTemplate.queryForObject(" select next_val as id_val from " + sequencename + " for update ", Long.class);
            iR=iNextVal+1L;
            log.debug("\n -------------------------------------------------");
            log.debug("\n getNextVal result: true" + iR);

        }catch (Exception ex)  {
            ex.printStackTrace();
            throw ex;
        }

        return  iR;
    }

    /**
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.NESTED)
    public void incrementNextVal(long nextVal) throws Exception {
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("nextVal", nextVal);

            int iRCnt=nJdbcTemplate.update(" update hibernate_sequence set next_val= :nextVal where next_val= :nextVal -1 ",mapSqlParameterSource);

            log.debug("\n -------------------------------------------------");
            log.debug("\n incrementNextVal result:" + iRCnt);

        }catch (Exception ex)  {
            ex.printStackTrace();
            throw ex;
        }
    }

    /*

                CREATE TABLE sequence_orderID( next_val int auto_increment primary key )
                select next_val as id_val from sequence_orderid for update
                update hibernate_sequence set next_val= ? where next_val=?
                SELECT count(*) as cnt FROM Information_schema.tables WHERE table_schema = ? AND table_name = ?

     */


}
