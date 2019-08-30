package com.example.demo;

import com.sun.xml.internal.bind.v2.TODO;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderIDGenerator  implements IdentifierGenerator {
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

            // TODO: 2019-08-30  

            return Long.parseLong(simpleDateFormat.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
