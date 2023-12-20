package com.mia.xrs.controller;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.RejectDto;
import com.mia.xrs.service.RejectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RejectController {

    private final RejectService rejectService;

    @GetMapping("/rejects")
    public ResponseEntity<Page<RejectDto>> findAll(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
                                                   @RequestParam(name = "sortBy", required = false) String[] sortBy){
        return ResponseEntity.ok(rejectService.findAllPage(pageSize, pageNumber, sortBy));
    }

    @GetMapping("/reject/{id}")
    public ResponseEntity<RejectDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(rejectService.findById(id));
    }





    @PostMapping("/rejects")
    public ResponseEntity<RejectDto> save(@RequestBody RejectDto rejectDto){
        return ResponseEntity.ok(rejectService.save(rejectDto));
    }

    @PutMapping("/rejects/{id}")
    public ResponseEntity<RejectDto> update(@PathVariable Integer id,
                                            @RequestBody RejectDto rejectDto){
        return ResponseEntity.ok(rejectService.update(id,rejectDto));
    }

    @DeleteMapping("/rejects/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        rejectService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
