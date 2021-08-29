package br.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.model.FaixaCep;
import br.com.model.Permission;
import br.com.model.User;
import br.com.repository.FaixaCepRepository;
import br.com.repository.PermissionRepository;
import br.com.repository.UserRepository;

@Configuration
public class IniciarDB implements CommandLineRunner{

	@Autowired
	private FaixaCepRepository repositoryFaixaCep;
	
	@Autowired
	private UserRepository repositoryUser;
	
	@Autowired
	private PermissionRepository repositoryPermission;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		repositoryFaixaCep.deleteAll();
		FaixaCep model = new FaixaCep();
		model.setCodigoLoja("LOJA_PINHEIROS");
		model.setFaixaInicio(10000000);
		model.setFaixaFim(20000000);
		repositoryFaixaCep.save(model);
		
		repositoryUser.deleteAll();
		User user = new User();
		user.setUserName("admin");
		user.setFullName("admin");
		user.setPassword("$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		
		repositoryPermission.deleteAll();
		Permission permission = new Permission();
		permission.setDescription("ADMIN");
		permission = repositoryPermission.save(permission);
		
		user.getPermissions().add(permission);
		repositoryUser.save(user);
		
	}

}
