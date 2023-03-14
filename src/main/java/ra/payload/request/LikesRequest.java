package ra.payload.request;

import lombok.Data;

@Data
public class LikesRequest {
    private boolean likeStatus;
    private int blogID;
}
