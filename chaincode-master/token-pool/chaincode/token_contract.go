package chaincode

import (
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// Token is the main contract for this chaincode
type Token struct {
	Name        string `json:"name"`
	Symbol      string `json:"symbol"`
	Decimals    uint8  `json:"decimals"`
	TotalSupply uint64 `json:"totalSupply"`
}

type Account struct {
	Address string
	Tokens  map[string]uint64
}

// EventStruct burn，mint，transfer事件数据结构
type EventStruct struct {
	TokenName string
	amount    uint64
}

// SmartContract provides functions for transferring tokens between accounts
type SmartContract struct {
	Account
	Token
	EventStruct
	contractapi.Contract
}

//// event provides an organized struct for emitting events
//type event struct {
//	From  string `json:"from"`
//	To    string `json:"to"`
//	Value int    `json:"value"`
//}

// GetToken 根据名字获取对应的token实例
func (s *SmartContract) GetToken(ctx contractapi.TransactionContextInterface, name string) (*Token, error) {
	// Retrieve the state of the ledger for the given name
	tokenBytes, err := ctx.GetStub().GetState(name)
	if err != nil {
		return nil, fmt.Errorf("failed to read from world state: %v", err)
	}
	if tokenBytes == nil {
		return nil, fmt.Errorf("token %s does not exist", name)
	}

	// Unmarshal the state into a Token struct
	var token Token
	err = json.Unmarshal(tokenBytes, &token)
	if err != nil {
		return nil, fmt.Errorf("failed to unmarshal token: %v", err)
	}

	return &token, nil
}

func (s *SmartContract) DeployToken(ctx contractapi.TransactionContextInterface, name string, symbol string, decimals uint8, totalSupply uint64) error {
	//账户处理
	clientAccountID, err := ctx.GetClientIdentity().GetID()
	accountBytes, err := ctx.GetStub().GetState(clientAccountID)
	if err != nil {
		return fmt.Errorf("failed to read from world state: %v", err)
	}
	if accountBytes == nil {
		account := Account{
			Address: clientAccountID, Tokens: map[string]uint64{name: totalSupply},
		}
		accountJSON, err := json.Marshal(account)
		if err != nil {
			return err
		}

		err = ctx.GetStub().PutState(account.Address, accountJSON)
		if err != nil {
			return err
		}
	} else {
		var account Account
		err = json.Unmarshal(accountBytes, &account)
		if err != nil {
			return fmt.Errorf("failed to unmarshal account JSON: %v", err)
		}
		//这里需要判断名字是不是重复
		if _, ok := account.Tokens[name]; ok {
			return fmt.Errorf("token already exist")
		}

		account.Tokens[name] = totalSupply
		accountJSON, err := json.Marshal(account)
		if err != nil {
			return err
		}

		err = ctx.GetStub().PutState(account.Address, accountJSON)
		if err != nil {
			return err
		}
	}

	// Create a new Token struct with the given parameters
	token := &Token{
		Name:        name,
		Symbol:      symbol,
		Decimals:    decimals,
		TotalSupply: totalSupply,
	}

	// Marshal the Token struct into bytes
	tokenBytes, err := json.Marshal(token)
	if err != nil {
		return fmt.Errorf("failed to marshal token: %v", err)
	}

	// Store the Token struct in the ledger
	err = ctx.GetStub().PutState(name, tokenBytes)
	if err != nil {
		return fmt.Errorf("failed to store token: %v", err)
	}

	//将部署token事件记录下来
	return ctx.GetStub().SetEvent("DeployToken", tokenBytes)
}
func (s *SmartContract) Mint(ctx contractapi.TransactionContextInterface, amount uint64, tokeName string) error {
	//账户token处理
	clientAccountID, err := ctx.GetClientIdentity().GetID()
	accountBytes, err := ctx.GetStub().GetState(clientAccountID)
	if err != nil {
		return fmt.Errorf("failed to read from world state: %v", err)
	}
	var account Account
	err = json.Unmarshal(accountBytes, &account)
	if err != nil {
		return fmt.Errorf("failed to unmarshal account JSON: %v", err)
	}

	account.Tokens[tokeName] += amount
	accountJSON, err := json.Marshal(account)
	if err != nil {
		return err
	}
	err = ctx.GetStub().PutState(account.Address, accountJSON)
	if err != nil {
		return err
	}

	//token实例处理，全局token处理
	// Retrieve the current state of the token from the ledger
	tokenBytes, err := ctx.GetStub().GetState(tokeName)
	if err != nil {
		return fmt.Errorf("failed to read from world state: %v", err)
	}
	if tokenBytes == nil {
		return fmt.Errorf("token %s does not exist", tokeName)
	}

	// Unmarshal the state into a Token struct
	var token Token
	err = json.Unmarshal(tokenBytes, &token)
	if err != nil {
		return fmt.Errorf("failed to unmarshal token: %v", err)
	}

	// Update the total supply of the token
	token.TotalSupply += amount

	// Marshal the updated Token struct into bytes
	tokenBytes, err = json.Marshal(token)
	if err != nil {
		return fmt.Errorf("failed to marshal token: %v", err)
	}

	// Store the updated Token struct in the ledger
	err = ctx.GetStub().PutState(tokeName, tokenBytes)
	if err != nil {
		return fmt.Errorf("failed to store token: %v", err)
	}

	//将mint了多少个token记录下来
	var tokenEvent EventStruct
	tokenEvent.TokenName = tokeName
	tokenEvent.amount = amount
	tokenEventBytes, err := json.Marshal(tokenEvent)
	return ctx.GetStub().SetEvent("MintToken", tokenEventBytes)
}

func (s *SmartContract) Burn(ctx contractapi.TransactionContextInterface, amount uint64, tokeName string) error {
	//账户处理
	clientAccountID, err := ctx.GetClientIdentity().GetID()
	accountBytes, err := ctx.GetStub().GetState(clientAccountID)
	if err != nil {
		return fmt.Errorf("failed to read from world state: %v", err)
	}
	var account Account
	err = json.Unmarshal(accountBytes, &account)
	if err != nil {
		return fmt.Errorf("failed to unmarshal account JSON: %v", err)
	}
	if account.Tokens[tokeName] < amount {
		return fmt.Errorf("not enough balance to burn")
	}
	account.Tokens[tokeName] -= amount
	accountJSON, err := json.Marshal(account)
	if err != nil {
		return err
	}
	err = ctx.GetStub().PutState(account.Address, accountJSON)
	if err != nil {
		return err
	}

	//token实例处理
	// Retrieve the current state of the token from the ledger
	tokenBytes, err := ctx.GetStub().GetState(tokeName)
	if err != nil {
		return fmt.Errorf("failed to read from world state: %v", err)
	}
	if tokenBytes == nil {
		return fmt.Errorf("token %s does not exist", tokeName)
	}

	// Unmarshal the state into a Token struct
	var token Token
	err = json.Unmarshal(tokenBytes, &token)
	if err != nil {
		return fmt.Errorf("failed to unmarshal token: %v", err)
	}

	// Check if the token has enough balance to burn
	if token.TotalSupply < amount {
		return fmt.Errorf("not enough balance to burn")
	}

	// Update the total supply of the token
	token.TotalSupply -= amount

	// Marshal the updated Token struct into bytes
	tokenBytes, err = json.Marshal(token)
	if err != nil {
		return fmt.Errorf("failed to marshal token: %v", err)
	}

	// Store the updated Token struct in the ledger
	err = ctx.GetStub().PutState(tokeName, tokenBytes)
	if err != nil {
		return fmt.Errorf("failed to store token: %v", err)
	}

	//将burn了多少个token记录下来
	var tokenEvent EventStruct
	tokenEvent.TokenName = tokeName
	tokenEvent.amount = amount
	tokenEventBytes, err := json.Marshal(tokenEvent)
	return ctx.GetStub().SetEvent("BurnToken", tokenEventBytes)
}

// Transfer Transfer不改变全局token的数量，只改变to from两个人各自账户对应token的数量
func (s *SmartContract) Transfer(ctx contractapi.TransactionContextInterface, to string, amount uint64, tokeName string) error {
	//amount超过from数量不能转
	//账户处理
	fromAccountID, err := ctx.GetClientIdentity().GetID()
	accountBytes, err := ctx.GetStub().GetState(fromAccountID)
	if err != nil {
		return fmt.Errorf("failed to read from world state: %v", err)
	}
	var account Account
	err = json.Unmarshal(accountBytes, &account)
	if err != nil {
		return fmt.Errorf("failed to unmarshal account JSON: %v", err)
	}
	if account.Tokens[tokeName] < amount {
		return fmt.Errorf("not enough balance to transfer")
	}
	account.Tokens[tokeName] -= amount
	accountJSON, err := json.Marshal(account)
	if err != nil {
		return err
	}
	err = ctx.GetStub().PutState(account.Address, accountJSON)
	if err != nil {
		return err
	}
	//修改接收方账户钱包
	toaccountBytes, err := ctx.GetStub().GetState(to)
	if err != nil {
		return fmt.Errorf("failed to read from world state: %v", err)
	}
	if toaccountBytes == nil {
		account := Account{
			Address: to, Tokens: map[string]uint64{tokeName: amount},
		}
		accountJSON, err := json.Marshal(account)
		if err != nil {
			return err
		}
		err = ctx.GetStub().PutState(account.Address, accountJSON)
		if err != nil {
			return err
		}
	} else {
		var account Account
		err = json.Unmarshal(toaccountBytes, &account)
		if err != nil {
			return fmt.Errorf("failed to unmarshal account JSON: %v", err)
		}
		_, ok := account.Tokens[tokeName]
		if ok {
			account.Tokens[tokeName] += amount
		} else {
			account.Tokens[tokeName] = amount
		}
		accountJSON, err := json.Marshal(account)
		if err != nil {
			return err
		}
		err = ctx.GetStub().PutState(account.Address, accountJSON)
		if err != nil {
			return err
		}
	}
	//将transfer了多少个token记录下来
	var tokenEvent EventStruct
	tokenEvent.TokenName = tokeName
	tokenEvent.amount = amount
	tokenEventBytes, err := json.Marshal(tokenEvent)
	return ctx.GetStub().SetEvent("TransferToken", tokenEventBytes)
}

func (s *SmartContract) ClientAccountID(ctx contractapi.TransactionContextInterface) (string, error) {
	// Get ID of submitting client identity
	clientAccountID, err := ctx.GetClientIdentity().GetID()
	if err != nil {
		return "", fmt.Errorf("failed to get client id: %v", err)
	}

	return clientAccountID, nil
}

// ClientAccountBalances 查看token的余额
func (s *SmartContract) ClientAccountBalances(ctx contractapi.TransactionContextInterface) (map[string]uint64, error) {
	// Get ID of submitting client identity
	clientID, err := ctx.GetClientIdentity().GetID()
	if err != nil {
		return nil, fmt.Errorf("failed to get client id: %v", err)
	}

	accountBytes, err := ctx.GetStub().GetState(clientID)
	if err != nil {
		return nil, fmt.Errorf("failed to read from world state: %v", err)
	}
	if accountBytes == nil {
		return nil, fmt.Errorf("the account %s does not exist", clientID)
	}

	var account Account
	err = json.Unmarshal(accountBytes, &account)
	if err != nil {
		return nil, fmt.Errorf("failed to unmarshal account JSON: %v", err)
	}

	return account.Tokens, nil
}
