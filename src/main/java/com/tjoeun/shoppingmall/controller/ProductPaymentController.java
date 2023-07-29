package com.tjoeun.shoppingmall.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.PaymentStatus;
import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.DestinationAddressService;
import com.tjoeun.shoppingmall.service.PaymentService;
import com.tjoeun.shoppingmall.service.ProductOrderService;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.PaymentVO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/product/payment")
public class ProductPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SettingVO setting = null;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() throws ServletException {

		super.init();
		
		this.setting = SettingService.getInstance().select();		
	}


	public String getCmd(HttpServletRequest request)
    {
    	String uri = request.getRequestURI();
    	int start = uri.lastIndexOf("/");
    	
    	return uri.substring(start + 1);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		this.doAction(request, response);
	}
	
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
		
		
		
		retval.put("code", 0);
		
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}
}
