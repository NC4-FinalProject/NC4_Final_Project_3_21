package com.bit.nc4_final_project.entity.board;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class BoardLikeId implements Serializable {
    private Integer board;
    private Integer user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardLikeId that = (BoardLikeId) o;
        return Objects.equals(board, that.board) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, user);
    }
}