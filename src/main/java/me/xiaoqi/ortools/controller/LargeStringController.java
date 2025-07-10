package me.xiaoqi.ortools.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class LargeStringController {

    @PostMapping("/process-strings")
    public ResponseEntity<Void> handleStrings(@RequestParam("data") String data) {
        try (final ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());) {
            processInputStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/process-large-data")
    public ResponseEntity<Void> handleLargeData(InputStream inputStream) throws IOException {
        processInputStream(inputStream);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/upload")
    public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        processInputStream(file.getInputStream());
        return ResponseEntity.ok().build();
    }

    private @Autowired ObjectMapper objectMapper;

    private void processInputStream(InputStream inputStream) throws IOException {
        try (Reader br = new BufferedReader(new InputStreamReader(inputStream))) {
            JsonFactory jsonFactory = objectMapper.getFactory();
            JsonParser jsonParser = jsonFactory.createParser(br);

//            long c = br.lines().peek(line ->{
//                System.out.println(line);
//            }).count();
//
//            System.out.println("Total lines: " + c);

            char[] buffer = new char[1024]; // 缓冲区大小
            int charsRead;

            while ((charsRead = br.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, charsRead));
            }

        }
    }

}
