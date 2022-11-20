package com.developertools.questionoverflow.controller;

import com.developertools.questionoverflow.dto.NoteDto;
import com.developertools.questionoverflow.dto.request.CreateNoteRequest;
import com.developertools.questionoverflow.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteDto> save(@RequestBody @Valid CreateNoteRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noteService.save(request));
    }

    @GetMapping("/get-by-user-mail/{mail}")
    public ResponseEntity<List<NoteDto>> getAllByUserMail(@PathVariable(value = "mail") String mail) {
        return ResponseEntity
                .ok(noteService.getByUserMail(mail));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String publicId) {
        noteService.delete(publicId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<NoteDto> getByPublicId(@PathVariable(value = "publicId") String publicId) {
        return ResponseEntity.ok(noteService.getByPublicId(publicId));
    }
}
