package com.tjoeun.shoppingmall.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.PaymentService;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Controller
public class ProductPaymentController {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value="/product/payment", method=RequestMethod.GET)
	public String productPaymentList(HttpServletRequest request, HttpServletResponse response, Model model)
	{		
		CategoryVO[] cardList = {
				new CategoryVO(10000, "현대카드"),
				new CategoryVO(10001, "삼성카드"),
				new CategoryVO(10002, "비씨카드"),
				new CategoryVO(10003, "KB국민"),
				new CategoryVO(10004, "신한카드"),
				new CategoryVO(10005, "롯데카드"),
				new CategoryVO(10006, "NH농협"),
				new CategoryVO(10007, "하나카드"),
				new CategoryVO(10008, "SC제일"),
				new CategoryVO(10009, "우리카드")
		};
		
		model.addAttribute("cardList", cardList);
		
		return "product/payment";
	}
	

	@RequestMapping(value="/product/payment", method=RequestMethod.POST)
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws Exception 
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
							if(item.getProductAmount() >= item.getAmount() && item.getProductAmount() > 0)
							{
								int discountPrice = item.getAmount() * item.getDiscountPrice();
								int deliveryPrice = item.getDeliveryPrice();
								
								totalDiscountPrice += discountPrice;
								totalDevliveryPrice += deliveryPrice;	
							}
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
					if(PaymentService.getInstance().pay(user, AttributeName.getDestAddr(request)))
					{
						retval.put("code", 0);
						retval.put("msg", "결제가 완료되었습니다.");	
					}
					else
					{
						retval.put("code", -996);
						retval.put("msg", "error");					
					}
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
		}
		else
		{
			retval.put("code", -991);
			retval.put("msg", "error");
		}
		
		
		retval.put("code", 0);
		
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}
}
