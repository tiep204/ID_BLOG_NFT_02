package ra.payload.response;

import lombok.Data;
import ra.model.entity.Product;
import ra.model.entity.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class HistoryResponse {
    private int historyID;
    private LocalDate historyDataTime;
    private String productName;
    private String userName;
}
