package java.com.bit.nc4_final_project.entity.community;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class CommunitySubscriberId implements Serializable {
    private Integer user;
    private Integer community;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunitySubscriberId that = (CommunitySubscriberId) o;
        return Objects.equals(user, that.user) && Objects.equals(community, that.community);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, community);
    }
}
