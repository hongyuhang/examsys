package examsys.first.serviceTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath:applicationContext-main.xml","classpath:applicationContext-mybatis.xml"})
@Transactional
public abstract class AbstractBaseServiceTest {

}
