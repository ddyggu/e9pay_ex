package com.e9pay.controller;

import com.e9pay.entity.*;
import com.e9pay.repository.CustomerRepository;
import com.e9pay.repository.DealingRepository;
import com.e9pay.repository.LoginRepository;
import com.e9pay.repository.OutboundRemittanceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class loginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private OutboundRemittanceRepository outboundRemittanceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DealingRepository dealingRepository;


    @GetMapping(value="/index")
    public String moveIndex(Model model, Map<String, Object> param) {
        return "index";
    }

    @GetMapping(value="/login")
    public String moveLogin(Model model, Map<String, Object> param) {
        List<AdminUser> adminUsers = loginRepository.findAll();
        AdminUser adminUser = loginRepository.findById("ddyggu").map(user -> { user.setEmpId(user.getEmpId().toUpperCase(Locale.ROOT)); return user;}).get();
        model.addAttribute("user", adminUser);
        return "/auth/login";
    }

    @GetMapping(value="/test")
    public String test(Model model, Map<String, Object> param) {

        Customer cu = new Customer();

        cu.setFirstName("Chan");
        cu.setLastName("Von");
        cu.setSubColumn1("e9pay");
        cu.setSubColumn2(new BigDecimal("10000"));

        customerRepository.saveAndFlush(cu);

        OutboundRemittance ob = new OutboundRemittance();
        Exchange ex = new Exchange();

        ex.setUsdAmount(new BigDecimal("100.00"));
        ex.setUsdRate(new BigDecimal("1310.00"));
        ob.setExchange(ex);

        Dealing de = new Dealing();
        de.setAmount(new BigDecimal("10000000"));
        de.setCustomer(cu);
        //ob.setDealing(de);
        //ob.setCustomerId(cu.getId());

        dealingRepository.save(de);
        outboundRemittanceRepository.saveAndFlush(ob);

        OutboundRemittance ob2 = new OutboundRemittance();
        Exchange ex2 = new Exchange();

        ex2.setUsdAmount(new BigDecimal("100.00"));
        ex2.setUsdRate(new BigDecimal("1310.00"));
        ob2.setExchange(ex2);
        //ob2.setCustomerId(cu.getId());

        Dealing de2 = new Dealing();
        de2.setAmount(new BigDecimal("10000000"));
        de2.setCustomer(cu);
        //ob2.setDealing(de2);

        dealingRepository.save(de2);
        outboundRemittanceRepository.saveAndFlush(ob2);

        Customer qu = customerRepository.findByFirstName("Chan");
        System.out.println(qu);


        return "/auth/login";
    }

    @GetMapping(value="/test2")
    public String test2(Model model, Map<String, Object> param) {

        Customer cu = new Customer();

        cu.setFirstName("Chan");
        cu.setLastName("Von");
        cu.setSubColumn1("e9pay");
        cu.setSubColumn2(new BigDecimal("10000"));

        customerRepository.saveAndFlush(cu);
        return "/auth/login";
    }


    @GetMapping(value="/test3")
    @Transactional
    public String test3(Model model, Map<String, Object> param) {

        Customer customer = customerRepository.findById("24020512113500001001").orElseThrow();

        //List<OutboundRemittance> outList = customer.getOutboundRemittanceList();

        Dealing de2 = new Dealing();
        de2.setAmount(new BigDecimal("10000000"));
        de2.setCustomer(customer);

        entityManager.persist(de2);

        OutboundRemittance ob3 = new OutboundRemittance();
        Exchange ex2 = new Exchange();

        ex2.setUsdAmount(new BigDecimal("100.00"));
        ex2.setUsdRate(new BigDecimal("1310.00"));
        ob3.setExchange(ex2);
        //ob3.setDealing(de2);

        entityManager.persist(ob3);

        //outList.add(ob3);
        //customer.setOutboundRemittanceList(outList);
        //ob3.setCustomer(customer);

        outboundRemittanceRepository.saveAndFlush(ob3);
        // outList.forEach(o -> o.getExchange().setUsdAmount(new BigDecimal("400")));

        return "/auth/login";
    }

}
