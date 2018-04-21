package cn.wilmar.ureport.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @NonNull String username;
    @Enumerated(EnumType.STRING) Gender gender = Gender.randomGender();
    @NonNull String email;
}
