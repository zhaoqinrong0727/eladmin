package me.zhengjie.modules.zx.rest;

import me.zhengjie.modules.zx.service.ZxService;
import me.zhengjie.modules.zx.service.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ZxController {
    @Autowired
    private ZxService zxServicce;

    //    @GetMapping(value = "/visits")
//    public ResponseEntity get(){
//        return new ResponseEntity(visitsService.get(), HttpStatus.OK);
//    }
//
    @GetMapping(value = "/chartData")
    public ResponseEntity getChartData() {

        return new ResponseEntity(zxServicce.getChartData(), HttpStatus.OK);
    }

    @GetMapping(value = "/customerList")
    public ResponseEntity getCustomerList() {
        List<CustomerDto> customerList = zxServicce.getCustomerList();
        return new ResponseEntity(customerList, HttpStatus.OK);
    }

    @GetMapping(value = "/counts")
    public ResponseEntity getCustomerCount() {
        Map<String, Object> customerList = zxServicce.getOrderCount();
        return new ResponseEntity(customerList, HttpStatus.OK);
    }
}
