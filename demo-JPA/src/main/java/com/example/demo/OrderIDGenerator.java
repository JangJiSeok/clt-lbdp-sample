package com.example.demo;

import com.example.demo.idutil.IDGenManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transaction;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;



@Slf4j
@Data
public class OrderIDGenerator  implements IdentifierGenerator {

    //@Autowired
    private IDGenManager idm;
    private static final String sequencename="sequence_orderid";
    private static final String schemaname="world";

    @Override
    //@Transactional(propagation = Propagation.NEVER)
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long iSeq = 0;
        String sSeq="";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddMMmmss");
            /* TODO 2019-08-30
             *  Concatenate Date and Database Sequence(ex. Oracle sequence) by repository service
            *   and
            *   need to make Common Utility for all custom id generator
            *
            * */


            try {
                idm = new IDGenManager();
                iSeq = idm.getNextVal(this.sequencename, this.schemaname,5);
                log.debug( "OrderIDGenerator Seq:" + iSeq);
                session.connection().commit();

            }catch (Exception ex) {
                ex.printStackTrace();
            }

            sSeq=StringUtils.leftPad( Long.toString(iSeq), 5,"0" );

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


}
