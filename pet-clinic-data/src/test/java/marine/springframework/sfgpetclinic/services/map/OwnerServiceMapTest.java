package marine.springframework.sfgpetclinic.services.map;

import marine.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {
    final Long ownerID= 1L;
    final String lastName= "Tri";
    OwnerServiceMap ownerServiceMap;
    @BeforeEach
    void setUp() {
        ownerServiceMap= new OwnerServiceMap(new PetTypeServiceMap(),new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerID).lastName(lastName).build());
    }

    @Test
    void findById() throws Exception {
        Owner owner = ownerServiceMap.findById(ownerID);
        assertEquals(ownerID,owner.getId());
    }

    @Test
    void saveExistingID() throws Exception {
        Long id= 2L;
        Owner owner2 = Owner.builder().id(2L).build();
        Owner saveOwner = ownerServiceMap.save(owner2);
        assertEquals(id, saveOwner.getId());
    }

    @Test
    void saveNoId() throws Exception {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findAll() throws Exception {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1,owners.size());
    }

    @Test
    void deleteById() throws Exception {
        ownerServiceMap.deleteById(ownerID);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() throws Exception {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerID));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() throws Exception {
        Owner Tri = ownerServiceMap.findByLastName(lastName);
        assertNotNull(Tri);
        assertEquals(1,Tri.getId());
    }
}