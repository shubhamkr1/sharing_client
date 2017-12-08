/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubham
 */
import org.apache.airavata.sharing.registry.models.*;
import org.apache.airavata.sharing.registry.models.SharingRegistryException;
import org.apache.airavata.sharing.registry.service.cpi.SharingRegistryService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaClientsharing {
     public static void main(String[] arg){
         
    try{     
    String serverHost = "localhost";
    int serverPort = 7878;

    TTransport transport = new TSocket(serverHost, serverPort);
    transport.open();
    TBinaryProtocol protocol = new TBinaryProtocol(transport);
    SharingRegistryService.Client client = new SharingRegistryService.Client(protocol);
        
         Domain domain = new Domain();
         domain.setName("test-domain");
    
         //domain id will be same as domain name
         String domainId = client.createDomain(domain);
         
         User user1 = new User();
        String userName = "test-user-1";
        String userId1 =  "test-user-1";
    //required
        user1.setUserId(userId1);
    //required
        user1.setUserName(userName);
    //required
        user1.setDomainId(domainId);
    //required
        user1.setFirstName("John");
    //required
        user1.setLastName("Doe");
    //required
        user1.setEmail("john.doe@abc.com");
   
         client.createUser(user1);
         
        UserGroup userGroup1 = new UserGroup();
        //required
        userGroup1.setGroupId("test-group-1");
        //required
        userGroup1.setDomainId(domainId);
        //required
        userGroup1.setName("test-group-1");
        //optional
        userGroup1.setDescription("test group description");
        //required
        userGroup1.setOwnerId("test-user-1");
        //required
        userGroup1.setGroupType(GroupType.USER_LEVEL_GROUP);
        
        userGroup1.setGroupVisibility(GroupVisibility.PUBLIC);

        client.createGroup(userGroup1);
        
        UserGroup ugp = new UserGroup();
        ugp = client.getGroup(domainId, "test-group-1");
        System.out.println(ugp.getGroupVisibility());
      }
     catch(Exception e)
     {
        e.printStackTrace(); 
     }
    }
    
}
