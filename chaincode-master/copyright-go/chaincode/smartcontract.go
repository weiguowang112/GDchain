package chaincode

import (
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// CopyrightChaincode represents the chaincode for copyright management
type CopyrightChaincode struct {
	contractapi.Contract
}

// CopyrightAsset represents the copyright asset
type CopyrightAsset struct {
	Hash  string `json:"hash"`
	Title string `json:"title"`
	Owner string `json:"owner"`
	Buyer string `json:"buyer"`
}

// Init initializes the chaincode
func (cc *CopyrightChaincode) Init(ctx contractapi.TransactionContextInterface) error {
	fmt.Println("Chaincode initialization")
	return nil
}

// PurchaseCopyright allows a buyer to purchase a copyright asset
func (cc *CopyrightChaincode) PurchaseCopyright(ctx contractapi.TransactionContextInterface, hash string, title string, owner string, buyer string) error {
	exists, err := cc.assetExists(ctx, hash)
	if err != nil {
		return fmt.Errorf("failed to check if asset exists: %v", err)
	}
	if exists {
		return fmt.Errorf("asset with hash %s already exists", hash)
	}

	copyright := CopyrightAsset{
		Hash:  hash,
		Title: title,
		Owner: owner,
		Buyer: buyer,
	}

	err = ctx.GetStub().PutState(hash, []byte(cc.encodeAssetToJSON(&copyright)))
	if err != nil {
		return fmt.Errorf("failed to purchase copyright: %v", err)
	}

	return nil
}

// GetCopyrightByHash retrieves the copyright asset by hash
func (cc *CopyrightChaincode) GetCopyrightByHash(ctx contractapi.TransactionContextInterface, hash string) (*CopyrightAsset, error) {
	assetJSON, err := ctx.GetStub().GetState(hash)
	if err != nil {
		return nil, fmt.Errorf("failed to read asset: %v", err)
	}
	if assetJSON == nil {
		return nil, fmt.Errorf("asset with hash %s does not exist", hash)
	}

	var copyright CopyrightAsset
	err = cc.decodeAssetFromJSON(assetJSON, &copyright)
	if err != nil {
		return nil, err
	}

	return &copyright, nil
}

// assetExists checks if an asset with the given hash exists
func (cc *CopyrightChaincode) assetExists(ctx contractapi.TransactionContextInterface, hash string) (bool, error) {
	assetJSON, err := ctx.GetStub().GetState(hash)
	if err != nil {
		return false, fmt.Errorf("failed to read asset: %v", err)
	}
	return assetJSON != nil, nil
}

// encodeAssetToJSON encodes the asset to JSON format
func (cc *CopyrightChaincode) encodeAssetToJSON(asset *CopyrightAsset) string {
	assetJSON, _ := json.Marshal(asset)
	return string(assetJSON)
}

// decodeAssetFromJSON decodes the asset from JSON format
func (cc *CopyrightChaincode) decodeAssetFromJSON(assetJSON []byte, asset *CopyrightAsset) error {
	err := json.Unmarshal(assetJSON, asset)
	if err != nil {
		return fmt.Errorf("failed to decode asset: %v", err)
	}
	return nil
}

func main() {
	chaincode, err := contractapi.NewChaincode(&CopyrightChaincode{})
	if err != nil {
		fmt.Printf("Error creating copyright chaincode: %v", err)
		return
	}

	if err := chaincode.Start(); err != nil {
		fmt.Printf("Error starting copyright chaincode: %v", err)
	}
}
