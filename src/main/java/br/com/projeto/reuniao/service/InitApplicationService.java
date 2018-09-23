package br.com.projeto.reuniao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.projeto.reuniao.domain.entity.Notes;

/**
 * 
 * @author Pablo
 *
 */
@Service
public class InitApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitApplicationService.class);

    @Autowired
    NotesService notesService;

    /**
     * Initialize the test data
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeTestData() {
//        LOGGER.info("Initialize test data");
//
//        notesService.saveNotes(new Notes("Test 1", "Content 1"));
//        notesService.saveNotes(new Notes("Test 2", "Content 2"));
//        notesService.saveNotes(new Notes("Test 3", "Content 3"));
//        notesService.saveNotes(new Notes("Test 4", "Content 4"));
//        notesService.saveNotes(new Notes("Test 5", "Content 5"));
//        notesService.saveNotes(new Notes("Test 6", "Content 6"));
//        notesService.saveNotes(new Notes("Test 7", "Content 7"));
//        notesService.saveNotes(new Notes("Test 8", "Content 8"));
//        notesService.saveNotes(new Notes("Test 9", "Content 9"));
//        notesService.saveNotes(new Notes("Test 10", "Content 10"));
//
//        LOGGER.info("Initialization completed");
    }

}
