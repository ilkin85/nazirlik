package com.mia.xrs.controller;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.PackageDto;
import com.mia.xrs.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class PackageController {
    private final PackageService packageService;

//    @GetMapping("/packages")
//    public ResponseEntity<Page<LetterDto>> findAll(@RequestParam(name = "pageSize", required = false) Integer pageSize,
//                                                   @RequestParam(name = "pageNumber", required = true) Integer pageNumber,
//                                                   @RequestParam(name = "sortBy", required = false) String[] sortBy){
//        return ResponseEntity.ok(packageService.(pageSize, pageNumber, sortBy));
//    }

    @PostMapping("/packages")
    public ResponseEntity<PackageDto> save(@RequestBody PackageDto packageDto){
        return ResponseEntity.ok(packageService.save(packageDto));
    }

    @PutMapping("/packages/{id}")
    public ResponseEntity<PackageDto> update(@PathVariable Integer id,
                                            @RequestBody PackageDto packageDto){
        return ResponseEntity.ok(packageService.update(id,packageDto));
    }

}
