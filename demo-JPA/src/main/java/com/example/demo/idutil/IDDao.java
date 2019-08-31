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
import org.springframework.stereotype.Service;
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
        log.debug("\n======================================================");
        log.debug("\ndatasource:" + this.dataSource);
        log.debug("\nnJdbcTemplate:" + this.nJdbcTemplate);
        log.debug("\njdbcTemplate:" + this.jdbcTemplate);
        log.debug("\n======================================================");
    }

    /**
     * 이거 하나면 됨....
     * @return
     * @throws Exception
     */
    public long getNextVal2() throws Exception {
        long iR=0L;
        try {
            String query="  select nextval(trim('" + sequencename + "')) as nextValue from dual ";
            log.debug("\n" + query  );
            long iNextVal=jdbcTemplate.queryForObject(query, Long.class);

            log.debug("\n -------------------------------------------------");
            log.debug("\n getNextVal result: true" + iNextVal);
            log.debug("\n -------------------------------------------------");

            iR=iNextVal;

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
            log.debug("\n -------------------------------------------------");
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

            int iRCnt=nJdbcTemplate.update(" update " + this.schemaname + "." + sequencename + " set next_val= :nextVal ",mapSqlParameterSource);

            log.debug("\n -------------------------------------------------");
            log.debug("\n incrementNextVal result:" + iRCnt);

        }catch (Exception ex)  {
            ex.printStackTrace();
            throw ex;
        }
    }

 }


    /*

                CREATE TABLE sequence_orderID( next_val int auto_increment primary key )
                select next_val as id_val from sequence_orderid for update
                update hibernate_sequence set next_val= ? where next_val=?
                SELECT count(*) as cnt FROM Information_schema.tables WHERE table_schema = ? AND table_name = ?


                INSERT INTO sequences VALUES ('sequence_orderid', 0);
                INSERT INTO sequences VALUES ('sequence_productid', 0);


 1. 시퀀스 테이블 생성 (변경하는것 없이 그대로 실행)

CREATE TABLE sequences ( name varchar(32), currval BIGINT UNSIGNED ) ENGINE=InnoDB;

CREATE TABLE sequences ( name varchar(32) primary key, currval BIGINT UNSIGNED ) ENGINE=InnoDB;

2. 시퀀스 프로시저 생성(변경하는것 없이 그대로 실행)
DELIMITER $$
CREATE PROCEDURE `create_sequence`(IN the_name text)
MODIFIES SQL DATA
DETERMINISTIC
BEGIN
    DELETE FROM sequences WHERE name=the_name;
    INSERT INTO sequences VALUES (the_name, 0);
END

3. nextval function 생성(변경하는것 없이 그대로 실행)

 DELIMITER $$
 CREATE FUNCTION `nextval`(the_name varchar(32))
 RETURNS BIGINT UNSIGNED
 MODIFIES SQL DATA
 DETERMINISTIC
 BEGIN
     DECLARE ret BIGINT UNSIGNED;
     UPDATE sequences SET currval=currval+1 WHERE name=the_name;
     SELECT currval INTO ret FROM sequences WHERE name=the_name limit 1;
     RETURN ret;
 END

4. 시퀀스명 입력 및 0값 넣기 ('Statistics_seq' 대신 원하는 시퀀스 명을 넣을 것)

INSERT INTO sequences VALUES ('Statistics_seq', 0);
5. nextval 값 가져오기 ('Statistics_seq' 대신 생성한 시퀀스 명을 넣을 것)

select nextval('Student_seq') as Student_seq from dual;

* 시퀀스를 하나 생성한 후 추가로 시퀀스를 생성할 시 3번부터 진행하시면 됩니다.
@ 시퀀스 생성 방법은 하기의 내용 처럼 이쁜우리님 티스토리에서 가져왔습니다.


     */