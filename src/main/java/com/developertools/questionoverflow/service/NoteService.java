package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.NoteDto;
import com.developertools.questionoverflow.dto.converter.NoteDtoConverter;
import com.developertools.questionoverflow.dto.request.CreateNoteRequest;
import com.developertools.questionoverflow.model.Link;
import com.developertools.questionoverflow.model.Note;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteDtoConverter noteDtoConverter;
    private final UserService userService;


    public NoteService(NoteRepository noteRepository, NoteDtoConverter noteDtoConverter,
                       UserService userService) {
        this.noteRepository = noteRepository;
        this.noteDtoConverter = noteDtoConverter;
        this.userService = userService;
    }

    public NoteDto save(CreateNoteRequest request) {
        User user = userService.getByMail(request.getUserMail());
        List<Link> linkList = new ArrayList<>();

        for (String link : request.getUrlToImages()) {
            linkList.add(new Link(link));
        }

        Note saved = new Note(
                request.getTitle(),
                request.getDescription(),
                linkList,
                user
        );

        return noteDtoConverter.convertNoteToNoteDto(noteRepository.save(saved));
    }

    public void delete(String publicId) {
        noteRepository.deleteById(getNoteByPublicId(publicId).getId());
    }

    public NoteDto getByPublicId(String publicId) {
        return noteDtoConverter.convertNoteToNoteDto(getNoteByPublicId(publicId));
    }

    public List<NoteDto> getByUserMail(String mail) {
        User user = userService.getByMail(mail);

        return noteDtoConverter.convertNoteListToNoteDtoList(noteRepository.findNoteByUser(user));
    }

    private Note getNoteByPublicId(String publicId) {
        return noteRepository.findNoteByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException(""));
    }
}
