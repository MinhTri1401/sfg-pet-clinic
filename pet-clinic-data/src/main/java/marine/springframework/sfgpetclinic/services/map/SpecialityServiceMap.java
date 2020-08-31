package marine.springframework.sfgpetclinic.services.map;

import marine.springframework.sfgpetclinic.model.Speciality;
import marine.springframework.sfgpetclinic.services.SpecialtiesService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class SpecialityServiceMap extends AbstractMapService<Speciality,Long> implements SpecialtiesService {
    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }
}
