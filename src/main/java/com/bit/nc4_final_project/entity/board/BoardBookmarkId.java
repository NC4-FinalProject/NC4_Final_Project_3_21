package com.bit.nc4_final_project.entity.board;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class BoardBookmarkId implements Serializable {
    private Integer user;
    private Integer board;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardBookmarkId that = (BoardBookmarkId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, board);
    }
}