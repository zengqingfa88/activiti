package me.kafeitu.demo.activiti.service.oa.leave;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import me.kafeitu.demo.activiti.entity.oa.Leave;
import me.kafeitu.demo.activiti.factory.CustomGroupEntityManager;
import me.kafeitu.demo.activiti.factory.CustomUserEntityManager;
import me.kafeitu.demo.activiti.user.entity.SysRole;
import me.kafeitu.demo.activiti.user.mapper.RoleRepository;
import me.kafeitu.modules.test.spring.SpringTransactionalTestCase;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 请假实体管理测试
 *
 * @author HenryYan
 */
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class LeaveManagerTest extends SpringTransactionalTestCase {

	@Autowired
	private LeaveManager leaveManager;

	@Before
	public void setUp() throws Exception {
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	CustomUserEntityManager customUserEntityManager;

	@Autowired
	CustomGroupEntityManager customGroupEntityManager;

	@Test
	public void testUser() {
		List<Group> groupsByUser = customUserEntityManager.findGroupsByUser("1");
		System.out.println(groupsByUser);

	}

	@Test
	public void testGroup() {
		UserEntity userById = customUserEntityManager.findUserById("4");
		System.out.println(userById);
	}

	@Test
	public void testSave() {
		List<SysRole> groupsByUserName = roleRepository.getGroupsByUserName(1l);
		System.out.println(groupsByUserName);
		Leave leave = new Leave();
		leave.setApplyTime(new Date());
		leave.setStartTime(new jodd.datetime.JDateTime("2012-05-22").convertToSqlDate());
		leave.setEndTime(new jodd.datetime.JDateTime("2012-05-23").convertToSqlDate());
		leave.setLeaveType("公休");
		leave.setUserId("kafeitu");
		leave.setReason("no reason");
		leaveManager.saveLeave(leave);
		
		assertNotNull(leave.getId());
		
		Leave newLeave = leaveManager.getLeave(leave.getId());
		assertNotNull(newLeave);
	}

}
