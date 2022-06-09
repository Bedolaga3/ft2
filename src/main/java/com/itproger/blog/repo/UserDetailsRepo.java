package com.itproger.blog.repo;

import com.itproger.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {

}
