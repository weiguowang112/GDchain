package chaincode

import (
	"fmt"
	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// SmartContract provides functions for managing an Asset
type SmartContract struct {
	contractapi.Contract
}

// Asset describes basic details of what makes up a simple asset
// Insert struct field in alphabetic order => to achieve determinism across languages
// golang keeps the order when marshal to json but doesn't order automatically

// InitLedger adds a base set of assets to the ledger
func (s *SmartContract) InitLedger() error {
	fmt.Println("Chaincode initialization")
	return nil
}

// PutScoreDetailOnChain 溯源信息上链
func (s *SmartContract) PutScoreDetailOnChain(ctx contractapi.TransactionContextInterface, hash string, scoreDetail string) error {

	err := ctx.GetStub().PutState(hash, []byte(scoreDetail))
	if err != nil {
		return fmt.Errorf("将资产存储到状态数据库时发生错误：%v", err)
	}

	fmt.Printf("溯源信息已成功上链：%s\n", hash)

	return nil
}

// GetScoreDetailByHash 根据hash值查询
func (s *SmartContract) GetScoreDetailByHash(ctx contractapi.TransactionContextInterface, hash string) (string, error) {
	scoreDetail, err := ctx.GetStub().GetState(hash)
	if err != nil {
		return "", fmt.Errorf("从状态数据库中获取溯源信息时发生错误：%v", err)
	}

	if scoreDetail == nil {
		return "", fmt.Errorf("溯源信息不存在：%s", hash)
	}
	return string(scoreDetail), nil

}
