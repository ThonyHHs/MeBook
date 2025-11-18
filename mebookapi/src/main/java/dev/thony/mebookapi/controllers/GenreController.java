package dev.thony.mebookapi.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thony.mebookapi.models.GenreModel;
import dev.thony.mebookapi.models.DTOs.GenreDTO;
import dev.thony.mebookapi.models.DTOs.GenreMapper;
import dev.thony.mebookapi.services.GenreService;

@RestController
@RequestMapping("/v1/genres")
public class GenreController {
    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDTO> getAll() {
        return genreService.getAll().stream().map(GenreMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{genreId}")
    public GenreDTO getById(@PathVariable UUID genreId) {
        return GenreMapper.toDto(genreService.getById(genreId));
    }

    @PostMapping()
    public GenreDTO save(@RequestBody GenreModel genre) {
        return GenreMapper.toDto(genreService.save(genre));
    }

    @PutMapping("/{genreId}")
    public GenreDTO update(@PathVariable UUID genreId, @RequestBody GenreModel updatedGenre) {
        return GenreMapper.toDto(genreService.update(genreId, updatedGenre));
    }

    @DeleteMapping("/{genreId}")
    public void delete(@PathVariable UUID genreId) {
        genreService.delete(genreId);
    }
}
