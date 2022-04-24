package danta.domain.delivery;

import javax.persistence.Embeddable;

@Embeddable
public enum DeliveryStatus {
    READY_STATUS, SHIPPING_STATUS, COMPLETE_STATUS
}
