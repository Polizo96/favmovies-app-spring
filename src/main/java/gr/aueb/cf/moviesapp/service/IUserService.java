package gr.aueb.cf.moviesapp.service;


import gr.aueb.cf.moviesapp.dto.RegisterUserDTO;
import gr.aueb.cf.moviesapp.model.Favorite;
import gr.aueb.cf.moviesapp.model.User;
import gr.aueb.cf.moviesapp.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {
	public User registerUser(RegisterUserDTO userToRegister);
	public User getUserByUsername(String username) throws UserNotFoundException;
	public Favorite addFavorite(Favorite favorite);
	public List<Favorite> getFavorites(User user);
	public boolean isEmailTaken(String email);
}
