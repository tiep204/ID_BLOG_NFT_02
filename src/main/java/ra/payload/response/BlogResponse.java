package ra.payload.response;

import lombok.Data;
import ra.model.entity.Comment;
import ra.model.entity.Tag;
import ra.model.entity.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
public class BlogResponse {
    private int blogID;
    private String blogTitle;
    private String blogContent;
    private String blogImage;
    private LocalDate blogCreateDate;
    private boolean blogStatus;
    private String userName;
    private List<Tag> listTag;
    private List<Comment> listComment;
}
