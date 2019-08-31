package com.example.demo.idutil;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Transactional
@Service
public class IDGenManager {


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long getNextVal2(String sequencename,String schemaname ,int size) throws Exception{
        return new IDDao(sequencename,schemaname).getNextVal2();
    }


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public long getNextVal(String sequencename,String schemaname ,int size) throws Exception{
//        long nextVal=1L;
//
//        dao=new IDDao();
//        dao.setSchemaname(schemaname);
//        dao.setSequencename(sequencename);
//
//        boolean isExist =dao.isSequence();
//        if (!isExist) dao.createSeqTable();
//
//        nextVal=dao.getNextVal();
//        dao.incrementNextVal(nextVal);
//
//        return nextVal;
//    }

}

/*
                CREATE TABLE sequence_orderID( next_val int auto_increment primary key )
                select next_val as id_val from sequence_orderid for update
                update hibernate_sequence set next_val= ? where next_val=?
                SELECT count(*) as cnt FROM Information_schema.tables WHERE table_schema = ? AND table_name = ?

EntityTransaction txn = null;
		try {
			EntityManager entityManager = entityManagerFactory().createEntityManager();
			txn = entityManager.getTransaction();
			txn.begin();
			entityManager.createNativeQuery( "CREATE TABLE FOO (FOO_ID NUMBER)" ).executeUpdate();

			txn.commit();
		}
		catch ( Throwable e ) {
			if ( txn != null && txn.isActive() ) {
				txn.rollback();
			}
			throw e;
		}
 */