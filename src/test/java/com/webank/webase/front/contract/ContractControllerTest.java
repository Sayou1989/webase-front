/**
 * Copyright 2014-2019  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.front.contract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webank.webase.front.Application;
import com.webank.webase.front.contract.entity.ReqContractSave;
import com.webank.webase.front.contract.entity.ReqDeploy;
import com.webank.webase.front.contract.entity.ReqDeployWithSign;
import com.webank.webase.front.contract.entity.ReqPageContract;

import java.util.ArrayList;
import java.util.List;
import org.fisco.bcos.web3j.protocol.core.methods.response.AbiDefinition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ContractControllerTest {

    private MockMvc mockMvc;
    private Integer groupId = 1;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testSaveContract() throws Exception {
        //test new
        ReqContractSave testNew = new ReqContractSave();
        testNew.setGroupId(groupId);
        testNew.setContractName("a");
        testNew.setContractPath("myPath");
        testNew.setContractSource(
            "cHJhZ21hIHNvbGlkaXR5IF4wLjQuMjsNCmNvbnRyYWN0IE9rew0KICAgIA0KICAgIHN0cnVjdCBBY2NvdW50ew0KICAgICAgICBhZGRyZXNzIGFjY291bnQ7DQogICAgICAgIHVpbnQgYmFsYW5jZTsNCiAgICB9DQogICAgDQogICAgc3RydWN0ICBUcmFuc2xvZyB7DQogICAgICAgIHN0cmluZyB0aW1lOw0KICAgICAgICBhZGRyZXNzIGZyb207DQogICAgICAgIGFkZHJlc3MgdG87DQogICAgICAgIHVpbnQgYW1vdW50Ow0KICAgIH0NCiAgICANCiAgICBBY2NvdW50IGZyb207DQogICAgQWNjb3VudCB0bzsNCiAgICANCiAgICBUcmFuc2xvZ1tdIGxvZzsNCg0KICAgIGZ1bmN0aW9uIE9rKCl7DQogICAgICAgIGZyb20uYWNjb3VudD0weDE7DQogICAgICAgIGZyb20uYmFsYW5jZT0xMDAwMDAwMDAwMDsNCiAgICAgICAgdG8uYWNjb3VudD0weDI7DQogICAgICAgIHRvLmJhbGFuY2U9MDsNCg0KICAgIH0NCiAgICBmdW5jdGlvbiBnZXQoKWNvbnN0YW50IHJldHVybnModWludCl7DQogICAgICAgIHJldHVybiB0by5iYWxhbmNlOw0KICAgIH0NCiAgICBmdW5jdGlvbiB0cmFucyh1aW50IG51bSl7DQogICAgCWZyb20uYmFsYW5jZT1mcm9tLmJhbGFuY2UtbnVtOw0KICAgIAl0by5iYWxhbmNlKz1udW07DQogICAgDQogICAgCWxvZy5wdXNoKFRyYW5zbG9nKCIyMDE3MDQxMyIsZnJvbS5hY2NvdW50LHRvLmFjY291bnQsbnVtKSk7DQogICAgfQ0KDQoNCg0KfQ==");
        testNew.setBytecodeBin(
            "6060604052341561000c57fe5b5b6001600060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506402540be4006000600101819055506002600260000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060006002600101819055505b5b610443806100c26000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");
        testNew.setContractAbi(
            "[{\"constant\":false,\"inputs\":[{\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"trans\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"type\":\"constructor\"}]");
        testNew.setContractBin(
            "60606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");

        //test update
        ReqContractSave testUpdate = new ReqContractSave();
        testUpdate.setGroupId(groupId);
        testUpdate.setContractId(1L);
        testUpdate.setContractName("Ooook");
        testUpdate.setContractPath("myPath");
        testUpdate.setContractSource(
            "cHJhZ21hIHNvbGlkaXR5IF4wLjQuMjsNCmNvbnRyYWN0IE9rew0KICAgIA0KICAgIHN0cnVjdCBBY2NvdW50ew0KICAgICAgICBhZGRyZXNzIGFjY291bnQ7DQogICAgICAgIHVpbnQgYmFsYW5jZTsNCiAgICB9DQogICAgDQogICAgc3RydWN0ICBUcmFuc2xvZyB7DQogICAgICAgIHN0cmluZyB0aW1lOw0KICAgICAgICBhZGRyZXNzIGZyb207DQogICAgICAgIGFkZHJlc3MgdG87DQogICAgICAgIHVpbnQgYW1vdW50Ow0KICAgIH0NCiAgICANCiAgICBBY2NvdW50IGZyb207DQogICAgQWNjb3VudCB0bzsNCiAgICANCiAgICBUcmFuc2xvZ1tdIGxvZzsNCg0KICAgIGZ1bmN0aW9uIE9rKCl7DQogICAgICAgIGZyb20uYWNjb3VudD0weDE7DQogICAgICAgIGZyb20uYmFsYW5jZT0xMDAwMDAwMDAwMDsNCiAgICAgICAgdG8uYWNjb3VudD0weDI7DQogICAgICAgIHRvLmJhbGFuY2U9MDsNCg0KICAgIH0NCiAgICBmdW5jdGlvbiBnZXQoKWNvbnN0YW50IHJldHVybnModWludCl7DQogICAgICAgIHJldHVybiB0by5iYWxhbmNlOw0KICAgIH0NCiAgICBmdW5jdGlvbiB0cmFucyh1aW50IG51bSl7DQogICAgCWZyb20uYmFsYW5jZT1mcm9tLmJhbGFuY2UtbnVtOw0KICAgIAl0by5iYWxhbmNlKz1udW07DQogICAgDQogICAgCWxvZy5wdXNoKFRyYW5zbG9nKCIyMDE3MDQxMyIsZnJvbS5hY2NvdW50LHRvLmFjY291bnQsbnVtKSk7DQogICAgfQ0KDQoNCg0KfQ==");
        testUpdate.setBytecodeBin(
            "6060604052341561000c57fe5b5b6001600060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506402540be4006000600101819055506002600260000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060006002600101819055505b5b610443806100c26000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");
        testUpdate.setContractAbi(
            "[{\"constant\":false,\"inputs\":[{\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"trans\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"type\":\"constructor\"}]");
        testUpdate.setContractBin(
            "60606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/contract/save").
            content(JSON.toJSONString(testNew)).
            contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.
            andExpect(MockMvcResultMatchers.status().isOk()).
            andDo(MockMvcResultHandlers.print());
        System.out
            .println("response:" + resultActions.andReturn().getResponse().getContentAsString());
    }


    @Test
    public void testDeploy() throws Exception {
        String abiInfo = "[{\"constant\":false,\"inputs\":[{\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"trans\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"type\":\"constructor\"}]";
        JSONArray abiArray = JSONArray.parseArray(abiInfo);
        List<AbiDefinition> list = JSONObject
            .parseArray(abiArray.toJSONString(), AbiDefinition.class);
        //param
        ReqDeploy deployInputParam = new ReqDeploy();
        deployInputParam.setGroupId(groupId);
        deployInputParam.setUser("0x58415e44c664af1eab071da728a8405afa1991f3");
        deployInputParam.setContractId(1L);
        deployInputParam.setContractName("a");
        deployInputParam.setContractSource(
            "cHJhZ21hIHNvbGlkaXR5IF4wLjQuMjsNCmNvbnRyYWN0IE9rew0KICAgIA0KICAgIHN0cnVjdCBBY2NvdW50ew0KICAgICAgICBhZGRyZXNzIGFjY291bnQ7DQogICAgICAgIHVpbnQgYmFsYW5jZTsNCiAgICB9DQogICAgDQogICAgc3RydWN0ICBUcmFuc2xvZyB7DQogICAgICAgIHN0cmluZyB0aW1lOw0KICAgICAgICBhZGRyZXNzIGZyb207DQogICAgICAgIGFkZHJlc3MgdG87DQogICAgICAgIHVpbnQgYW1vdW50Ow0KICAgIH0NCiAgICANCiAgICBBY2NvdW50IGZyb207DQogICAgQWNjb3VudCB0bzsNCiAgICANCiAgICBUcmFuc2xvZ1tdIGxvZzsNCg0KICAgIGZ1bmN0aW9uIE9rKCl7DQogICAgICAgIGZyb20uYWNjb3VudD0weDE7DQogICAgICAgIGZyb20uYmFsYW5jZT0xMDAwMDAwMDAwMDsNCiAgICAgICAgdG8uYWNjb3VudD0weDI7DQogICAgICAgIHRvLmJhbGFuY2U9MDsNCg0KICAgIH0NCiAgICBmdW5jdGlvbiBnZXQoKWNvbnN0YW50IHJldHVybnModWludCl7DQogICAgICAgIHJldHVybiB0by5iYWxhbmNlOw0KICAgIH0NCiAgICBmdW5jdGlvbiB0cmFucyh1aW50IG51bSl7DQogICAgCWZyb20uYmFsYW5jZT1mcm9tLmJhbGFuY2UtbnVtOw0KICAgIAl0by5iYWxhbmNlKz1udW07DQogICAgDQogICAgCWxvZy5wdXNoKFRyYW5zbG9nKCIyMDE3MDQxMyIsZnJvbS5hY2NvdW50LHRvLmFjY291bnQsbnVtKSk7DQogICAgfQ0KDQoNCg0KfQ==");
        deployInputParam.setBytecodeBin(
            "6060604052341561000c57fe5b5b6001600060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506402540be4006000600101819055506002600260000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060006002600101819055505b5b610443806100c26000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");
        deployInputParam.setAbiInfo(list);
        deployInputParam.setContractBin(
            "60606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806366c99139146100465780636d4ce63c14610066575bfe5b341561004e57fe5b610064600480803590602001909190505061008c565b005b341561006e57fe5b610076610264565b6040518082815260200191505060405180910390f35b806000600101540360006001018190555080600260010160008282540192505081905550600480548060010182816100c49190610272565b916000526020600020906004020160005b608060405190810160405280604060405190810160405280600881526020017f32303137303431330000000000000000000000000000000000000000000000008152508152602001600060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600260000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000190805190602001906101c49291906102a4565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301555050505b50565b600060026001015490505b90565b81548183558181151161029f5760040281600402836000526020600020918201910161029e9190610324565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e557805160ff1916838001178555610313565b82800160010185558215610313579182015b828111156103125782518255916020019190600101906102f7565b5b50905061032091906103aa565b5090565b6103a791905b808211156103a357600060008201600061034491906103cf565b6001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090555060040161032a565b5090565b90565b6103cc91905b808211156103c85760008160009055506001016103b0565b5090565b90565b50805460018160011615610100020316600290046000825580601f106103f55750610414565b601f01602090049060005260206000209081019061041391906103aa565b5b505600a165627a7a72305820d453cb481a312519166e409e7248d76d8c2672458c08b9500945a4004a1b69020029");

        ResultActions resultActions = mockMvc
            .perform(MockMvcRequestBuilders.post("/contract/deploy").
                content(JSON.toJSONString(deployInputParam)).
                contentType(MediaType.APPLICATION_JSON)
            );
        resultActions.
            andExpect(MockMvcResultMatchers.status().isOk()).
            andDo(MockMvcResultHandlers.print());
        System.out
            .println("response:" + resultActions.andReturn().getResponse().getContentAsString());
    }


    @Test
    public void testQueryContractList() throws Exception {
        ReqPageContract param = new ReqPageContract();
        param.setGroupId(groupId);
        //  param.setContractStatus(2);
         param.setContractName("a");
        // param.setContractAddress("0x19146d3a2f138aacb97ac52dd45dd7ba7cb3e04a");
        param.setPageNumber(0);
        param.setPageSize(10);
        // param.setContractAddress("bb4c11a3b38e931e");

        ResultActions resultActions = mockMvc
            .perform(MockMvcRequestBuilders.post("/contract/contractList").
                content(JSON.toJSONString(param)).
                contentType(MediaType.APPLICATION_JSON)
            );
        resultActions.
            andExpect(MockMvcResultMatchers.status().isOk()).
            andDo(MockMvcResultHandlers.print());
        System.out
            .println("response:" + resultActions.andReturn().getResponse().getContentAsString());
    }

    /**
     * test deploy contract with webase-sign
     * @throws Exception
     */
    @Test
    public void testDeployWithSign() throws Exception {
        ReqDeployWithSign param = new ReqDeployWithSign();
        param.setSignAddress("0xf16c0bf5a8bf4049ede4c3a070efcc1052095f63");
        param.setGroupId(groupId);
        param.setFuncParam(new ArrayList<>());
        String bin = "608060405234801561001057600080fd5b50610372806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634ed3885e146100515780636d4ce63c146100ba575b600080fd5b34801561005d57600080fd5b506100b8600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061014a565b005b3480156100c657600080fd5b506100cf6101ff565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561010f5780820151818401526020810190506100f4565b50505050905090810190601f16801561013c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b7ecb39d6c2c520f0597db0021367767c48fef2964cf402d3c9e9d4df12e43964816040518080602001828103825283818151815260200191508051906020019080838360005b838110156101ab578082015181840152602081019050610190565b50505050905090810190601f1680156101d85780820380516001836020036101000a031916815260200191505b509250505060405180910390a180600090805190602001906101fb9291906102a1565b5050565b606060008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102975780601f1061026c57610100808354040283529160200191610297565b820191906000526020600020905b81548152906001019060200180831161027a57829003601f168201915b5050505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e257805160ff1916838001178555610310565b82800160010185558215610310579182015b8281111561030f5782518255916020019190600101906102f4565b5b50905061031d9190610321565b5090565b61034391905b8082111561033f576000816000905550600101610327565b5090565b905600a165627a7a72305820da27217609c9c343ff5bcedb71d953278f6839c9c1b131f316835286ceb7b6c70029";
        param.setBytecodeBin(bin);
        String abiStr = "[{\"constant\":false,\"inputs\":[{\"name\":\"n\",\"type\":\"string\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"name\",\"type\":\"string\"}],\"name\":\"Test\",\"type\":\"event\"}]";
        List<Object> abiList = JSON.parseArray(abiStr);
        param.setContractAbi(abiList);
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/contract/deployWithSign").
                        content(JSON.toJSONString(param)).
                        contentType(MediaType.APPLICATION_JSON)
                );
        resultActions.
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }
/*

    @Test
    public void tesSendTransaction() throws Exception {
        //abi
        String abiStr = "[{\"constant\":false,\"inputs\":[{\"name\":\"num\",\"type\":\"uint256\"}],\"name\":\"trans\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"a\",\"type\":\"string\"}],\"name\":\"abb\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"b\",\"type\":\"string\"}],\"name\":\"bba\",\"type\":\"event\"}]";
        List<Object> abiList = JSONArray.parseArray(abiStr);

        //param
        TransactionInputParam param = new TransactionInputParam();
        param.setContractId(200069);
        param.setGroupId(groupId);
        param.setAbiInfo(abiList);
        param.setUserId(userId);
        param.setVersion(version);
        param.setContractName("Ok");
        param.setFuncName("trans");
        param.setFuncParam(Arrays.asList(3));

        //if make exception
        param.setFuncParam(Arrays.asList("asdfasfasd"));

        ResultActions resultActions = mockMvc
            .perform(MockMvcRequestBuilders.post("/contract/transaction").
                content(JSON.toJSONString(param)).
                contentType(MediaType.APPLICATION_JSON)
            );
        resultActions.
            andExpect(MockMvcResultMatchers.status().isOk()).
            andDo(MockMvcResultHandlers.print());
        System.out
            .println("response:" + resultActions.andReturn().getResponse().getContentAsString());
    }
*/


}