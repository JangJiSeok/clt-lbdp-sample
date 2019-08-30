package com.example.demo;

import com.example.demo.idutil.IDGenManager;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderIDGenerator  implements IdentifierGenerator {

    //@Autowired
    private IDGenManager idm;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddMMmmss");
            /* TODO 2019-08-30
             *  Concatenate Date and Database Sequence(ex. Oracle sequence) by repository service
            *   and
            *   need to make Common Utility for all custom id generator
            *
            * */
            try {
                String sequencename="hibernate_sequence";
                String schemaname="world";

                idm = new IDGenManager();
                idm.getNextVal(sequencename, schemaname,5);

            }catch (Exception ex) {
                ex.printStackTrace();
            }

            // TODO: 2019-08-30
            /*
                CREATE TABLE sequence_orderID( next_val int auto_increment primary key )
                select next_val as id_val from sequence_orderid for update
                update hibernate_sequence set next_val= ? where next_val=?
                SELECT count(*) as cnt FROM Information_schema.tables WHERE table_schema = ? AND table_name = ?
             */

            return Long.parseLong(simpleDateFormat.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
