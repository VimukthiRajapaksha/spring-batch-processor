package com.demo.batchprocessor.processor;

import com.demo.batchprocessor.bean.CustomerInfoBean;
import com.demo.batchprocessor.util.CustomerInfoUtil;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerInfoProcessor implements ItemProcessor<CustomerInfoBean, CustomerInfoBean> {

    @Autowired
    CustomerInfoUtil customerInfoUtil;

    @Override
    public CustomerInfoBean process(CustomerInfoBean customerInfoBean) throws Exception {
        customerInfoBean.setCardNumber(customerInfoUtil.hashCardNumber(customerInfoBean.getCardNumber()));
        customerInfoBean.setEmail(customerInfoBean.getEmail().toUpperCase());

        return customerInfoBean;
    }


}
