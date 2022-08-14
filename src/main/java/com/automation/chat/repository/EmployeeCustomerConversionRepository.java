package com.automation.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.automation.chat.model.EmployeeCustomerConversion;

@Repository
public interface EmployeeCustomerConversionRepository extends JpaRepository<EmployeeCustomerConversion, Long> {
	
	@Query("select conversion from EmployeeCustomerConversion conversion where conversion.employee.id = :employeeId and conversion.customer.id = :customerId")
	public EmployeeCustomerConversion fetchEmployeeCustomerConversion(@Param("employeeId") Long employeeId, @Param("customerId") Long customerId);

	@Query("select conversion from EmployeeCustomerConversion conversion where conversion.employee.id = :employeeId and conversion.customer.id = :customerId and replyByCustomer = :replyByCustomer")
	public EmployeeCustomerConversion fetchLatestEmployeeCustomerConversion(@Param("employeeId") Long employeeId, @Param("customerId") Long customerId, @Param("replyByCustomer") Boolean replyByCustomer);
}
