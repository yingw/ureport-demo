package cn.wilmar.ureport.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {

}
