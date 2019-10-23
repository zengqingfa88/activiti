package me.kafeitu.demo.activiti.service.oa.leave;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import me.kafeitu.demo.activiti.entity.oa.Leave;
import me.kafeitu.demo.activiti.factory.CustomGroupEntityManager;
import me.kafeitu.demo.activiti.factory.CustomUserEntityManager;
import me.kafeitu.demo.activiti.user.entity.SysRole;
import me.kafeitu.demo.activiti.user.mapper.RoleRepository;
import me.kafeitu.modules.test.spring.SpringTransactionalTestCase;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
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

	@Autowired
	private RepositoryService repositoryService;

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


	@Test
	public void testDeploy() throws FileNotFoundException {
		// 获取本地文件
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new File("L:\\idea_workspace\\project02\\kft-activiti-demo-master\\src\\main\\resources\\diagrams\\leave-formkey\\leave-formkey.zip"))); // 填写你的zip压缩包绝对路径
// 进行部署
		Deployment deployment = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
		System.out.println(" - deployment: " + deployment);

	}

}
