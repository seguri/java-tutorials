package name.seguri.java.tutorials.springwebfileupload;

import com.jakewharton.byteunits.BinaryByteUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UploadController {
  private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

  @PostMapping("/upload")
  void receiveFile(@RequestParam MultipartFile file) {
    logger.info("Received: {}", toString(file));
    validate(file);
    forEachLine(file, (lineNumber, line) -> logger.info("line {}: {}", lineNumber, line));
  }

  void forEachLine(MultipartFile file, BiConsumer<Integer, String> lineConsumer) {
    int lineNumber = 0;
    String line;
    try (var br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      while ((line = br.readLine()) != null) {
        lineNumber++;
        lineConsumer.accept(lineNumber, line);
      }
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading file");
    }
  }

  void validate(MultipartFile file) {
    if (file.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
    }
    if (!file.getContentType().startsWith("text/")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad content type");
    }
  }

  String toString(MultipartFile file) {
    return new StringJoiner(", ", MultipartFile.class.getSimpleName() + "[", "]")
        .add("size=" + file.getSize())
        .add("humanReadableSize='" + BinaryByteUnit.format(file.getSize()) + "'")
        .add("contentType='" + file.getContentType() + "'")
        .add("originalFilename='" + file.getOriginalFilename() + "'")
        .toString();
  }
}
