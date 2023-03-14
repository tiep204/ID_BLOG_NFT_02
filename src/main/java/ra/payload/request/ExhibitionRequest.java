package ra.payload.request;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ExhibitionRequest {
    private String exhibitionTitle;
    private String exhibitionDescription;
    private LocalDate exhibitionCreatedDate;
    private LocalDate exhibitionExpiredDate;
    private boolean exhibitionStatus;
    private int productID;
}
