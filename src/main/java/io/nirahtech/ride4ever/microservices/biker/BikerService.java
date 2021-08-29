package io.nirahtech.ride4ever.microservices.biker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("bikerService")
public final class BikerService implements BikerApi {

    @Autowired
    private BikerRepository repository;

    private static final BikerService SINGLETON = new BikerService();

    private BikerService() { }

    public static BikerService getInstance() {
        return SINGLETON;
    }

    @Override
    public Biker create(Biker entity) {
        return this.repository.save(entity);
    }

    @Override
    public Biker read(Integer identifier) {
        Optional<Biker> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public Biker update(Integer identifier, Biker entity) {
        return this.repository.save(entity);
    }

    @Override
    public Biker delete(Integer identifier) {
        Biker entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        return entity;
    }

    @Override
    public List<Biker> findAll() {
        List<Biker> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

    @Override
    public Biker findByEmail(String email) {
        return this.repository.findFirstByEmail(email);
    }

    @Override
    public Biker findByPseudo(String pseudo) {
        return this.repository.findFirstByPseudo(pseudo);
    }

}
