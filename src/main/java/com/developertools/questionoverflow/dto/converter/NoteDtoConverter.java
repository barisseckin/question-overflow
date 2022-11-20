package com.developertools.questionoverflow.dto.converter;

import com.developertools.questionoverflow.dto.NoteDto;
import com.developertools.questionoverflow.model.Note;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteDtoConverter {

    public NoteDto convertNoteToNoteDto(Note from) {
        return new NoteDto(
                from.getTitle(),
                from.getDescription(),
                from.getPublicId(),
                from.getUrlToImages(),
                from.getUser().getMail(),
                from.getCreateDate(),
                from.getUpdateDate()
        );
    }

    public List<NoteDto> convertNoteListToNoteDtoList(List<Note> from) {
        return from.stream().map(this::convertNoteToNoteDto).collect(Collectors.toList());
    }

}
