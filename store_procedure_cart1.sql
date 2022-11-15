delimiter //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_addToCartAdvance`(
	inout pCartId bigint,
	in pSessionId varchar(100),
    in pTokenId varchar(100),
    in pStatus SMALLINT,
    in pProductId bigint,
    in pQuantity SMALLINT,
    in pPrice float,
    out pMessage varchar(200)
)
begin
    declare bAmount SMALLINT;
    declare bTempTotal SMALLINT;
    declare bAddCartItem boolean;
    
    START TRANSACTION;
	set @bCheckCartExists = (not exists (select * from cart where `id` = pCartId)) or pCartId = NULL;
	if(@bCheckCartExists) then
			INSERT INTO `cart` (`userId`,`sessionId`, `token`, `status`, `createdAt`) 
			VALUES ('1', pSessionId, pTokenId, '1', now());
			set pCartId = (SELECT max(id) from cart);
	end if;
    set @bAmount = (SELECT `quantity` FROM `cart_item` where `productId`  = pProductId and `cartId` = pCartId);
	set @bTempTotal = @bAmount + pQuantity;
	
    set @bValid = 0;
   -- select concat(pCartId, @bValid, pMessage, "----");
    if(exists (SELECT `productId` FROM `cart_item` where `productId`  = pProductId and `cartId` = pCartId)) then
            UPDATE `cart_item` SET `quantity` = @bTempTotal  WHERE (`productId`  = pProductId and `cartId` = pCartId);
			set pMessage = concat("Update quantity success ","cartId:", pCartId,"productId:",pProductId,"quantity:", @bTempTotal);
            call sp_checkProductQuantiy(pProductId, @bTempTotal, @bValid);	
			set @bQuantity = (select `quantity` from `product` where `id` = pProductId);
			UPDATE `product` SET `quantity` = (@bQuantity - @bTempTotal) WHERE (`id` = pProductId);
		else
			INSERT INTO `cart_item` (`productId`, `cartId`, `sku`, `price`, `discount`, 
			`quantity`, `active`, `createdAt`, `updatedAt`) VALUES 
			(pProductId, pCartId, 'sp2', pPrice, '0', pQuantity, '1', now(), now());
            call sp_checkProductQuantiy(pProductId, 1, @bValid);	
            set @bQuantity = (select `quantity` from `product` where `id` = pProductId);
            UPDATE `product` SET `quantity` = (@bQuantity - 1) WHERE (`id` = pProductId);
			set pMessage = concat("Add item to cart success ","cartId:", pCartId,"productId:", pProductId, "quantity:" , 1);
        end if;
	if(@bValid = false) then
		set pMessage = "Update fail quantity not valid....";
        select concat(pCartId, @bValid, pMessage, "vvvv");
		ROLLBACK;
	end if;
	COMMIT;
end //