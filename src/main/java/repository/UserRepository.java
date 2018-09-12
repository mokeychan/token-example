package repository;

import entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description: User类的CRUD操作
 * @Author: chenfeixiang
 * @Date: Created in 18:01 2018/9/11
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String name);

}
