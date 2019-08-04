package me.zhengjie.modules.zx.service;

import me.zhengjie.modules.zx.service.dto.CustomerDto;

import java.util.List;
import java.util.Map;

public interface ZxService {

    List<CustomerDto> getCustomerList();

    Map<String, Object> getChartData();

    List<CustomerDto> getCustomers();

    Map<String, Object> getOrderCount();

}
