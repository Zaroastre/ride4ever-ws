/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.club;

import java.util.List;

import io.nirahtech.ride4ever.motorcycleclub.core.Crud;

interface MemberApi extends Crud<Integer,  Member> {
    List<Member> findAll();
}
