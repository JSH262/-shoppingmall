package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.PaymentStatus;
import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.PaymentService;
import com.tjoeun.shoppingmall.service.ProductOrderService;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.PaymentVO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.UsersVO;


@RequestMapping
public class ProductPaymentController
{
	private static final long serialVersionUID = 1L;

	@RequestMapping(value="/product/payment", method=RequestMethod.GET)
	public String productPayment()
	{
		return "product/payment";
	}
	
	
	@RequestMapping(value="/product/payment", method=RequestMethod.POST)
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		final String PAYMENT_PROCESS_PAY_AFTER = "pay_after"; //결제를 하기 전에 결제할 금액을 가져올 이름
		final String PAYMENT_PROCESS_PAY_BEFORE = "pay_before"; //결제가 성공적으로 종료가 되면 사용할 이름
		
		JSONObject retval = new JSONObject();
		UsersVO user = AttributeName.getUserData(request);		
		JSONObject jParams = com.tjoeun.helper.Util.toJSONObject(request);
		String status = PAYMENT_PROCESS_PAY_AFTER;
		
		if(jParams != null)
		{
			status = jParams.get("status").toString();
		}
		else
		{
			try
			{
				PaymentVO item = new PaymentVO(request);
				
				status = item.getStatus();
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
				
		if(user != null)
		{
			//cart 테이블에 있는 모든 상품의 가격을 계산한다.
			if(PAYMENT_PROCESS_PAY_AFTER.equals(status))
			{
				CartVO params = new CartVO();
				
				params.setUserId(user.getId());
				
				List<CartVO> productIds = CartService.getInstance().selectList(params);
				
				if(productIds != null)
				{
					JSONObject result = new JSONObject();
					DecimalFormat numFormat = new DecimalFormat("#,###");
					
					long totalDiscountPrice = 0L;
					int totalDevliveryPrice = 0;
					
					for(CartVO item : productIds)
					{
						int discountPrice = item.getAmount() * item.getDiscountPrice();
						int deliveryPrice = item.getDeliveryPrice();
						
						totalDiscountPrice += discountPrice;
						totalDevliveryPrice += deliveryPrice;
					}
					
					long paymentPrice = totalDiscountPrice + totalDevliveryPrice;
					
					result.put("paymentPrice", paymentPrice); // 가격
					result.put("fmtPaymentPrice", numFormat.format(paymentPrice)); // 가격(#,###원)
					retval.put("result", result);
					retval.put("code", 0);
				}
				else
				{
					retval.put("code", -999);
					retval.put("msg", "parameter is empty");
				}
			}
			else if(PAYMENT_PROCESS_PAY_BEFORE.equals(status))
			{
				CartVO cart = new CartVO();				
				cart.setUserId(user.getId());
				
				List<CartVO> productIds = CartService.getInstance().selectList(cart);
				Long poId = ProductOrderService.getInstance().selectId(user.getId());
				
				
				if(productIds != null)
				{
					long paymentPrice = 0;
					for(CartVO item : productIds)
					{
						ProductOrderVO params = new ProductOrderVO();
						
						params.setId(poId);
						params.setUserId(user.getId());
						params.setProductId(item.getProductId());
						params.setProductAmount(item.getAmount());
						params.setProductPrice(item.getDiscountPrice());
						params.setProductDeliveryPrice(item.getDeliveryPrice());
						params.setProductName(item.getProductName());
						params.setStatus(ProductOrderStatus.COMPLATE);
						params.setProductThumbnail(item.getThumbnail());
						
						ProductOrderService.getInstance().insert(params);
						
						paymentPrice += item.getAmount() * item.getDiscountPrice();
						paymentPrice += item.getDeliveryPrice();
					}
					
					
					PaymentVO pVo = new PaymentVO();
					
					pVo.setUserId(user.getId());
					pVo.setPaymentNumber(String.valueOf(System.currentTimeMillis()));
					pVo.setStatus(PaymentStatus.SUCCESS);
					pVo.setProductOrderId(poId);
					pVo.setPaymentPrice(paymentPrice);
					
					System.out.println(pVo);
					PaymentService.getInstance().insert(pVo);
				}

//////////////////////////////////////////////////////////////////////////////////상품 목록에서 수량을 감소			
				
				CartService.getInstance().delete(cart);
				
				
				retval.put("code", 0);
				retval.put("msg", "결제가 완료되었습니다.");				
			}
			else
			{
				retval.put("code", -997);
				retval.put("msg", "error");
			}
		}
		else
		{
			retval.put("code", -998);
			retval.put("msg", "error");
		}
		
		
		
		retval.put("code", 0);
		
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}
}
