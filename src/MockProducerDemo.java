import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * MockKafkaProcess
 *
 * @author kaijiezhou
 * @since Mar, 2016
 */
public class MockProducerDemo {
    private static final Logger LOG = LoggerFactory.getLogger(MockProducerDemo.class);

    public void demo() {
        Cluster cluster = Cluster.empty();

        // Can also use this here, since the cluster is not specify:
        //     MockProducer<String, String> producer = new MockProducer<String, String>(false, new
        //                DefaultPartitioner(), new StringSerializer(), new StringSerializer());
        MockProducer<String, String> producer = new MockProducer<String, String>(cluster, false, new
                DefaultPartitioner(), new StringSerializer(), new StringSerializer());

        //Specify the topic, key and value. The message sent is stored in the producer instance
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("test",
                "test-key", "hello!");
        producer.send(record);
        producer.flush();

        //We can check messages using the producer instance.
        //We can check the partition information in cluster instance
        List<ProducerRecord<String, String>> history = producer.history();
        for (ProducerRecord r : history) {
            System.out.println(r.key() + ", " + r.value());
        }


    }
    //------------------------------------ Private Methods ---------------------------------------//


}
