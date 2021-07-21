package com.aws.crud.demo.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.crud.demo.entity.User;
import com.aws.crud.demo.exception.ResourceNotFoundException;
import com.aws.crud.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		return repository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("Users not found with id :" + userId));
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable (value = "id") Long userId) {
		User existingUser = repository.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("Users not found with id :" + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return repository.save(existingUser);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable (value = "id") Long userId){
		User existingUser = repository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("Users not found with id :" + userId));
		repository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
	
	
}
