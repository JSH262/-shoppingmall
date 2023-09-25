package com.tjoeun.shoppingmall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.helper.PaymentStatus;
import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.DestinationAddressDAO;
import com.tjoeun.shoppingmall.dao.PaymentDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.dao.ProductOrderDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;
import com.tjoeun.shoppingmall.vo.PaymentVO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
@Transactional(readOnly=true)
public class PaymentService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	//*/
	
	@Autowired
	PaymentDAO paymentDAO;

	@Autowired
	CartDAO cartDAO;

	@Autowired
	ProductOrderDAO productOrderDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	DestinationAddressDAO destinationAddressDAO;
	
	public List<PaymentVO> selectList(PaymentVO vo)
	{
		/*
		List<PaymentVO> retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			PaymentDAO dao = th.getMapper(PaymentDAO.class);
			
			retval = dao.selectList(vo);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		
		return paymentDAO.selectList(vo);
	}
	
	@Transactional
	public int insert(PaymentVO vo)
	{
		/*
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			PaymentDAO dao = th.getMapper(PaymentDAO.class);
			
			retval = dao.insert(vo);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		
		return paymentDAO.insert(vo);
	}
	
	@Transactional
	public boolean pay(UsersVO user, DestinationAddressVO destAddrVO)
	{
		/*
		boolean retval = false;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{

			PaymentDAO dao = th.getMapper(PaymentDAO.class);
			CartDAO cartDAO = th.getMapper(CartDAO.class);
			ProductOrderDAO poDAO = th.getMapper(ProductOrderDAO.class);
			ProductDAO productDAO = th.getMapper(ProductDAO.class);
			DestinationAddressDAO destAddrDAO = th.getMapper(DestinationAddressDAO.class);
			
			ProductOrderVO poVO = new ProductOrderVO();
			CartVO cart = new CartVO();
			destAddrVO = destAddrDAO.select(destAddrVO);
						
			cart.setUserId(user.getId());			
			poVO.setUserId(user.getId());
			
			List<CartVO> productIds = cartDAO.selectList(cart);
			Long poId = poDAO.selectId(poVO);
						
			if(productIds != null)
			{
				long paymentPrice = 0;
				for(CartVO item : productIds)
				{	
					//장바구니의 수량이 0보다 크고 장바구니의 수량보다 상품의 재고개수가 같거나 큰가?
					if(item.getAmount() > 0 && item.getAmount() <= item.getProductAmount())
					{
						ProductVO pVO = new ProductVO();
						
						pVO.setId(item.getProductId());
						pVO.setAmount(item.getAmount());
												
						//상품의 재고 개수를 감소시킨다.
						if(productDAO.updateDecrement(pVO) == 1)
						{						
							ProductOrderVO params = new ProductOrderVO();
							
							params.setId(poId);
							params.setUserId(user.getId());
							params.setProductId(item.getProductId());
							params.setProductAmount(item.getAmount());
							params.setProductPrice(item.getDiscountPrice());
							params.setProductDeliveryPrice(item.getDeliveryPrice());
							params.setProductName(item.getProductName());
							params.setStatus(ProductOrderStatus.NML_PAYMENT_COMPLETE.getCode());
							params.setProductThumbnail(item.getThumbnail());							
							params.setDeliveryName(destAddrVO.getName());
							params.setDeliveryAddr1(destAddrVO.getAddr1());
							params.setDeliveryAddr2(destAddrVO.getAddr2());
							params.setDeliveryPhone(destAddrVO.getPhone());
							params.setDeliveryRequestMsg(destAddrVO.getReqMsg());
							params.setSellerId(item.getSellerId());
							
							poDAO.insert(params);
												
							paymentPrice += item.getAmount() * item.getDiscountPrice();
							paymentPrice += item.getDeliveryPrice();
							
							cartDAO.delete(item);
						}
					}
				}
				
				
				PaymentVO pVo = new PaymentVO();
				
				pVo.setUserId(user.getId());
				pVo.setPaymentNumber(String.valueOf(System.currentTimeMillis()));
				pVo.setStatus(PaymentStatus.SUCCESS);
				pVo.setProductOrderId(poId);
				pVo.setPaymentPrice(paymentPrice);
				
				dao.insert(pVo);

				retval = true;
			}
			
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		
		boolean retval = false;

		PaymentDAO dao = this.paymentDAO;			
		ProductOrderDAO poDAO = this.productOrderDAO;			
		DestinationAddressDAO destAddrDAO = this.destinationAddressDAO;
		
		ProductOrderVO poVO = new ProductOrderVO();
		CartVO cart = new CartVO();
		destAddrVO = destAddrDAO.select(destAddrVO);
					
		cart.setUserId(user.getId());			
		poVO.setUserId(user.getId());
		
		List<CartVO> productIds = cartDAO.selectList(cart);
		Long poId = poDAO.selectId(poVO);
					
		if(productIds != null)
		{
			long paymentPrice = 0;
			for(CartVO item : productIds)
			{	
				//장바구니의 수량이 0보다 크고 장바구니의 수량보다 상품의 재고개수가 같거나 큰가?
				if(item.getAmount() > 0 && item.getAmount() <= item.getProductAmount())
				{
					ProductVO pVO = new ProductVO();
					
					pVO.setId(item.getProductId());
					pVO.setAmount(item.getAmount());
											
					//상품의 재고 개수를 감소시킨다.
					if(productDAO.updateDecrement(pVO) == 1)
					{						
						ProductOrderVO params = new ProductOrderVO();
						
						params.setId(poId);
						params.setUserId(user.getId());
						params.setProductId(item.getProductId());
						params.setProductAmount(item.getAmount());
						params.setProductPrice(item.getDiscountPrice());
						params.setProductDeliveryPrice(item.getDeliveryPrice());
						params.setProductName(item.getProductName());
						params.setStatus(ProductOrderStatus.NML_PAYMENT_COMPLETE.getCode());
						params.setProductThumbnail(item.getThumbnail());							
						params.setDeliveryName(destAddrVO.getName());
						params.setDeliveryAddr1(destAddrVO.getAddr1());
						params.setDeliveryAddr2(destAddrVO.getAddr2());
						params.setDeliveryPhone(destAddrVO.getPhone());
						params.setDeliveryRequestMsg(destAddrVO.getReqMsg());
						params.setSellerId(item.getSellerId());
						
						poDAO.insert(params);
											
						paymentPrice += item.getAmount() * item.getDiscountPrice();
						paymentPrice += item.getDeliveryPrice();
						
						cartDAO.delete(item);
					}
				}
			}
			
			
			PaymentVO pVo = new PaymentVO();
			
			pVo.setUserId(user.getId());
			pVo.setPaymentNumber(String.valueOf(System.currentTimeMillis()));
			pVo.setStatus(PaymentStatus.SUCCESS);
			pVo.setProductOrderId(poId);
			pVo.setPaymentPrice(paymentPrice);
			
			dao.insert(pVo);

			retval = true;
		}
			
		return retval;
	}
	
}
