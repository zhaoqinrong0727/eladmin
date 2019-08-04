package me.zhengjie.modules.zx.service.dto;

import lombok.Data;
import me.zhengjie.utils.StringUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class CustomerDto {
    private String customerName;

    public String getCustomerName() {
        if (StringUtils.isNotBlank(this.customerName)) {
            StringBuilder customerName = new StringBuilder();
            customerName.append(this.customerName.charAt(0));
            for (int i = 1; i < this.customerName.length(); i++) {
                customerName.append('*');
            }
            return customerName.toString();
        }
        return customerName;
    }

    public String getIdNumber() {
        if (StringUtils.isNotBlank(this.idNumber)) {
            String head6 = this.idNumber.substring(0, 6);
            String last4 = this.idNumber.substring(this.idNumber.length() - 4);
            return head6.concat("********").concat(last4);
        }
        return idNumber;
    }

    public String getCustomerPhone() {
        if (StringUtils.isNotBlank(this.customerPhone)) {
            String head3 = this.customerPhone.substring(0, 3);
            String last4 = this.customerPhone.substring(this.customerPhone.length() - 4);
            return head3.concat("****").concat(last4);
        }
        return customerPhone;
    }

    public String getCardNo() {
        if (StringUtils.isNotBlank(this.getCardNo())) {
            String startNum = this.cardNo.substring(0, 4);
            String endNum = this.cardNo.substring(this.cardNo.length() - 4);
            return startNum + " **** **** " + endNum;
        }
        return cardNo;
    }

    public String getReportId() {
        if (StringUtils.isNotBlank(this.reportId)) {
            String startNum = this.cardNo.substring(0, 4);
            String endNum = this.cardNo.substring(this.cardNo.length() - 4);
            return startNum.concat("******").concat(endNum);
        }
        return reportId;
    }

    private String idNumber;
    private String customerPhone;
    private String cardNo;
    private String reportId;
    private Timestamp createTime;
    private Timestamp lateModify;
    private boolean pay;
}
