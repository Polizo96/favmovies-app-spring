package gr.aueb.cf.moviesapp.service;

import gr.aueb.cf.moviesapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SecurityServiceImpl implements ISecurityService {

	private final AuthenticationManager authenticationManager;

	@Autowired
	public SecurityServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void autoLogin(User user) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				user.getEmail(), user.getPassword(), Collections.<GrantedAuthority>emptyList());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}
}