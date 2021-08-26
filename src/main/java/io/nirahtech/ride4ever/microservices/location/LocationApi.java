package io.nirahtech.ride4ever.microservices.location;

import com.ip2location.IPResult;

public interface LocationApi {
    IPResult resolve(final String ipAddress);
    
}
