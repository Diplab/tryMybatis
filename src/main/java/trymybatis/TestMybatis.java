package trymybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
			for (int i = 0; i < 10; i++) {
				Temperature temperature = new Temperature();
				temperature.setTemperature(15 + 30 * Math.random());
				temperature.setTime(new Date((long) (Long.MAX_VALUE * Math
						.random())));
				mapper.insert(temperature);
			}

			List<Temperature> all = mapper.selectAll();

			for (Temperature temperature : all) {
				System.out.format("%s %f \n", temperature.getTime()
						.toLocaleString(), temperature.getTemperature());
			}

		} finally {
			session.close();
		}

	}
}
