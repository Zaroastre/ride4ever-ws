/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.club;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("memberRepository")
interface MemberRepository extends CrudRepository<Member, Integer> {

}
