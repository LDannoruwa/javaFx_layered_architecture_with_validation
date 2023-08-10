package service.custom;

import java.util.ArrayList;

import dto.CustomerDto;

public interface CustomerService {
    public boolean saveCustomer(CustomerDto customerDto) throws Exception;
    public boolean deleteCustomer(Integer id) throws Exception;
    public boolean updateCustomer(CustomerDto customerDto) throws Exception;
    public CustomerDto getCustomer(Integer id) throws Exception;
    public ArrayList<CustomerDto> getAllCustomer() throws Exception;

    public ArrayList<CustomerDto> getAllCustomerID() throws Exception;
    
}
