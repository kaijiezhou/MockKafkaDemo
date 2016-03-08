import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.MockConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * MockConsumerDemo
 *
 * @author kaijiezhou
 * @since Mar, 2016
 */
public class MockConsumerDemo {
    private static final Logger LOG = LoggerFactory.getLogger(MockConsumerDemo.class);

    public void demo() {
        MockConsumer<String, String> consumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST);

        //Specify which topic and partition it should follow
        TopicPartition part = new TopicPartition("test", 0);
        consumer.assign(Arrays.asList(part));

        //Reset the start offset of that partition. If the offset strategy is LATEST, we need to
        // reset the end offset.
        Map<TopicPartition, Long> offsetRecord = new HashMap<TopicPartition, Long>();
        offsetRecord.put(part, 0L);
        consumer.updateBeginningOffsets(offsetRecord);

        //Add record to the "Mock Kafka"(or just a list)
        consumer.addRecord(new ConsumerRecord<String, String>("test", 0, 0, "key", "well-say"));

        //poll the data. Here we can also use a  schedulePollTask(Runnable) to add some task
        // during the poll() is running. see: https://kafka.apache.org/090/javadoc/index.html?org/apache/kafka/clients/consumer/KafkaConsumer.html
        ConsumerRecords<String, String> cRecords = consumer.poll(100);
        System.out.println(cRecords.count());
        for (ConsumerRecord r : cRecords) {
            System.out.println(r.key() + ", " + r.value());
        }
    }
    //------------------------------------ Private Methods ---------------------------------------//
}
