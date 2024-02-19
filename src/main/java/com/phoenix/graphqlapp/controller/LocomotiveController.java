package com.phoenix.graphqlapp.controller;


import com.phoenix.graphqlapp.model.Loco;
import com.phoenix.graphqlapp.model.Locomotive;
import com.phoenix.graphqlapp.service.LocomotiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LocomotiveController {

    private final LocomotiveService locomotiveService;

    @QueryMapping
    public List<Loco> findAll(){
        return locomotiveService.findAll();
    }

    @QueryMapping
    public Optional<Loco> findOne(@Argument Integer id){
        return locomotiveService.findOne(id);
    }

    @MutationMapping
    public Loco create(@Argument String name, @Argument String roadNumber, @Argument Locomotive locoType, @Argument String locoSeries){
        return locomotiveService.createLoco(name, roadNumber, locoType, locoSeries);
    }

    @MutationMapping
    public Loco update(@Argument Integer id, @Argument String name, @Argument String roadNumber, @Argument Locomotive locoType, @Argument String locoSeries){
        return locomotiveService.update(id, name, roadNumber, locoType, locoSeries);
    }

    @MutationMapping
    public Loco delete(@Argument Integer id){
        return locomotiveService.deleteLoco(id);
    }
}
