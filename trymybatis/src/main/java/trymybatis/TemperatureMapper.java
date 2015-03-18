package trymybatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TemperatureMapper {

	@Select("SELECT * FROM DIP_TEMPERATURE")
	@Results(value = { @Result(column = "TEMPERATURE_", property = "temperature") })
	List<Temperature> selectAll();

	@Insert("INSERT INTO DIP_TEMPERATURE (TEMPERATURE_) VALUES (#{temperature})")
	void insert(Temperature temperature);
}
