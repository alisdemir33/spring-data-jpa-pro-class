package com.aliboucoding.jpa.specification;

import com.aliboucoding.jpa.models.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {

    public static Specification<Author> hasAge(int age) {
        return (
                Root<Author> root,
                CriteriaQuery<?> query,
                CriteriaBuilder builder
        ) -> {
            if (age < 0) {
                return null;
            }
            return builder.equal(root.get("age"), age);
        };
    }

    public static Specification<Author> firstnameLike(String firstname) {
        return (
                Root<Author> root,
                CriteriaQuery<?> query,
                CriteriaBuilder builder
        ) -> {
            if (firstname == null) {
                return null;
            }
            return builder.like(root.get("firstName"), "%" + firstname + "%");
        };
    }

    public static Specification<Author> emailContains(String email) {
        return (Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (email == null) {
                return null;
            }
            return builder.like(root.get("email"), "%" + email + "%");
        };
    }

    public static Specification<Author> ageBetween(int minAge, int maxAge) {
        return (Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (minAge < 0 || maxAge < 0) {
                return null;
            }
            return builder.between(root.get("age"), minAge, maxAge);
        };
    }
}
