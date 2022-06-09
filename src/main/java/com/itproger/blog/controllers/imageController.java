package com.itproger.blog.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.itproger.blog.domain.Image;
import com.itproger.blog.FileChecker;

import com.itproger.blog.domain.Views;
import com.itproger.blog.generator.imageGenerator;
import com.itproger.blog.repo.ImageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("images")
public class imageController {
    private String pathToResultFolder = "src/main/resources/results";
    File resultsDir = FileChecker.DirCreator(pathToResultFolder);

    private final ImageRepo imageRepo;

    @Autowired
    public imageController(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public Long getImageCount() {
        return imageRepo.count();
    }



    //add(new HashMap<String, String>() {{ put("id", StringI); put("url", "/images/showme/" + StringI);}});
    @GetMapping()
    @JsonView(Views.FullImage.class)
    public List<Image> list() {
        return imageRepo.findAll(Sort.by(Sort.Order.desc("id")));
    }

    @GetMapping("{id}")
    public Image getOne(@PathVariable("id") Image image) {
        return image;
    }

    @GetMapping(value = "/showme/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> image(@PathVariable("id") String id) throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                pathToResultFolder + "/"+ id + ".png"
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }


    @PostMapping
    public Image create(@RequestBody Image image) throws IOException {
        /////////////////////////
        imageGenerator.Generate(getImageCount());
        /////////////////////////

        image.setUrl("url", "/images/showme/" + String.valueOf(getImageCount()+1));
        image.setCreationDate(LocalDateTime.now());

        return imageRepo.save(image);

    }

    @PutMapping("{id}")
    public Image update(
            @PathVariable("id") Image imageFromDb,
            @RequestBody Image image) {
        BeanUtils.copyProperties(image, imageFromDb, "id");

        return imageRepo.save(imageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Image image) {
        imageRepo.delete(image);
    }


}
