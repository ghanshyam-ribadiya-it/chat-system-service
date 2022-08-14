package com.automation.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automation.chat.dto.CustomerResponse;
import com.automation.chat.model.Customer;
import com.automation.chat.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public List<CustomerResponse> findAllCustomer() {
		List<CustomerResponse> customerResponseList = new ArrayList<>();

		List<Customer> customerList = customerRepository.findAll();
		CustomerResponse customerResponse = null;
		for (Customer customer : customerList) {
			customerResponse = new CustomerResponse(customer.getId(), customer.getName(), customer.getNumber());
			customerResponseList.add(customerResponse);
		}

		return customerResponseList;
	}
}
