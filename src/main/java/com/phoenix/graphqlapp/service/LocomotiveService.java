package com.phoenix.graphqlapp.service;

import com.phoenix.graphqlapp.exception.LocoException;
import com.phoenix.graphqlapp.model.Loco;
import com.phoenix.graphqlapp.model.Locomotive;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LocomotiveService {

    private List<Loco> locos = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Loco> findAll(){
        return locos;
    }

    public Optional<Loco> findOne(Integer id){
        return locos.stream().filter(l -> l.Id() == id).findFirst();
    }

    public Loco createLoco(String name, String roadNumber, Locomotive locoType, String locoSeries ){
        Loco loco = new Loco(id.incrementAndGet(), name, roadNumber, locoType, locoSeries);
        locos.add(loco);
        return loco;
    }

    public Loco deleteLoco(Integer id){
        Loco loco = locos.stream().filter(l -> l.Id() == id)
                .findFirst().orElseThrow(() -> new LocoException("Error deleting locomotive"));
        locos.remove(loco);
        return loco;
    }

    public Loco update (Integer id, String name, String roadNumber, Locomotive locoType, String locoSeries ){
        Loco updatedLoco = new Loco(id, name, roadNumber, locoType, locoSeries);
        Optional<Loco> locoOptional = locos.stream()
                .filter(l -> l.Id() == id).findFirst();
        if(locoOptional.isPresent()){
            Loco orgLoco = locoOptional.get();
            int index = locos.indexOf(orgLoco);
            locos.set(index, updatedLoco);
        }else{
            throw new LocoException("Error updating locomotive details");
        }
        return updatedLoco;
    }

    @PostConstruct
    private void init(){
        locos.add(new Loco(id.incrementAndGet(), "CLW", "32144", Locomotive.WAP, "4"));
        locos.add(new Loco(id.incrementAndGet(), "DLW", "12194", Locomotive.WAP, "7"));
        locos.add(new Loco(id.incrementAndGet(), "LGD", "20147", Locomotive.WAG, "9"));
        locos.add(new Loco(id.incrementAndGet(), "GE", "47859", Locomotive.WAM, "2"));
        locos.add(new Loco(id.incrementAndGet(), "TKD", "43021", Locomotive.WDP, "4D"));
        locos.add(new Loco(id.incrementAndGet(), "ALCO", "12541", Locomotive.WDM, "3A"));
        locos.add(new Loco(id.incrementAndGet(), "ALCO", "26314", Locomotive.WDG, "2B"));
        locos.add(new Loco(id.incrementAndGet(), "EMU", "00214", Locomotive.EMU, "6"));
        locos.add(new Loco(id.incrementAndGet(), "DEMU", "36001", Locomotive.DEMU, "8"));
        locos.add(new Loco(id.incrementAndGet(), "MEMU", "49574", Locomotive.MEMU, "3"));
    }
}
