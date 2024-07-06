package inverte.ya.api.controller;

import inverte.ya.api.dto.CommentaryDTO;
import inverte.ya.api.model.Commentary;
import inverte.ya.api.service.ICommentaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaries")
@RequiredArgsConstructor
public class CommentaryController
{
    private final ICommentaryService service;
    private final ModelMapper mapper;

    @PostMapping()
    public ResponseEntity<CommentaryDTO> save(@RequestBody @Valid CommentaryDTO commentary)
    {
        CommentaryDTO commentaryDTO = this.convertToDTO(service.save(this.convertToEntity(commentary)));
        return new ResponseEntity<>(commentaryDTO, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<CommentaryDTO> update(@RequestBody @Valid CommentaryDTO commentary)
    {
        CommentaryDTO commentaryDTO = this.convertToDTO(service.save(this.convertToEntity(commentary)));
        return new ResponseEntity<>(commentaryDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentaryDTO> findById(@PathVariable("id") Integer id)
    {
        CommentaryDTO commentaryDTO = this.convertToDTO(service.readById(id));
        return new ResponseEntity<>(commentaryDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CommentaryDTO>> findAll()
    {
        List<CommentaryDTO> commentaries = service.readAll().stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(commentaries, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CommentaryDTO convertToDTO(Commentary commentary)
    {
        return mapper.map(commentary, CommentaryDTO.class);
    }

    private Commentary convertToEntity(CommentaryDTO dto)
    {
        return mapper.map(dto, Commentary.class);
    }

}

