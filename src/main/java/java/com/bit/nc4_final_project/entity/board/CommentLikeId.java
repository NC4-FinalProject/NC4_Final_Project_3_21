package java.com.bit.nc4_final_project.entity.board;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class CommentLikeId implements Serializable {
    private Integer user;
    private Integer boardComment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentLikeId that = (CommentLikeId) o;
        return user.equals(that.user) && boardComment.equals(that.boardComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, boardComment);
    }
}
