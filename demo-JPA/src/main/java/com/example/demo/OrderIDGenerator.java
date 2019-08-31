package com.example.demo;

import com.example.demo.idutil.BeanUtils;
import com.example.demo.idutil.IDGenManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.transaction.Transaction;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

/* TODO 2019-08-30
 *  Concatenate Date and Database Sequence(ex. Oracle sequence) by repository service
 *   and
 *   need to make Common Utility for all custom id generator
 * */


@Slf4j
@Data
@Service
public class OrderIDGenerator  implements IdentifierGenerator {

    private static final String sequencename="sequence_orderid";
    private static final String schemaname="world";


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long iSeq = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddMMmmss");

            try {
                iSeq = getNextVal();
            }catch (Exception ex) {
                ex.printStackTrace();
            }

            String sSeq=StringUtils.leftPad( Long.toString(iSeq), 5,"0" );

            // TODO: 2019-08-30
            log.debug("\n------------------------------------------------------------");
            log.debug("\n" + Long.parseLong(simpleDateFormat.format(new Date())) + sSeq );
            log.debug("\n------------------------------------------------------------");
            return Long.parseLong(simpleDateFormat.format(new Date()) + sSeq )    ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Autowired
    public DataSource dataSource;
    @Autowired
    public NamedParameterJdbcTemplate nJdbcTemplate;
    @Autowired
    public JdbcTemplate jdbcTemplate;

    private void setJDBCTemplete(){
        this.dataSource = (DataSource) BeanUtils.getBean(DataSource.class);
        this.nJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        log.debug("\n======================================================");
        log.debug("\ndatasource:" + this.dataSource);
        log.debug("\nnJdbcTemplate:" + this.nJdbcTemplate);
        log.debug("\njdbcTemplate:" + this.jdbcTemplate);
        log.debug("\n======================================================");
    }
    private void printJDBCTemplete(){
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long getNextVal() throws Exception {
        setJDBCTemplete();
        printJDBCTemplete();
        String query="  select nextval(trim('" + sequencename + "')) as nextValue from dual ";
        log.debug("\n" + query  );
        return  jdbcTemplate.queryForObject(query, Long.class);
    }


}
