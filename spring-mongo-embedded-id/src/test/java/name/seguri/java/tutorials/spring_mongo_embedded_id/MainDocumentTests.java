package name.seguri.java.tutorials.spring_mongo_embedded_id;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MainDocumentTests extends AbstractMongoTests {

  @AfterEach
  protected void afterEach() {
    super.afterEach();
  }

  @Test
  void mongoTemplateWorks() {
    var expected = givenAnExistingDocument();

    var result = whenFetchingAnExistingDocument();

    assertThat(result.getName()).isEqualTo(expected.getName());
  }

  private MainDocument givenAnExistingDocument() {
    var doc = new MainDocument();
    mongoTemplate.save(doc);
    return doc;
  }

  private MainDocument whenFetchingAnExistingDocument() {
    return mongoTemplate.findAll(MainDocument.class).get(0);
  }
}
