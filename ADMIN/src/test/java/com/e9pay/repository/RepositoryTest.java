package com.e9pay.repository;

import com.e9pay.entity.Customer;
import com.e9pay.entity.Dealing;
import com.e9pay.entity.OutboundRemittance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private OutboundRemittanceRepository outboundRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DealingRepository dealingRepository;


    //@Test
    public void updateTest() {
       // Applying Transactional but in Junit, it forcefully rollbacked, update X
       OutboundRemittance out = outboundRepository.findById("24020201372400000001").orElseThrow();
       out.getExchange().setUsdAmount(new BigDecimal("900"));
       System.out.println(out);
    }

    //@Test
    @Rollback(value = false)
    public void oneToManyTest() {


    }


    @Test
    public void OneToOneTest() {
        //Dealing de1 = dealingRepository.findById("24020201372400000001").orElseThrow();
        //System.out.println(de1);

        OutboundRemittance ob = outboundRepository.findById("24020201372400000001").orElseThrow();
        System.out.println(ob);
    }

}
