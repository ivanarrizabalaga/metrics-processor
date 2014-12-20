package metrics.processor;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "measurements", path = "measurements")
public interface MeasurementRepository extends MongoRepository<Measurement, String> {
	
	/**
	 * Dynamic finder to get values for an specific metric.
	 * @param key Metric id (Ex: 'jvm-usage')
	 * @return List of matching measurements
	 */
	List<Measurement> findByKey(@Param("key") String key);
}
