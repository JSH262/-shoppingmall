package com.tjoeun.shoppingmall.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.helper.PaymentStatus;
import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.mybatis.MySession;
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

public class PaymentService 
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	static PaymentService g_inst = new PaymentService();
	PaymentService() {}
	
	static public PaymentService getInstance()
	{
		return g_inst;
	}

	public List<PaymentVO> selectList(PaymentVO vo)
	{
		List<PaymentVO> retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = PaymentDAO.getInstance().selectList(mapper, vo);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			logger.error("", exp);
		}
		
		mapper.close();
				
		return retval;
	}
	public int insert(PaymentVO vo)
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = PaymentDAO.getInstance().insert(mapper, vo);
			mapper.commit();
		}
		catch(Exception exp)
		{
			mapper.rollback();
			logger.error("", exp);
		}
		
		mapper.close();
				
		return retval;
	}
	
	
	public boolean pay(UsersVO user, DestinationAddressVO destAddrVO)
	{
		SqlSession mapper = MySession.getSession();
		CartDAO cartDAO = CartDAO.getInstance();
		ProductOrderDAO poDAO = ProductOrderDAO.getInstance();
		PaymentDAO paymentDAO = PaymentDAO.getInstance();
		boolean retval = false;
		ProductDAO productDAO = ProductDAO.getInstance();
		DestinationAddressDAO destAddrDAO = DestinationAddressDAO.getInstance();
		try
		{
			ProductOrderVO poVO = new ProductOrderVO();
			CartVO cart = new CartVO();
			destAddrVO = destAddrDAO.select(mapper, destAddrVO);
						
			cart.setUserId(user.getId());			
			poVO.setUserId(user.getId());
			
			List<CartVO> productIds = cartDAO.selectList(mapper, cart);
			Long poId = poDAO.selectId(mapper, poVO);
						
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
						if(productDAO.updateDecrement(mapper, pVO) == 1)
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
							
							poDAO.insert(mapper, params);
												
							paymentPrice += item.getAmount() * item.getDiscountPrice();
							paymentPrice += item.getDeliveryPrice();
							
							cartDAO.delete(mapper, item);
						}
					}
				}
				
				
				PaymentVO pVo = new PaymentVO();
				
				pVo.setUserId(user.getId());
				pVo.setPaymentNumber(String.valueOf(System.currentTimeMillis()));
				pVo.setStatus(PaymentStatus.SUCCESS);
				pVo.setProductOrderId(poId);
				pVo.setPaymentPrice(paymentPrice);
				
				paymentDAO.insert(mapper, pVo);

				retval = true;
			}
			
			mapper.commit();
		}
		catch(Exception exp)
		{
			mapper.rollback();
			logger.error("", exp);
		}
		
		mapper.close();
		
		return retval;
	}
	
	
	
	
}
