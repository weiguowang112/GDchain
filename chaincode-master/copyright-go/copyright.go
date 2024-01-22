/*
SPDX-License-Identifier: Apache-2.0
*/

package main

import (
	"fmt"
	"github.com/hyperledger/fabric-contract-api-go/contractapi"
	"github.com/hyperledger/fabric-samples/asset-transfer-basic/chaincode-go/chaincode"
)

func main() {
	copyrightChaincode, err := contractapi.NewChaincode(&chaincode.CopyrightChaincode{})
	if err != nil {
		fmt.Printf("Error creating copyright chaincode: %v", err)
		return
	}

	if err := copyrightChaincode.Start(); err != nil {
		fmt.Printf("Error starting copyright chaincode: %v", err)
	}
}
