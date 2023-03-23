package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.History;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.service.HistoryService;
import ra.model.service.ProductService;
import ra.model.service.UserService;
import ra.payload.response.HistoryResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    ///start creteHistory///
    @PostMapping("/createHistory")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createHistory(@RequestBody History history) {
        try {
            Product product = productService.findById(history.getProduct().getProductID());
            User user = userService.findByUserId(history.getUser().getUserID());
            History historyNew = new History();
            historyNew.setHistoryDataTime(java.time.LocalDate.now());
            historyNew.setProduct(product);
            historyNew.setUser(user);
            historyService.save(historyNew);
            return ResponseEntity.ok("ban da mua thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("ban da mua thất bại");
        }
    }
    ///end createHistory///

        ///start ehibitionsList///
    @GetMapping("/ehibitionsList")
    public List<HistoryResponse> exhibitionsList() {
        List<HistoryResponse> historyResponses = new ArrayList<>();
        List<History> histories = historyService.findAll();
        for (History his: histories) {
            HistoryResponse histo = new HistoryResponse();
            histo.setHistoryID(his.getHistoryID());
            histo.setHistoryDataTime(his.getHistoryDataTime());
            histo.setProductName(his.getProduct().getProductName());
            histo.setUserName(his.getUser().getUserName());
            historyResponses.add(histo);
        }
        return historyResponses;
    }

    @GetMapping("/ehibitionsLists")
    public List<History> exhibitionsListt() {
        List<History> histories = historyService.findAll();
        return histories;
    }


    ///end ehibitionsList///

    ///start deleteHistory///
    @DeleteMapping("/deleteHistory/{historyID}")
    public ResponseEntity<String> delete(@PathVariable("historyID") int historyID) {
        historyService.delete(historyID);
        return  ResponseEntity.ok("đã xóa thành công");
    }
    ///end deleteHistory////
}
