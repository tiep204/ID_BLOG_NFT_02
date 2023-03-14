package ra.payload.response;

import lombok.Data;
import ra.model.entity.Product;
import java.time.LocalDate;

@Data
public class ExhibitionResponse {
    private int exhibitionID;
    private String exhibitionTitle;
    private String exhibitionDescription;
    private LocalDate exhibitionCreatedDate;
    private LocalDate exhibitionExpiredDate;
    private boolean exhibitionStatus;
}
