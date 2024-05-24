//package org.zhuo.zexample2.filecompare;
//
//import com.aspose.words.Document;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Date;
//
///**
// * @author Xinxuan Zhuo
// * @version 2024/5/6
// * <p>
// *
// * </p>
// */
//
//@RestController
//@RequestMapping("/file-compare")
//public class FileCompareController {
//
//    @PostMapping
//    public void compare(@RequestPart("file1")MultipartFile file1, @RequestPart(name = "file2") MultipartFile file2) throws Exception {
//        // TODO
//    }
//
//    public static void main(String[] args) throws Exception {
//        Document docA = new Document("E:\\文件1.docx");
//        Document docB = new Document("E:\\文件2.docx");
//
//
//        // There should be no revisions before comparison.
//        docA.acceptAllRevisions();
//        docB.acceptAllRevisions();
//
//        docA.compare(docB, "Author Name", new Date());
//        docA.save("E:\\Output.docx");
//    }
//}
