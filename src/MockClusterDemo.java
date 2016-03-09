import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MockClusterDemo
 *
 * @author kaijiezhou
 * @since Mar, 2016
 */
public class MockClusterDemo {
    private static final Logger LOG = LoggerFactory.getLogger(MockClusterDemo.class);
    public void demo(){
        String topic = "test";
        Node node = new Node(0, "127.0.0.1",9092);
        List<Node> list= new ArrayList<>();
        list.add(node);
        PartitionInfo info=new PartitionInfo(topic, 0, node, list.toArray(new Node[list.size()]), list
                .toArray(new Node[list.size()]));
        List<PartitionInfo> partitions=new ArrayList<>();
        partitions.add(info);
        Set<String> topics= new HashSet<>();
        topics.add(topic);
        Cluster createByNode = new Cluster(list, partitions, topics);
        System.out.println("Node cluster: "+ createByNode.nodes());


        InetSocketAddress isa=new InetSocketAddress("0.0.0.0", 9092);
        List<InetSocketAddress> ilist =  new ArrayList<>();
        ilist.add(isa);
        Cluster createByAddr = Cluster.bootstrap(ilist);
        System.out.println("Address cluster: "+ createByAddr.nodes());


        Cluster emptyDefault = Cluster.empty();
        System.out.println("Empty cluster: "+ emptyDefault.nodes());
    }
    //------------------------------------ Private Methods ---------------------------------------//

}
