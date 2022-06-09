package com.itproger.blog.repo;

import com.itproger.blog.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {

}
