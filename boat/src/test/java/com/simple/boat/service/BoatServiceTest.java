package com.simple.boat.service;

import com.simple.boat.model.Boat;
import com.simple.boat.repository.BoatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoatServiceTest {

    private static final String TITANIC_BOAT_NAME = "Titanic";
    private static final String TITANIC_DESCRIPTION = "It won't make it till the end";

    @Mock
    private BoatRepository repository;

    @InjectMocks
    private BoatService service;

    @Test
    void shouldCreateBoat() {
        //given
        Boat titanic = new Boat(TITANIC_BOAT_NAME, TITANIC_DESCRIPTION);
        when(repository.save(any(Boat.class))).thenReturn(titanic);

        //when
        Boat saved = service.save(titanic);

        //then
        assertEquals(TITANIC_BOAT_NAME, saved.getName());
        verify(repository, times(1)).save(titanic);
    }

    @Test
    void shouldReturnListOfBoatsWhenFindAllIsCalled() {
        //given
        List<Boat> boats = Arrays.asList(
                new Boat(TITANIC_BOAT_NAME, TITANIC_DESCRIPTION),
                new Boat("Queen Mary", "Ocean liner")
        );
        when(repository.findAll()).thenReturn(boats);

        //when
        List<Boat> result = service.findAll();

        //then
        assertEquals(2, result.size());
        assertEquals(TITANIC_BOAT_NAME, result.getFirst().getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldUpdateExistingBoat() {
        //given
        Boat titanic = new Boat(TITANIC_BOAT_NAME, TITANIC_DESCRIPTION);
        Boat updated = new Boat("Titanic II", "Rebuilt ship");
        updated.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(titanic));
        when(repository.save(any(Boat.class))).thenReturn(updated);

        //when
        Boat result = service.update(1L, updated);

        //then
        assertEquals("Titanic II", result.getName());
        assertEquals("Rebuilt ship", result.getDescription());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Boat.class));
    }

    @Test
    void shouldDeleteExistingBoat() {
        //given
        Boat titanic = new Boat(TITANIC_BOAT_NAME, TITANIC_DESCRIPTION);
        service.save(titanic);

        //when
        service.delete(1L);

        //then
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void shouldReturnErrorWhenBoatNotFoundById() {
        //given
        when(repository.findById(99L)).thenReturn(Optional.empty());

        //when
        Exception ex = assertThrows(RuntimeException.class, () -> service.findById(99L));

        //then
        assertTrue(ex.getMessage().contains("not found"));
    }
}