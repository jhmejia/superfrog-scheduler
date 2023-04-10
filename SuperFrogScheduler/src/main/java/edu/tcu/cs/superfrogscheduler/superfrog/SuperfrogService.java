package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperfrogService {

    private final SuperfrogRepository superfrogRepository;


    public SuperfrogService(SuperfrogRepository superfrogRepository) {
        this.superfrogRepository = superfrogRepository;
    }

    public Superfrog findById(int superfrogId) {
        return this.superfrogRepository.findById(superfrogId)
                .orElseThrow(()-> new ObjectNotFoundException("superfrog", superfrogId));
    }

    public List<Superfrog> findAll() {
        return this.superfrogRepository.findAll();
    }
}
