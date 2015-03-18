package trymybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMybatis {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		Configuration configuration = sqlSessionFactory.getConfiguration();
		configuration.addMapper(TemperatureMapper.class);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			TemperatureMapper mapper = session
					.getMapper(TemperatureMapper.class);

			Temperature temperature2 = new Temperature();
			temperature2.setTemperature(10.2);
			mapper.insert(temperature2);

			List<Temperature> all = mapper.selectAll();
			for (Temperature temperature : all) {
				System.out.println(temperature.getTemperature());
			}
			
			session.commit();
		} finally {
			session.close();
		}

	}
}
