/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.human;

import java.util.List;

import io.nirahtech.ride4ever.motorcycleclub.core.Crud;

interface BikerApi extends Crud<Integer,  Biker> {
    List<Biker> findAll();
    Biker findByEmail(final String email);
    Biker findByPseudo(final String pseudo);
}
