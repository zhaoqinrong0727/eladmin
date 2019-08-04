package me.zhengjie.modules.zx.service.impl;

import me.zhengjie.modules.security.security.JwtUser;
import me.zhengjie.modules.zx.domain.Customer;
import me.zhengjie.modules.zx.domain.Order;
import me.zhengjie.modules.zx.repository.CustomerRepository;
import me.zhengjie.modules.zx.repository.OrderRepository;
import me.zhengjie.modules.zx.service.ZxService;
import me.zhengjie.modules.zx.service.dto.CustomerDto;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZxServiceImpl implements ZxService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<CustomerDto> getCustomerList() {
        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Customer> customers = customerRepository
                .findByOrgIdAndDate(principal.getUsername(),
                        new Timestamp(DateUtils.addDays(new Date(), -30).getTime()));
        if (CollectionUtils.isEmpty(customers)) {
            return null;
        }
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            for (Order order : customer.getOrderList()) {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setCustomerName(customer.getCustomerName());
                customerDto.setCustomerPhone(customer.getCustomerPhone());
                customerDto.setIdNumber(customer.getIdNumber());
                customerDto.setCardNo(customer.getCardNo());
                customerDto.setReportId(order.getReportId());
                customerDto.setCreateTime(order.getCreateTime());
                customerDto.setLateModify(order.getLateModify());
                customerDtos.add(customerDto);
            }
        }
        return customerDtos;
    }

    @Override
    public  Map<String, Object> getChartData() {
//        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date end = new Date();
        Date start = DateUtils.addDays(end, -30);
//        List<Order> orders = orderRepository
//                .findByOrgIdAndLateModify(principal.getUsername(),
//                        new Timestamp(start.getTime()));
                List<Order> orders = orderRepository
                .findByOrgIdAndLateModify("xinyongxiongmao",
                        new Timestamp(start.getTime()));
        List<String> dayList = orders.stream().sorted((x, y) -> x.getLateModify().before(y.getLateModify()) ? 1 : -1)
                .map(r -> DateFormatUtils.format(r.getLateModify(), "yyyy-MM-dd")).collect(Collectors.toList());
        List<Map<String, Object>> monthLine = getDaysBetween(start, end);
        List<Map<String, Object>> monthPayLine = getDaysBetween(start, end);
        Map<String, Long> month = dayList.stream().collect(Collectors.groupingBy(r -> r, Collectors.counting()));
        List<String> payList = orders.stream().filter(r -> r.isPay())
                .sorted((x, y) -> x.getLateModify().before(y.getLateModify()) ? 1 : -1)
                .map(r -> DateFormatUtils.format(r.getLateModify(), "yyyy-MM-dd")).collect(Collectors.toList());
        Map<String, Long> monthPay = payList.stream().collect(Collectors.groupingBy(r -> r, Collectors.counting()));
        for (int i = 0; i < monthLine.size(); i++) {
            Map<String, Object> map = monthLine.get(i);
            String day = map.get("day").toString();
            long count = month.get(day) == null ? 0 : month.get(day);
            map.put("count",count);
            long monthPayCount = monthPay.get(day) == null ? 0 : monthPay.get(day);
            Map<String, Object> map1 = monthPayLine.get(i);
            map1.put("count",monthPayCount);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("monthLine", monthLine);
        result.put("monthPayLine", monthPayLine);
        return result;
    }

    private static List<Map<String, Object>> getDaysBetween(Date start, Date end) {
        List<Map<String, Object>> dateStrList = new ArrayList<>();
        while (!start.after(end)) {
            Map<String, Object> day = new HashMap<>();
            String startDay = DateFormatUtils.format(start, "yyyy-MM-dd");
            day.put("day", startDay);
            day.put("count", 0);
            dateStrList.add(day);
            start = DateUtils.addDays(start, 1);

        }
        return dateStrList;
    }


    @Override
    public List<CustomerDto> getCustomers() {
        return null;
    }

    @Override
    public Map<String, Object> getOrderCount() {
        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderRepository
                .findByOrgIdAndLateModify(principal.getUsername(),
                        new Timestamp(DateUtils.addDays(new Date(), -30).getTime()));
        int dayCount = 0;
        int dayPay = 0;
        int monthCount = 0;
        int monthPayCount = 0;
        String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        for (Order order : orders) {
            monthCount++;
            String date = DateFormatUtils.format(order.getLateModify(), "yyyy-MM-dd");
            if (order.isPay()) {
                monthPayCount++;
            }
            if (today.equals(date) && order.isPay()) {
                dayPay++;
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("dayCount", dayCount);
        result.put("dayPay", dayPay);
        result.put("monthCount", monthCount);
        result.put("monthPayCount", monthPayCount);
        return result;
    }
}
