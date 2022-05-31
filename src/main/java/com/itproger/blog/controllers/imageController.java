package com.itproger.blog.controllers;


import com.itproger.blog.exceptions.NotFoundException;
import com.itproger.blog.generator.imageGenerator;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("images")
public class imageController {
    private String pathToResultFolder = "C:/Users/ASUS/Desktop/springboot/ft2/src/main/resources/results"; //Введите путь до папки result

    private int counter = new File(pathToResultFolder).listFiles().length;

    private List<Map<String, String>> images = new ArrayList<Map<String, String>>() {{

        for(int i = counter-1; i>-1; i--){
            String StringI = Integer.toString(i);
            add(new HashMap<String, String>() {{ put("id", StringI); put("url", "/images/showme/" + StringI);}});
        }
        //add(new HashMap<String, String>() {{ put("id", "1"); put("url", "https://i.pinimg.com/600x315/04/fc/74/04fc74e8816759bb4bb8e9a61bd67981.jpg");}});
        //add(new HashMap<String, String>() {{ put("id", "2"); put("url", "/images/showme");}});
        //add(new HashMap<String, String>() {{ put("id", "3"); put("url", "https://i.pinimg.com/600x315/04/fc/74/04fc74e8816759bb4bb8e9a61bd67981.jpg");}});

    }};
    @GetMapping()
    public List<Map<String, String>> list() {
        return images;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getImage(id);
    }

    @GetMapping(value = "/showme/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> image(@PathVariable String id) throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                pathToResultFolder + "/"+ id + ".png"
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }

    private Map<String, String> getImage(String id) {
        return images.stream()
                .filter(image -> image.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> image) throws IOException {
        image.put("id", String.valueOf(counter));
        image.put("url", "/images/showme/"+ String.valueOf(counter++));

        /////////////////////////
        counter = imageGenerator.Generate();
        /////////////////////////

        Collections.reverse(images);
        images.add(image);
        Collections.reverse(images);

        return image;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> image) {
        Map<String, String> imageFromDb = getImage(id);

        imageFromDb.putAll(image);
        imageFromDb.put("id", id);

        return imageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> image = getImage(id);

        images.remove(image);
    }


}
