/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.location;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {
    String getClientIp(HttpServletRequest request);
}
