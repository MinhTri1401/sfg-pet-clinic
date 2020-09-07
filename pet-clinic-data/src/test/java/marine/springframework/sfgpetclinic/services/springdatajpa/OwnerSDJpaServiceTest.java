package marine.springframework.sfgpetclinic.services.springdatajpa;

import marine.springframework.sfgpetclinic.model.Owner;
import marine.springframework.sfgpetclinic.repositories.OwnerRepository;
import marine.springframework.sfgpetclinic.repositories.PetRepository;
import marine.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    public static final String lastName = "Smith";
    public Long ownerId= 1L;
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;
    @BeforeEach
    void setUp() {
        returnOwner=Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findByLastName() throws Exception {
        Owner owner = Owner.builder().lastName(lastName).id(1L).build();

        when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner smith = service.findByLastName(lastName);

        assertEquals(lastName,smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findById() throws Exception {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void save() throws Exception {
        Owner ownerToSave = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(ownerToSave);
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void findAll() throws Exception {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> owners1= service.findAll();

        assertEquals(2, owners1.size());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void delete() throws Exception {
        service.delete(returnOwner);
        verify(ownerRepository,times(1)).delete(any());
    }
}