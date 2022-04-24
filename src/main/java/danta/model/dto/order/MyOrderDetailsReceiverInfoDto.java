package danta.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.jni.Address;

@AllArgsConstructor
@Getter
public class MyOrderDetailsReceiverInfoDto {
    private String receiverName;
    private String contact;
    private Address address;
}
