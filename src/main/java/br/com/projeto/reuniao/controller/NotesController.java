package br.com.projeto.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.reuniao.domain.entity.Notes;
import br.com.projeto.reuniao.service.NotesService;

@Controller
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    NotesService notesService;
    
    @GetMapping("")    
    public String notesList(Model model) {
        model.addAttribute("notesList", notesService.findAll());
        return "notes/notesList";
    }
    
    @GetMapping(value={"/notesEdit","/notesEdit/{id}"})
    public String notesEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("notes", notesService.findById(id));
        } else {
            model.addAttribute("notes", new Notes());
        }
        return "notes/notesEdit";
    }

    @PostMapping("/notesEdit")
    public String notesEdit(Model model, Notes notes) {
        notesService.saveNotes(notes);
        model.addAttribute("notesList", notesService.findAll());
        return "notes/notesList";
    }

    @GetMapping("/notesDelete/{id}")
    public String notesDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        notesService.deleteNotes(id);
        model.addAttribute("notesList", notesService.findAll());
        return "notes/notesList";
    }

}
