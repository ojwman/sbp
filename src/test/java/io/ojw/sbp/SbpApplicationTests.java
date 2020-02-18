package io.ojw.sbp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.ojw.sbp.domain.User;
import io.ojw.sbp.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
class SbpApplicationTests {
	@Autowired
	private DataSource ds;
	
	@Autowired
	private UserMapper mapper;
	
	@Test
	public void testUserMapper() throws Exception {
		User user = mapper.getLoginInfo("admin");
		System.out.println("User>>" + user);
		assertEquals("관리자", user.getName());
		
		String user_name = mapper.getName("admin");
		assertEquals("관리자", user_name);
	}
	@Test
	public void testDataSource() throws Exception {
		System.out.println("DS=" + ds);
		
		try (Connection con = ds.getConnection()) {
			System.out.println("Coooooon=" + con);
			
			assertThat(con).isInstanceOf(Connection.class);
			
			assertEquals(1041169771, getLong(con, "select cast(phone as bigint) from user"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Long getLong(Connection con, String sql) {
		long result = 0;
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * @Test void contextLoads() { }
	 */

}
