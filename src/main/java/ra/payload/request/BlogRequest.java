package ra.payload.request;

import lombok.Data;
import ra.model.entity.Comment;
import ra.model.entity.Tag;
import ra.model.entity.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class BlogRequest {
    private String blogTitle;
    private String blogContent;
    private String blogImage;
    private LocalDate blogCreateDate;
    private boolean blogStatus;
    private List<Tag> listTag ;

}

