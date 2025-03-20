package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*") // ✅ Allows frontend access
public class FileController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    // ✅ Upload File API
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ File is empty!");
        }

        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Failed to create upload directory!");
            }

            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            file.transferTo(filePath.toFile());

            return ResponseEntity.ok("✅ File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Failed to upload file!");
        }
    }

    // ✅ List Uploaded Files
    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists() || uploadDir.list() == null) {
            return ResponseEntity.ok(List.of("⚠ No files uploaded yet."));
        }

        List<String> fileList = Arrays.asList(uploadDir.list());
        return ResponseEntity.ok(fileList);
    }

    // ✅ View File in Browser (PDFs will now open properly)
    @GetMapping("/view/{filename}")
    public ResponseEntity<Resource> viewFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // 🔹 Detect the correct MIME type
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream"; // Default type
            }

            // ✅ PDFs will now open properly
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"") // Opens in browser
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ✅ Download File API
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ✅ Delete File API
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        File file = new File(UPLOAD_DIR + filename);

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ File not found!");
        }

        if (file.delete()) {
            return ResponseEntity.ok("✅ File deleted successfully: " + filename);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Failed to delete file!");
        }
    }

    // ✅ Root Endpoint: Welcome Message
    @GetMapping("/")
    public String home() {
        return """
                <h2>Welcome to the File Server!</h2>
                <p>Use the following endpoints:</p>
                <ul>
                    <li><strong>Upload:</strong> POST <code>/api/files/upload</code></li>
                    <li><strong>List Files:</strong> GET <code>/api/files/list</code></li>
                    <li><strong>View File:</strong> GET <code>/api/files/view/{filename}</code></li>
                    <li><strong>Download File:</strong> GET <code>/api/files/download/{filename}</code></li>
                    <li><strong>Delete File:</strong> DELETE <code>/api/files/delete/{filename}</code></li>
                </ul>
                """;
    }
}
