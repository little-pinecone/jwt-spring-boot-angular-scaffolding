package in.keepgrowing.jwtspringbootangularscaffolding.user.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
