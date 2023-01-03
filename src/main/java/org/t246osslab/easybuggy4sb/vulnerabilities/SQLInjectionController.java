package org.t246osslab.easybuggy4sb.vulnerabilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.t246osslab.easybuggy4sb.Config;
import org.t246osslab.easybuggy4sb.controller.AbstractController;
import org.t246osslab.easybuggy4sb.core.model.User;

@Controller
public class SQLInjectionController extends AbstractController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private List<User> selectUsers(String name, String password) {
		String sql = "SELECT  name, secret from USERS where name='"+ name + "' or password='"+ password + "'" ;
		return jdbcTemplate.query(sql, new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setName(rs.getString("name"));
                        user.setSecret(rs.getString("secret"));
                        return user;
                    }
				});
	}
}
