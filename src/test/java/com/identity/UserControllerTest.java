package com.identity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.identity.common.utils.ResponseObject;
import com.identity.controller.UserController;
import com.identity.dto.UserRequest;
import com.identity.model.User;
import com.identity.repository.UserDetailRepository;
import com.identity.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	UserDetailRepository userDetailRepo;
	
	@Test
	public void testCreateUser() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
//		User user = new User();
//		when(userRepo.save(any(User.class))).thenReturn(user);
//		user.setAccessLevel("2");
//		user.setUserName("mwchan");
//		user.setType("admin");
		
//		UserDetail userDetail = new UserDetail();
//		when(userDetailRepo.save(any(UserDetail.class))).thenReturn(userDetail);

		UserRequest req = new UserRequest();
		req.setUserName("mwchan");
		req.setType("admin");
		req.setAccessLevel(2);
		req.setFullName("Chan Meng Woon");
		req.setEmail("test@test.com");
		req.setPhone("0126868668");
		
		/** todo */
		ResponseEntity<ResponseObject> responseEntity = userController.insertUser(req);
			
		assertThat(responseEntity.getStatusCode()).isEqualTo(200);
//		assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/128");
	}
}
