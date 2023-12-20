package com.mia.xrs.controller;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;

    @GetMapping("/letters")
    public ResponseEntity<Page<LetterDto>> findAll(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                   @RequestParam(name = "sortBy", required = false) String[] sortBy){
        return ResponseEntity.ok(letterService.findAllPage(pageSize, pageNumber, sortBy));
    }

    @GetMapping("/letter/{id}")
    public ResponseEntity<LetterDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(letterService.findById(id));
    }

    @GetMapping("/letter/no/{no}")
    public ResponseEntity<LetterDto> findByNo(@PathVariable Integer no){
        return ResponseEntity.ok(letterService.findByLetterNo(no));
    }

    @GetMapping("letters/date")
    public ResponseEntity<Page<LetterDto>> findByDate(@RequestParam Date date,
                                                      @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                      @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                      @RequestParam(name = "sortBy", required = false) String[] sortBy){
        return ResponseEntity.ok(letterService.findByDate(date, pageSize, pageNumber, sortBy));
    }

    @GetMapping("letters/from")
    public ResponseEntity<Page<LetterDto>> findByFromDepartment(@RequestParam String name,
                                                                @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                                @RequestParam(name = "sortBy", required = false) String[] sortBy){
        return ResponseEntity.ok(letterService.findByFromDepartment(name, pageSize, pageNumber, sortBy));
    }

    @GetMapping("letters/to")
    public ResponseEntity<Page<LetterDto>> findByToDepartment(@RequestParam String name,
                                                              @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                              @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                              @RequestParam(name = "sortBy", required = false) String[] sortBy){
        return ResponseEntity.ok(letterService.findByToDepartment(name, pageSize, pageNumber, sortBy));
    }

    @GetMapping("letters/createdBy")
    public ResponseEntity<Page<LetterDto>> findByCreatedBy(@RequestParam String createdBy,
                                                           @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                           @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                           @RequestParam(name = "sortBy", required = false) String[] sortBy){
       return ResponseEntity.ok(letterService.findByCreatedBy(createdBy, pageSize, pageNumber, sortBy));
    }

    @PostMapping("/letters")
    public ResponseEntity<LetterDto> save(@RequestBody LetterDto letterDto){
        return ResponseEntity.ok(letterService.save(letterDto));
    }

    @PutMapping("/letters/{id}")
    public ResponseEntity<LetterDto> update(@PathVariable Integer id,
                                            @RequestBody LetterDto letterDto){
        return ResponseEntity.ok(letterService.update(id,letterDto));
    }

    @DeleteMapping("/letters/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        letterService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
