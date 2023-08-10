package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.CustomerDao;
import dto.CustomerDto;
import entity.Customer;
import service.custom.CustomerService;

public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws Exception {
        Customer customer = new Customer(customerDto.getCustomer_id(), customerDto.getCustomer_name(), customerDto.getCustomer_address(), customerDto.getCustomer_Salary());
        return customerDao.save(customer);
    }

    @Override
    public boolean deleteCustomer(Integer id) throws Exception {
        return customerDao.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws Exception {
        return customerDao.update(new Customer(customerDto.getCustomer_id(), customerDto.getCustomer_name(), customerDto.getCustomer_address(), customerDto.getCustomer_Salary()));
    }

    @Override
    public CustomerDto getCustomer(Integer id) throws Exception {
        Customer customer = customerDao.get(id);

        if(customer != null){
            return new CustomerDto(customer.getCustomer_id(), customer.getCustomer_name(), customer.getCustomer_address(),
            customer.getCustomer_Salary());
        }

        return null;
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws Exception {
        ArrayList<Customer> list = customerDao.getAll();
        ArrayList<CustomerDto> dtoList = new ArrayList<>();

        for (Customer c : list) {
            dtoList.add(new CustomerDto(c.getCustomer_id(), c.getCustomer_name(), c.getCustomer_address(), c.getCustomer_Salary()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomerID() throws Exception {
        ArrayList<Customer> list = customerDao.getCustomerID();
        ArrayList<CustomerDto> dtoList = new ArrayList<>();

        for (Customer c : list) {
            dtoList.add(new CustomerDto(c.getCustomer_id()));
        }
        return dtoList;
    }
    
}
