package com.ead.authServer.specifications;

import com.ead.authServer.models.UserCourseModel;
import com.ead.authServer.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

import java.util.UUID;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {
    @And({
            @Spec(path = "userType", spec = Equal.class),
            @Spec(path = "userStatus", spec = Equal.class),
            @Spec(path = "email", spec = Like.class) ,
    })
    public interface UserSpec extends Specification<UserModel> {}
    
    public static Specification<UserModel> userCourseId(final UUID courseId){
    	return (root, query, cb)->{
    		query.distinct(true);
    		Join<UserModel, UserCourseModel> userPro = root.join("userCourses");
    		return cb.equal(userPro.get("courseId"), courseId);
    	};
    }
}
