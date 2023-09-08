package gr.aueb.cf.moviesapp.service;



import gr.aueb.cf.moviesapp.dto.RegisterUserDTO;
import gr.aueb.cf.moviesapp.model.Favorite;
import gr.aueb.cf.moviesapp.model.User;
import gr.aueb.cf.moviesapp.repository.FavoriteRepository;
import gr.aueb.cf.moviesapp.repository.UserRepository;
import gr.aueb.cf.moviesapp.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepository;

	private final FavoriteRepository favoriteRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, FavoriteRepository favoriteRepository) {
		this.userRepository = userRepository;
		this.favoriteRepository = favoriteRepository;
	}

	@Transactional
	@Override
	public User registerUser(RegisterUserDTO userToRegister) {
		return userRepository.save(convertToUser(userToRegister));
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		User user = userRepository.getUserByEmail(username);
		if (user == null) {
			throw new UserNotFoundException("User " + username + " not found.");
		}
		return user;
	}

	@Override
	public Favorite addFavorite(Favorite favorite) {
		return favoriteRepository.save(favorite);
	}

	@Override
	public List<Favorite> getFavorites(User user) {
		return userRepository.getReferenceById(user.getId()).getFavorites();
	}

	@Override
	public boolean isEmailTaken(String email) {
		return userRepository.emailExists(email);
	}

	private static User convertToUser(RegisterUserDTO userDTO) {
		return new User(userDTO.getEmail(), userDTO.getPassword());
	}

}
