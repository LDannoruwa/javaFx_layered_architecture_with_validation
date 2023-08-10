package service;

import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ItemServiceImpl;
import service.custom.impl.OrderDetailServiceImpl;
import service.custom.impl.OrderServiceImpl;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return (serviceFactory == null) ? (serviceFactory = new ServiceFactory()) : serviceFactory;
    }

    public enum ServiceType {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(ServiceType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case ITEM:
                return (T) new ItemServiceImpl();
            case ORDER:
                return (T) new OrderServiceImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailServiceImpl();
            default:
                return null;
        }
    }
}
