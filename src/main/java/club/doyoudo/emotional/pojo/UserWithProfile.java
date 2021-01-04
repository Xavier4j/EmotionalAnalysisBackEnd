package club.doyoudo.emotional.pojo;

import club.doyoudo.emotional.model.Profile;
import club.doyoudo.emotional.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWithProfile extends User {
    private Profile userProfile;
    private String plateIds;
}
